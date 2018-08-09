package org.curtis.lilypond.util;

public class TypeUtil {
    private TypeUtil() {

    }

    public static Boolean getBoolean(Boolean value) {
        return value == null ? false : value;
    }

    public static Boolean getBooleanDefaultYes(Boolean value) {
        return value == null ? true : value;
    }
}
