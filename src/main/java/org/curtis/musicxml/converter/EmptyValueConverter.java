package org.curtis.musicxml.converter;

import org.curtis.musicxml.builder.BuilderUtil;

public abstract class EmptyValueConverter {
    protected String enumToDbValue(Enum enumValue) {
        if (enumValue == null) return "";
        return enumValue.toString();
    }

    protected Enum dbValueToEnum(String value, Class enumClass) {
        if (value == null) return null;
        if (value.equals("")) {
            return Enum.valueOf(enumClass, BuilderUtil.EMPTY_VALUE);
        }
        return Enum.valueOf(enumClass, value);
    }
}
