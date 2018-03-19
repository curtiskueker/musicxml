package org.curtis.musicxml.builder.util;

import org.curtis.musicxml.note.NoteTypeValue;
import org.curtis.util.StringUtil;

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

    public static String stringValue(Integer integerValue) {
        return integerValue == null ? "" : String.valueOf(integerValue);
    }

    public static String noteTypeValue(NoteTypeValue noteTypeValue) {
        if (noteTypeValue == null) return "";

        String value = enumValue(noteTypeValue);
        return value.replace("_", "");
    }
}
