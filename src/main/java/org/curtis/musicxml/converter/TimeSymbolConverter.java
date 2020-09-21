package org.curtis.musicxml.converter;

import org.curtis.musicxml.attributes.time.TimeSymbol;

import javax.persistence.AttributeConverter;

public class TimeSymbolConverter extends BaseConverter implements AttributeConverter<TimeSymbol, String> {
    public TimeSymbolConverter() {

    }

    @Override
    public String convertToDatabaseColumn(TimeSymbol enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public TimeSymbol convertToEntityAttribute(String value) {
        return (TimeSymbol) dbValueToEnum(value, TimeSymbol.class);
    }
}
