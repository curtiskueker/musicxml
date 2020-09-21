package org.curtis.musicxml.converter;

import org.curtis.musicxml.common.play.SemiPitchedType;

import javax.persistence.AttributeConverter;

public class SemiPitchedTypeConverter extends BaseConverter implements AttributeConverter<SemiPitchedType, String> {
    public SemiPitchedTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(SemiPitchedType enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public SemiPitchedType convertToEntityAttribute(String value) {
        return (SemiPitchedType) dbValueToEnum(value, SemiPitchedType.class);
    }
}
