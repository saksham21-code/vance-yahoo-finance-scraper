package com.example.yahooexchangescraper.controller;


import com.example.yahooexchangescraper.service.ExchangeScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/exchange")
public class ExchangeController {

    @Autowired
    private ExchangeScraperService exchangeScraperService;

    /**
     * @param quote     e.g., "EURUSD=X".
     * @param fromDate  start date.
     * @param toDate    end date.
     */

    @PostMapping("/scrape")
    public void scrapeExchangeData(@RequestParam String quote, @RequestParam long fromDate, @RequestParam long toDate) {
        exchangeScraperService.scrapeAndStoreExchangeRates(quote, fromDate, toDate);
    }

}
