package org.curtis.musicxml.converter;

import org.curtis.musicxml.link.Show;

import javax.persistence.AttributeConverter;

public class ShowConverter extends BaseConverter implements AttributeConverter<Show, String> {
    public ShowConverter() {

    }

    @Override
    public String convertToDatabaseColumn(Show enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public Show convertToEntityAttribute(String value) {
        return (Show) dbValueToEnum(value, Show.class);
    }
}
