package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.NoteTypeValue;

import javax.persistence.AttributeConverter;

public class NoteTypeValueConverter extends BaseConverter implements AttributeConverter<NoteTypeValue, String> {
    public NoteTypeValueConverter() {

    }

    @Override
    public String convertToDatabaseColumn(NoteTypeValue enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public NoteTypeValue convertToEntityAttribute(String value) {
        return (NoteTypeValue) dbValueToEnum(value, NoteTypeValue.class);
    }
}
