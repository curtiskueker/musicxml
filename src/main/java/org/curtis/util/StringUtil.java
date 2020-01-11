package org.curtis.util;

public class StringUtil {
    private StringUtil() {
    }

    public static boolean isEmpty(String string) {
        return (string == null || string.equals(""));
    }

    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

    public static boolean isEmptyString(String string) {
        return string != null && string.equals("");
    }

    public static String nullToString(String string) {
        return string == null ? "" : string;
    }

    public static Integer getInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
