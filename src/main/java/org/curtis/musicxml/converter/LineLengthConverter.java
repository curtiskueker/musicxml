package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.LineLength;

import javax.persistence.AttributeConverter;

public class LineLengthConverter extends BaseConverter implements AttributeConverter<LineLength, String> {
    public LineLengthConverter() {

    }

    @Override
    public String convertToDatabaseColumn(LineLength enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public LineLength convertToEntityAttribute(String value) {
        return (LineLength) dbValueToEnum(value, LineLength.class);
    }
}
