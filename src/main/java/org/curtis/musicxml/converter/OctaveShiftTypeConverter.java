package org.curtis.musicxml.converter;

import org.curtis.musicxml.direction.directiontype.OctaveShiftType;

import javax.persistence.AttributeConverter;

public class OctaveShiftTypeConverter extends BaseConverter implements AttributeConverter<OctaveShiftType, String> {
    public OctaveShiftTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(OctaveShiftType enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public OctaveShiftType convertToEntityAttribute(String value) {
        return (OctaveShiftType) dbValueToEnum(value, OctaveShiftType.class);
    }
}
