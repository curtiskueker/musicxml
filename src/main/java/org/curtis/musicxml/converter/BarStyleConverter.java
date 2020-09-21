package org.curtis.musicxml.converter;

import org.curtis.musicxml.barline.BarStyle;

import javax.persistence.AttributeConverter;

public class BarStyleConverter extends BaseConverter implements AttributeConverter<BarStyle, String> {
    public BarStyleConverter() {

    }

    @Override
    public String convertToDatabaseColumn(BarStyle enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public BarStyle convertToEntityAttribute(String value) {
        return (BarStyle) dbValueToEnum(value, BarStyle.class);
    }
}
