package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.NoteheadType;

import javax.persistence.AttributeConverter;

public class NoteheadTypeConverter extends BaseConverter implements AttributeConverter<NoteheadType, String> {
    public NoteheadTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(NoteheadType enumValue) {
        if (enumValue == null) return null;

        switch (enumValue) {
            case INVERTED_TRIANGLE:
            case ARROW_DOWN:
            case ARROW_UP:
            case BACK_SLASHED:
            case CIRCLE_DOT:
            case LEFT_TRIANGLE:
            case FA_UP:
                return enumValue.toString().toLowerCase().replace("_", " ");
        }

        return enumToDbValue(enumValue);
    }

    @Override
    public NoteheadType convertToEntityAttribute(String value) {
        return (NoteheadType) dbValueToEnum(value, NoteheadType.class);
    }
}
