package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.notation.technical.TapHand;

import javax.persistence.AttributeConverter;

public class TapHandConverter extends BaseConverter implements AttributeConverter<TapHand, String> {
    public TapHandConverter() {

    }

    @Override
    public String convertToDatabaseColumn(TapHand enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public TapHand convertToEntityAttribute(String value) {
        return (TapHand) dbValueToEnum(value, TapHand.class);
    }
}
