package org.curtis.musicxml.converter;

import org.curtis.musicxml.barline.BarlineLocation;

import javax.persistence.AttributeConverter;

public class BarlineLocationConverter extends BaseConverter implements AttributeConverter<BarlineLocation, String> {
    public BarlineLocationConverter() {

    }

    @Override
    public String convertToDatabaseColumn(BarlineLocation enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public BarlineLocation convertToEntityAttribute(String value) {
        return (BarlineLocation) dbValueToEnum(value, BarlineLocation.class);
    }
}
