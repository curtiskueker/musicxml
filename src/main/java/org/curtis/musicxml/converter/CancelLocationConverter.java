package org.curtis.musicxml.converter;

import org.curtis.musicxml.attributes.key.CancelLocation;

import javax.persistence.AttributeConverter;

public class CancelLocationConverter extends BaseConverter implements AttributeConverter<CancelLocation, String> {
    public CancelLocationConverter() {

    }

    @Override
    public String convertToDatabaseColumn(CancelLocation enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public CancelLocation convertToEntityAttribute(String value) {
        return (CancelLocation) dbValueToEnum(value, CancelLocation.class);
    }
}
