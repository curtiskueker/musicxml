package org.curtis.musicxml.factory;

import org.curtis.util.StringUtil;

public class FactoryUtil {
    private FactoryUtil() {

    }

    public static <T extends Enum<T>> Enum<T> enumValue(Class<T> enumType, String elementValue) {
        if (StringUtil.isEmpty(elementValue)) return null;

        String enumString = elementValue.toUpperCase().replace("-", "_").replace(" ", "_");

        Enum<T> enumItem = null;
        try {
            enumItem = Enum.valueOf(enumType, enumString);
        } catch (IllegalArgumentException e) {
            System.err.println("Enum value " + enumType.getSimpleName() + "." + enumString + " not found");
        }

        return enumItem;
    }

    public static <T extends Enum<T>> Enum<T> enumValueWithEmptyValue(Class<T> enumType, String elementValue) {
        if (StringUtil.isEmptyString(elementValue)) elementValue = "empty_value";

        return enumValue(enumType, elementValue);
    }
}
