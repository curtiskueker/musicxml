package org.curtis.musicxml.converter;

import org.curtis.musicxml.direction.directiontype.WedgeType;

import javax.persistence.AttributeConverter;

public class WedgeTypeConverter extends BaseConverter implements AttributeConverter<WedgeType, String> {
    public WedgeTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(WedgeType enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public WedgeType convertToEntityAttribute(String value) {
        return (WedgeType) dbValueToEnum(value, WedgeType.class);
    }
}
