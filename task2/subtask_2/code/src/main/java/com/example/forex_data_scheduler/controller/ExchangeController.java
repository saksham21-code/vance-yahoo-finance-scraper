package com.example.forex_data_scheduler.controller;


import com.example.forex_data_scheduler.model.ExchangeRate;
import com.example.forex_data_scheduler.service.ExchangeRateQueryService;
import com.example.forex_data_scheduler.service.ExchangeScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exchange")
public class ExchangeController {

    @Autowired
    private ExchangeScraperService exchangeScraperService;

    @Autowired
    private ExchangeRateQueryService exchangeRateQueryService;

    /**
     * Endpoint to scrape exchange data from Yahoo Finance and store it in the database.
     *  quote :     e.g., "EURUSD=X".
     *  fromDate :  start date as Unix timestamp.
     *  toDate :    end date as Unix timestamp.
     */
    @PostMapping("/scrape")
    public void scrapeExchangeData(@RequestParam String quote, @RequestParam long fromDate, @RequestParam long toDate) {
        exchangeScraperService.scrapeAndStoreExchangeRates(quote, fromDate, toDate);
    }

    /**
     * Endpoint to query historical exchange rate data between given periods.
     * from :  From currency code (e.g., GBP, AED).
     * to :     To currency code (e.g., INR).
     * period : Time period (e.g., 1W, 1M, 3M, 6M, 1Y).
     * return List of ExchangeRate objects or a message if no data is found.
     */
    @PostMapping("/forex-data")
    public ResponseEntity<?> getForexData(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam String period) {
        List<ExchangeRate> exchangeRates = exchangeRateQueryService.getHistoricalExchangeRates(from, to, period);

        if (exchangeRates.isEmpty()) {
            return ResponseEntity.ok("No exchange rate data found for the given query.");
        }

        return ResponseEntity.ok(exchangeRates);
    }
}
