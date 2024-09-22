package com.example.forex_data_api.service;


import com.example.forex_data_api.model.ExchangeRate;
import com.example.forex_data_api.repository.ExchangeRateRepository;
import com.example.forex_data_api.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ExchangeScraperService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    /**
     * method to scrapes and stores it in the database.
     * quote    The currency pair
     * fromDate The start date as a Unix timestamp.
     * toDate   The end date as a Unix timestamp.
     */
    public void scrapeAndStoreExchangeRates(String quote, long fromDate, long toDate) {
        try {
            // Construct URL
            String url = String.format("https://finance.yahoo.com/quote/%s/history?period1=%d&period2=%d",
                    quote, fromDate, toDate);
            log.info("Connecting to URL: {}", url);

            // Jsoup Fetches the HTML content from the URL
            Document doc = Jsoup.connect(url).get();
            log.info("Successfully connected to URL");

            // STEP 1 : selecting the table.
            Element table = doc.selectFirst("table.table.yf-ewueuo.noDl");

            if (table == null) {
                log.warn("Historical Prices table not found in the fetched HTML.");
                return;
            }

            // STEP 2 : selecting the rows within the table.
            Elements rows = table.select("tbody tr");
            log.info("Number of rows found: {}", rows.size());

            List<ExchangeRate> rates = new ArrayList<>();

            // STEP 3 : parse the coulmn's within each row and extract data and put it in a object/model.
            for (Element row : rows) {
                Elements cols = row.select("td");

                // Skip the rows that do not have complete data
                if (cols.size() < 6) continue;
                // Parse and extract data
                LocalDate date = DateUtils.parseYahooDate(cols.get(0).text());
                Double open = parseDoubleValue(cols.get(1).text());
                Double high = parseDoubleValue(cols.get(2).text());
                Double low = parseDoubleValue(cols.get(3).text());
                Double close = parseDoubleValue(cols.get(4).text());
                Double adjClose = parseDoubleValue(cols.get(5).text());
                Long volume = parseLongValue(cols.get(6).text());

                // Create a new Model/Object to store data
                ExchangeRate rate = new ExchangeRate();
                rate.setQuote(quote);
                rate.setDate(date);
                rate.setOpen(open);
                rate.setHigh(high);
                rate.setLow(low);
                rate.setClose(close);
                rate.setAdjClose(adjClose);
                rate.setVolume(volume);

                rates.add(rate);
            }

            // Save the information in the DB
            exchangeRateRepository.saveAll(rates);
            log.info("Successfully saved {} exchange rates for {}", rates.size(), quote);

        } catch (Exception e) {
            log.error("Error while scraping and storing exchange rates: {}", e.getMessage());
        }
    }

    // Helper method to parse Double values to Handle missing data.
    private Double parseDoubleValue(String text) {
        try {
            return Double.parseDouble(text.replace(",", ""));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    // Helper method to parse Long values Indicate missing data with null, Handles missing data.
    private Long parseLongValue(String text) {
        try {
            if (text.equals("-")) {
                return null;
            }
            return Long.parseLong(text.replace(",", ""));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
