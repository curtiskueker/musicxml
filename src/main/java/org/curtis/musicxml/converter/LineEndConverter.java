package org.curtis.musicxml.converter;

import org.curtis.musicxml.direction.directiontype.LineEnd;

import javax.persistence.AttributeConverter;

public class LineEndConverter extends BaseConverter implements AttributeConverter<LineEnd, String> {
    public LineEndConverter() {

    }

    @Override
    public String convertToDatabaseColumn(LineEnd enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public LineEnd convertToEntityAttribute(String value) {
        return (LineEnd) dbValueToEnum(value, LineEnd.class);
    }
}
