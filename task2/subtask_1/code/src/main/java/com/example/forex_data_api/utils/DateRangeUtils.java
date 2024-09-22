package com.example.forex_data_api.utils;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateRangeUtils {

    /**
     * Calculate the start date
     * Supports formats like 1W, 2W, 1M, 3M, 6M, 1Y, 2Y
     * toDate : End date (current date).
     * period : Time period (e.g., 1W, 2W, 1M, 3M, 6M, 1Y, 2Y).
     * return start date
     */
    public static LocalDate calculateFromDate(LocalDate toDate, String period) {
        Pattern pattern = Pattern.compile("(\\d+)([WMY])");
        Matcher matcher = pattern.matcher(period.toUpperCase());
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid period format. Use formats like 1W, 2W, 1M, 3M, 6M, 1Y, 2Y.");
        }

        int value = Integer.parseInt(matcher.group(1));
        String unit = matcher.group(2);

        switch (unit) {
            case "W":
                return toDate.minusWeeks(value);
            case "M":
                return toDate.minusMonths(value);
            case "Y":
                return toDate.minusYears(value);
            default:
                throw new IllegalArgumentException("Invalid period unit. Use W for weeks, M for months, or Y for years.");
        }
    }
}
