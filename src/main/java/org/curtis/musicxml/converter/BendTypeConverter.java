package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.notation.technical.BendType;

import javax.persistence.AttributeConverter;

public class BendTypeConverter extends BaseConverter implements AttributeConverter<BendType, String> {
    public BendTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(BendType enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public BendType convertToEntityAttribute(String value) {
        return (BendType) dbValueToEnum(value, BendType.class);
    }
}
