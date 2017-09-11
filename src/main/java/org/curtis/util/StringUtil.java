package org.curtis.util;

public class StringUtil {
    private StringUtil() {
    }

    public static boolean isEmpty(String string) {
        return (string == null || string.equals(""));
    }
}
