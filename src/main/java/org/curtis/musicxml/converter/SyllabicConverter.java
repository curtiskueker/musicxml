package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.lyric.Syllabic;

import javax.persistence.AttributeConverter;

public class SyllabicConverter extends BaseConverter implements AttributeConverter<Syllabic, String> {
    public SyllabicConverter() {

    }

    @Override
    public String convertToDatabaseColumn(Syllabic enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public Syllabic convertToEntityAttribute(String value) {
        return (Syllabic) dbValueToEnum(value, Syllabic.class);
    }
}
