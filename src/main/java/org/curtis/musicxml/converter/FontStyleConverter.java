package org.curtis.musicxml.converter;

import org.curtis.musicxml.display.FontStyle;

import javax.persistence.AttributeConverter;

public class FontStyleConverter extends BaseConverter implements AttributeConverter<FontStyle, String> {
    public FontStyleConverter() {

    }

    @Override
    public String convertToDatabaseColumn(FontStyle enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public FontStyle convertToEntityAttribute(String value) {
        return (FontStyle) dbValueToEnum(value, FontStyle.class);
    }
}
