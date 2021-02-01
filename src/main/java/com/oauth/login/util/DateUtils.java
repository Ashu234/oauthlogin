package com.oauth.login.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {

    public static final DateTimeFormatter dateTimeFormatter;
    public static String ISO_8601_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm'Z'";
    public static String SIMPLE_DATE_TIME_FORMAT = "yyyy-MM-dd";
    public static final DateTimeFormatter simpleDateFormate;

    static {
        dateTimeFormatter = DateTimeFormatter.ofPattern(ISO_8601_DATE_TIME_FORMAT).withZone(ZoneId.of("UTC"));
        simpleDateFormate = DateTimeFormatter.ofPattern(SIMPLE_DATE_TIME_FORMAT);
    }

    public static Instant ISOStringToInstant(String stringDate) throws DateTimeParseException {
        return stringDate == null ? null : dateTimeFormatter.parse(stringDate, Instant::from);
    }

    public static String instantTOISOString(Instant instantDate) {
        return instantDate == null ? null : dateTimeFormatter.format(instantDate);
    }


    public static boolean isValid(String dateStr) {
        try {
            LocalDate.parse(dateStr, simpleDateFormate);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public static boolean validateRange(String fromDate, String toDate) {
        return LocalDate.parse(fromDate, simpleDateFormate).isBefore(LocalDate.parse(toDate, simpleDateFormate));
    }
}
