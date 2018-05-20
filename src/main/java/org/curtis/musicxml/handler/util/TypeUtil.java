package org.curtis.musicxml.handler.util;

import org.curtis.util.StringUtil;

public class TypeUtil {
    private TypeUtil() {

    }

    public static Boolean getYesNo(String value) {
        if(StringUtil.isEmpty(value)) return null;

        switch (value) {
            case "yes":
                return true;
            case "no":
            default:
                return false;
        }
    }

    public static Boolean getYesNoDefaultYes(String value) {
        if(StringUtil.isEmpty(value)) return true;

        return getYesNo(value);
    }
}
