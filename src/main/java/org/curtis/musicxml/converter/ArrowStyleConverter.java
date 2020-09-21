package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.notation.technical.ArrowStyle;

import javax.persistence.AttributeConverter;

public class ArrowStyleConverter extends BaseConverter implements AttributeConverter<ArrowStyle, String> {
    public ArrowStyleConverter() {

    }

    @Override
    public String convertToDatabaseColumn(ArrowStyle enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public ArrowStyle convertToEntityAttribute(String value) {
        return (ArrowStyle) dbValueToEnum(value, ArrowStyle.class);
    }
}
