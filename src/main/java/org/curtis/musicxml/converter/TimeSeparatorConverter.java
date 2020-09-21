package org.curtis.musicxml.converter;

import org.curtis.musicxml.attributes.time.TimeSeparator;

import javax.persistence.AttributeConverter;

public class TimeSeparatorConverter extends BaseConverter implements AttributeConverter<TimeSeparator, String> {
    public TimeSeparatorConverter() {

    }

    @Override
    public String convertToDatabaseColumn(TimeSeparator enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public TimeSeparator convertToEntityAttribute(String value) {
        return (TimeSeparator) dbValueToEnum(value, TimeSeparator.class);
    }
}
