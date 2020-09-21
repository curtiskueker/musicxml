package org.curtis.musicxml.converter;

import org.curtis.musicxml.layout.MarginType;

import javax.persistence.AttributeConverter;

public class MarginTypeConverter extends BaseConverter implements AttributeConverter<MarginType, String> {
    public MarginTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(MarginType enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public MarginType convertToEntityAttribute(String value) {
        return (MarginType) dbValueToEnum(value, MarginType.class);
    }
}
