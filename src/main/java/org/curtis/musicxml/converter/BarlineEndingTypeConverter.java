package org.curtis.musicxml.converter;

import org.curtis.musicxml.barline.BarlineEndingType;

import javax.persistence.AttributeConverter;

public class BarlineEndingTypeConverter extends BaseConverter implements AttributeConverter<BarlineEndingType, String> {
    public BarlineEndingTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(BarlineEndingType enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public BarlineEndingType convertToEntityAttribute(String value) {
        return (BarlineEndingType) dbValueToEnum(value, BarlineEndingType.class);
    }
}
