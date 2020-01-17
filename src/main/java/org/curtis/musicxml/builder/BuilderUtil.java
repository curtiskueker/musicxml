package org.curtis.musicxml.builder;

import org.curtis.musicxml.note.NoteTypeValue;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;

import java.math.BigDecimal;

public class BuilderUtil {
    public static String REQUIRED_ATTRIBUTE = "REQUIRED_ATTRIBUTE";
    public static String EMPTY_VALUE = "EMPTY_VALUE";

    private BuilderUtil() {

    }

    public static String enumValue(Enum enumValue) {
        if (enumValue == null) return "";

        String value = enumValue.toString();
        if (StringUtil.isEmpty(value)) return "";
        if (value.equals(EMPTY_VALUE)) return "";

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
        if (bigDecimalValue == null) return "";

        String value = String.valueOf(bigDecimalValue.stripTrailingZeros());
        return value.contains("E") ? Integer.toString(MathUtil.truncate(bigDecimalValue)) : value;
    }

    public static String yesOrNo(Boolean booleanValue) {
        if (booleanValue == null) return "";
        return booleanValue ? "yes" : "no";
    }

    public static String getDocumentDeclaration() {
        StringBuilder builder = new StringBuilder();
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
        builder.append("\n");
        builder.append("<!DOCTYPE score-partwise PUBLIC \"-//Recordare//DTD MusicXML 3.0 Partwise//EN\" \"http://www.musicxml.org/dtds/partwise.dtd\">");
        builder.append("\n");

        return builder.toString();
    }

    public static String requiredValue(String value) {
        return StringUtil.isEmpty(value) ? REQUIRED_ATTRIBUTE : value;
    }
}
