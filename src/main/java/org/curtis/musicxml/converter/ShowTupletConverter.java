package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.notation.ShowTuplet;

import javax.persistence.AttributeConverter;

public class ShowTupletConverter extends BaseConverter implements AttributeConverter<ShowTuplet, String> {
    public ShowTupletConverter() {

    }

    @Override
    public String convertToDatabaseColumn(ShowTuplet enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public ShowTuplet convertToEntityAttribute(String value) {
        return (ShowTuplet) dbValueToEnum(value, ShowTuplet.class);
    }
}
