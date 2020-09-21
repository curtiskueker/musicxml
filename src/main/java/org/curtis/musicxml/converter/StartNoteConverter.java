package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.notation.ornament.StartNote;

import javax.persistence.AttributeConverter;

public class StartNoteConverter extends BaseConverter implements AttributeConverter<StartNote, String> {
    public StartNoteConverter() {

    }

    @Override
    public String convertToDatabaseColumn(StartNote enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public StartNote convertToEntityAttribute(String value) {
        return (StartNote) dbValueToEnum(value, StartNote.class);
    }
}
