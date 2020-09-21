package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.StemType;

import javax.persistence.AttributeConverter;

public class StemTypeConverter extends BaseConverter implements AttributeConverter<StemType, String> {
    public StemTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(StemType enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public StemType convertToEntityAttribute(String value) {
        return (StemType) dbValueToEnum(value, StemType.class);
    }
}
