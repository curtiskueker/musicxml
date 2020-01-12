package org.curtis.musicxml.factory;

import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;

import java.math.BigDecimal;

public class FactoryUtil {
    public static final BigDecimal MAXIMUM_POSITION_VALUE = MathUtil.newBigDecimal(99999999);

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
