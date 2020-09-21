package org.curtis.musicxml.converter;

import org.curtis.musicxml.direction.directiontype.percussion.StickMaterial;

import javax.persistence.AttributeConverter;

public class StickMaterialConverter extends BaseConverter implements AttributeConverter<StickMaterial, String> {
    public StickMaterialConverter() {

    }

    @Override
    public String convertToDatabaseColumn(StickMaterial enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public StickMaterial convertToEntityAttribute(String value) {
        return (StickMaterial) dbValueToEnum(value, StickMaterial.class);
    }
}
