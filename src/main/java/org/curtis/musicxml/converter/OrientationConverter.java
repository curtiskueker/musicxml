package org.curtis.musicxml.converter;

import org.curtis.musicxml.display.Orientation;

import javax.persistence.AttributeConverter;

public class OrientationConverter extends BaseConverter implements AttributeConverter<Orientation, String> {
    public OrientationConverter() {

    }

    @Override
    public String convertToDatabaseColumn(Orientation enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public Orientation convertToEntityAttribute(String value) {
        return (Orientation) dbValueToEnum(value, Orientation.class);
    }
}
