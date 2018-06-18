package org.curtis.musicxml.builder.util;

import org.curtis.musicxml.note.NoteTypeValue;
import org.curtis.util.StringUtil;

import java.math.BigDecimal;

public class BuilderUtil {

    public static String enumValue(Enum enumValue) {
        if (enumValue == null) return "";

        String value = enumValue.toString();
        if(StringUtil.isEmpty(value)) return "";

        return value.toLowerCase().replace("_", "-");
    }

    public static String enumValueWithSpaces(Enum enumValue) {
        return enumValue(enumValue).replace("-", " ");
    }

    public static String noteTypeValue(NoteTypeValue noteTypeValue) {
        if (noteTypeValue == null) return "";

        String value = enumValue(noteTypeValue);
        return value.replace("-", "");
    }

    public static String stringValue(Integer integerValue) {
        return integerValue == null ? "" : String.valueOf(integerValue);
    }

    public static String stringValue(BigDecimal bigDecimalValue) {
        return bigDecimalValue == null ? "" : String.valueOf(bigDecimalValue);
    }

    public static String yesOrNo(Boolean booleanValue) {
        if (booleanValue == null) return "";
        return booleanValue ? "yes" : "no";
    }
}
