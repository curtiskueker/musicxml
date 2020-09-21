package org.curtis.musicxml.converter;

import org.curtis.musicxml.direction.directiontype.StringMuteDirection;

import javax.persistence.AttributeConverter;

public class StringMuteDirectionConverter extends BaseConverter implements AttributeConverter<StringMuteDirection, String> {
    public StringMuteDirectionConverter() {

    }

    @Override
    public String convertToDatabaseColumn(StringMuteDirection enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public StringMuteDirection convertToEntityAttribute(String value) {
        return (StringMuteDirection) dbValueToEnum(value, StringMuteDirection.class);
    }
}
