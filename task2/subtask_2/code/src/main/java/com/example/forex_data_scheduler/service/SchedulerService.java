package com.example.forex_data_scheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class SchedulerService {

    @Autowired
    private ExchangeScraperService exchangeScraperService;


    // INR-USD for Testing 10 sec calls with 1M param
    @Scheduled(cron = "*/10 * * * * ?") // Every 10 seconds
    public void scrapeINRUSDTest() {
        scrapeWithPeriod("INRUSD=X", "1M");
    }

    // GBP-INR Schedules
    @Scheduled(cron = "0 0 0 * * MON") // Monday at midnight
    public void scrapeGBPINRWeekly() {
        scrapeWithPeriod("GBPINR=X", "1W");
    }
    @Scheduled(cron = "0 0 0 1 * ?") // every month at midnight
    public void scrapeGBPINRMonthly() {
        scrapeWithPeriod("GBPINR=X", "1M");
    }
    @Scheduled(cron = "0 0 0 1 */3 ?") //  3 months at midnight
    public void scrapeGBPINRQuarterly() {
        scrapeWithPeriod("GBPINR=X", "3M");
    }
    @Scheduled(cron = "0 0 0 1 */6 ?") //  6 months at midnight
    public void scrapeGBPINRSemiAnnually() {
        scrapeWithPeriod("GBPINR=X", "6M");
    }
    @Scheduled(cron = "0 0 0 1 1 ?") // First day of every year at midnight
    public void scrapeGBPINRYearly() {
        scrapeWithPeriod("GBPINR=X", "1Y");
    }

    // AED-INR Schedules
    @Scheduled(cron = "0 0 0 * * MON") // Every Monday at midnight
    public void scrapeAEDINRWeekly() {
        scrapeWithPeriod("AEDINR=X", "1W");
    }
    @Scheduled(cron = "0 0 0 1 * ?") // First day of every month at midnight
    public void scrapeAEDINRMonthly() {
        scrapeWithPeriod("AEDINR=X", "1M");
    }
    @Scheduled(cron = "0 0 0 1 */3 ?") // Every 3 months at midnight on the first day
    public void scrapeAEDINRQuarterly() {
        scrapeWithPeriod("AEDINR=X", "3M");
    }
    @Scheduled(cron = "0 0 0 1 */6 ?") // Every 6 months at midnight on the first day
    public void scrapeAEDINRSemiAnnually() {
        scrapeWithPeriod("AEDINR=X", "6M");
    }
    @Scheduled(cron = "0 0 0 1 1 ?") // First day of every year at midnight
    public void scrapeAEDINRYearly() {
        scrapeWithPeriod("AEDINR=X", "1Y");
    }

    // Helper method to perform scraping based on period
    private void scrapeWithPeriod(String quote, String period) {
        long fromDate = calculateUnixTimestampFromPeriod(period);
        long toDate = System.currentTimeMillis() / 1000L; // Current time in Unix
        exchangeScraperService.scrapeAndStoreExchangeRates(quote, fromDate, toDate);
    }

    // calculate Unix timestamp based on the period
    private long calculateUnixTimestampFromPeriod(String period) {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate;
        switch (period) {
            case "1W":
                startDate = endDate.minusWeeks(1);
                break;
            case "1M":
                startDate = endDate.minusMonths(1);
                break;
            case "3M":
                startDate = endDate.minusMonths(3);
                break;
            case "6M":
                startDate = endDate.minusMonths(6);
                break;
            case "1Y":
                startDate = endDate.minusYears(1);
                break;
            default:
                throw new IllegalArgumentException("Invalid period: " + period);
        }
        return startDate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
    }
}
