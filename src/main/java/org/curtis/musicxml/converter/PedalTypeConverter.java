package org.curtis.musicxml.converter;

import org.curtis.musicxml.direction.directiontype.PedalType;

import javax.persistence.AttributeConverter;

public class PedalTypeConverter extends BaseConverter implements AttributeConverter<PedalType, String> {
    public PedalTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(PedalType enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public PedalType convertToEntityAttribute(String value) {
        return (PedalType) dbValueToEnum(value, PedalType.class);
    }
}
