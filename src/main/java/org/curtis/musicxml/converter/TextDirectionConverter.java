package org.curtis.musicxml.converter;

import org.curtis.musicxml.display.TextDirection;

import javax.persistence.AttributeConverter;

public class TextDirectionConverter extends BaseConverter implements AttributeConverter<TextDirection, String> {
    public TextDirectionConverter() {

    }

    @Override
    public String convertToDatabaseColumn(TextDirection enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public TextDirection convertToEntityAttribute(String value) {
        return (TextDirection) dbValueToEnum(value, TextDirection.class);
    }
}
