package org.curtis.musicxml.converter;

import org.curtis.musicxml.attributes.StaffType;

import javax.persistence.AttributeConverter;

public class StaffTypeConverter extends BaseConverter implements AttributeConverter<StaffType, String> {
    public StaffTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(StaffType enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public StaffType convertToEntityAttribute(String value) {
        return (StaffType) dbValueToEnum(value, StaffType.class);
    }
}
