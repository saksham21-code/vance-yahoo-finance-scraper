package com.example.forex_data_scheduler.service;


import com.example.forex_data_scheduler.model.ExchangeRate;
import com.example.forex_data_scheduler.repository.ExchangeRateRepository;
import com.example.forex_data_scheduler.utils.DateRangeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class ExchangeRateQueryService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    /**
     * Method to get historical exchange rate data based on given parameters.
     * from :  From currency code.
     * to : To currency code.
     * period : Time period (e.g., 1W, 1M, 3M, 6M, 1Y).
     * return List of ExchangeRate objects.
     */
    public List<ExchangeRate> getHistoricalExchangeRates(String from, String to, String period) {
        String quote = from + to + "=X";
        LocalDate toDate = LocalDate.now();
        LocalDate fromDate = DateRangeUtils.calculateFromDate(toDate, period);

        log.info("Querying exchange rates for quote: {}, fromDate: {}, toDate: {}", quote, fromDate, toDate);

        List<ExchangeRate> exchangeRates = exchangeRateRepository.findByQuoteAndDateBetween(quote, fromDate, toDate);

        if (exchangeRates.isEmpty()) {
            log.warn("No exchange rate data found for quote: {} in the period from {} to {}", quote, fromDate, toDate);
        } else {
            log.info("Found {} exchange rate records for quote: {} in the period from {} to {}",
                    exchangeRates.size(), quote, fromDate, toDate);
        }

        return exchangeRates;
    }
}
