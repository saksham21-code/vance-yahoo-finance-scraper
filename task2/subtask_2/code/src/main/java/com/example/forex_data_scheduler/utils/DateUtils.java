package com.example.forex_data_scheduler.utils;





import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtils {

    // uses "MMM d, yyyy" format for dates ("Jul 15, 2024")
    private static final DateTimeFormatter YAHOO_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);

    /**
     * get dateString
     * return the parsed LocalDate
     */
    public static LocalDate parseYahooDate(String dateString) {
        return LocalDate.parse(dateString, YAHOO_DATE_FORMAT);
    }

    /**
     *LocalDate to a Unix timestamp.
     */
    public static long toUnixTimestamp(LocalDate date) {
        return date.atStartOfDay().toEpochSecond(java.time.ZoneOffset.UTC);
    }
}
