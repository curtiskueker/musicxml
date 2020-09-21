package org.curtis.musicxml.converter;

import org.curtis.musicxml.display.FontWeight;

import javax.persistence.AttributeConverter;

public class FontWeightConverter extends BaseConverter implements AttributeConverter<FontWeight, String> {
    public FontWeightConverter() {

    }

    @Override
    public String convertToDatabaseColumn(FontWeight enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public FontWeight convertToEntityAttribute(String value) {
        return (FontWeight) dbValueToEnum(value, FontWeight.class);
    }
}
