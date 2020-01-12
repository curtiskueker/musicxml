package org.curtis.musicxml.handler.util;

import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;

import java.math.BigDecimal;

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
}
