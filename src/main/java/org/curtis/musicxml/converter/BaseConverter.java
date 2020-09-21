package org.curtis.musicxml.converter;

public abstract class BaseConverter {
    protected String enumToDbValue(Enum enumValue) {
        if (enumValue == null) return null;

        String dbValue = enumValue.toString();
        if (dbValue.startsWith("_")) dbValue = dbValue.replaceFirst("_", "");

        return dbValue.toLowerCase().replace("_", "-");
    }

    protected <E extends Enum<E>> Enum dbValueToEnum(String value, Class<E> enumClass) {
        if (value == null) return null;

        if (Character.isDigit(value.charAt(0))) value = "_" + value;

        return Enum.valueOf(enumClass, value.toUpperCase().replace(" ", "_").replace("-", "_"));
    }
}
