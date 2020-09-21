package org.curtis.musicxml.converter;

import org.curtis.musicxml.direction.directiontype.DynamicsType;

import javax.persistence.AttributeConverter;

public class DynamicsTypeConverter extends BaseConverter implements AttributeConverter<DynamicsType, String> {
    public DynamicsTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(DynamicsType enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public DynamicsType convertToEntityAttribute(String value) {
        return (DynamicsType) dbValueToEnum(value, DynamicsType.class);
    }
}
