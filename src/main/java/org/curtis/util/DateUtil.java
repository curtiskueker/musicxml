package org.curtis.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    private DateUtil() {

    }

    public static Date parseDate(String dateString, String formatString) {
        return parseDate(dateString, new SimpleDateFormat(formatString));
    }

    public static Date parseDate(String dateString, DateFormat dateFormat) {
        try {
            return dateFormat.parse(dateString);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date parseDate(String dateString) {
        return parseDate(dateString, YYYY_MM_DD);
    }
}
