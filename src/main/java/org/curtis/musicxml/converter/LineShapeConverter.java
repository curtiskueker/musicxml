package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.LineShape;

import javax.persistence.AttributeConverter;

public class LineShapeConverter extends BaseConverter implements AttributeConverter<LineShape, String> {
    public LineShapeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(LineShape enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public LineShape convertToEntityAttribute(String value) {
        return (LineShape) dbValueToEnum(value, LineShape.class);
    }
}
