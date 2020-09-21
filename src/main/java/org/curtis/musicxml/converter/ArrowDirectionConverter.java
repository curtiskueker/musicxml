package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.notation.technical.ArrowDirection;

import javax.persistence.AttributeConverter;

public class ArrowDirectionConverter extends BaseConverter implements AttributeConverter<ArrowDirection, String> {
    public ArrowDirectionConverter() {

    }

    @Override
    public String convertToDatabaseColumn(ArrowDirection enumValue) {
        if (enumValue == null) return null;

        switch (enumValue) {
            case LEFT_RIGHT:
            case UP_DOWN:
            case NORTHWEST_SOUTHEAST:
            case NORTHEAST_SOUTHWEST:
                return enumValue.toString().toLowerCase().replace("_", " ");
        }

        return enumToDbValue(enumValue);
    }

    @Override
    public ArrowDirection convertToEntityAttribute(String value) {
        return (ArrowDirection) dbValueToEnum(value, ArrowDirection.class);
    }
}
