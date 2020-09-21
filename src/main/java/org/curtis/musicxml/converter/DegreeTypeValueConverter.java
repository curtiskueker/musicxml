package org.curtis.musicxml.converter;

import org.curtis.musicxml.direction.harmony.DegreeTypeValue;

import javax.persistence.AttributeConverter;

public class DegreeTypeValueConverter extends BaseConverter implements AttributeConverter<DegreeTypeValue, String> {
    public DegreeTypeValueConverter() {

    }

    @Override
    public String convertToDatabaseColumn(DegreeTypeValue enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public DegreeTypeValue convertToEntityAttribute(String value) {
        return (DegreeTypeValue) dbValueToEnum(value, DegreeTypeValue.class);
    }
}
