package org.curtis.musicxml.converter;

import org.curtis.musicxml.display.EnclosureShape;

import javax.persistence.AttributeConverter;

public class EnclosureShapeConverter extends BaseConverter implements AttributeConverter<EnclosureShape, String> {
    public EnclosureShapeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(EnclosureShape enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public EnclosureShape convertToEntityAttribute(String value) {
        return (EnclosureShape) dbValueToEnum(value, EnclosureShape.class);
    }
}
