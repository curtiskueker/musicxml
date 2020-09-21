package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.notation.TiedType;

import javax.persistence.AttributeConverter;

public class TiedTypeConverter extends BaseConverter implements AttributeConverter<TiedType, String> {
    public TiedTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(TiedType enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public TiedType convertToEntityAttribute(String value) {
        return (TiedType) dbValueToEnum(value, TiedType.class);
    }
}
