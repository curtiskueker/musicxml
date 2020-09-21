package org.curtis.musicxml.converter;

import org.curtis.musicxml.barline.BarlineRepeatDirection;

import javax.persistence.AttributeConverter;

public class BarlineRepeatDirectionConverter extends BaseConverter implements AttributeConverter<BarlineRepeatDirection, String> {
    public BarlineRepeatDirectionConverter() {

    }

    @Override
    public String convertToDatabaseColumn(BarlineRepeatDirection enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public BarlineRepeatDirection convertToEntityAttribute(String value) {
        return (BarlineRepeatDirection) dbValueToEnum(value, BarlineRepeatDirection.class);
    }
}
