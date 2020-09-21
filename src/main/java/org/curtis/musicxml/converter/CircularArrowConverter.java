package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.notation.technical.CircularArrow;

import javax.persistence.AttributeConverter;

public class CircularArrowConverter extends BaseConverter implements AttributeConverter<CircularArrow, String> {
    public CircularArrowConverter() {

    }

    @Override
    public String convertToDatabaseColumn(CircularArrow enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public CircularArrow convertToEntityAttribute(String value) {
        return (CircularArrow) dbValueToEnum(value, CircularArrow.class);
    }
}
