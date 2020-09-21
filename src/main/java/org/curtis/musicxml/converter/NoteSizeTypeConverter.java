package org.curtis.musicxml.converter;

import org.curtis.musicxml.layout.NoteSizeType;

import javax.persistence.AttributeConverter;

public class NoteSizeTypeConverter extends BaseConverter implements AttributeConverter<NoteSizeType, String> {
    public NoteSizeTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(NoteSizeType enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public NoteSizeType convertToEntityAttribute(String value) {
        return (NoteSizeType) dbValueToEnum(value, NoteSizeType.class);
    }
}
