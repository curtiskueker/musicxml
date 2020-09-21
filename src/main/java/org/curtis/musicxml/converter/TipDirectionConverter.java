package org.curtis.musicxml.converter;

import org.curtis.musicxml.direction.directiontype.percussion.TipDirection;

import javax.persistence.AttributeConverter;

public class TipDirectionConverter extends BaseConverter implements AttributeConverter<TipDirection, String> {
    public TipDirectionConverter() {

    }

    @Override
    public String convertToDatabaseColumn(TipDirection enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public TipDirection convertToEntityAttribute(String value) {
        return (TipDirection) dbValueToEnum(value, TipDirection.class);
    }
}
