package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.notation.technical.HarmonicType;

import javax.persistence.AttributeConverter;

public class HarmonicTypeConverter extends BaseConverter implements AttributeConverter<HarmonicType, String> {
    public HarmonicTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(HarmonicType enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public HarmonicType convertToEntityAttribute(String value) {
        return (HarmonicType) dbValueToEnum(value, HarmonicType.class);
    }
}
