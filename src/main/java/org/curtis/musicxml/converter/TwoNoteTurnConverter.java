package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.notation.ornament.TwoNoteTurn;

import javax.persistence.AttributeConverter;

public class TwoNoteTurnConverter extends BaseConverter implements AttributeConverter<TwoNoteTurn, String> {
    public TwoNoteTurnConverter() {

    }

    @Override
    public String convertToDatabaseColumn(TwoNoteTurn enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public TwoNoteTurn convertToEntityAttribute(String value) {
        return (TwoNoteTurn) dbValueToEnum(value, TwoNoteTurn.class);
    }
}
