package org.curtis.musicxml.converter;

import org.curtis.musicxml.display.Placement;

import javax.persistence.AttributeConverter;

public class PlacementConverter extends BaseConverter implements AttributeConverter<Placement, String> {
    public PlacementConverter() {

    }

    @Override
    public String convertToDatabaseColumn(Placement enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public Placement convertToEntityAttribute(String value) {
        return (Placement) dbValueToEnum(value, Placement.class);
    }
}
