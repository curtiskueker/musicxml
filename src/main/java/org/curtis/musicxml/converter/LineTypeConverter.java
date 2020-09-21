package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.LineType;

import javax.persistence.AttributeConverter;

public class LineTypeConverter extends BaseConverter implements AttributeConverter<LineType, String> {
    public LineTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(LineType enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public LineType convertToEntityAttribute(String value) {
        return (LineType) dbValueToEnum(value, LineType.class);
    }
}
