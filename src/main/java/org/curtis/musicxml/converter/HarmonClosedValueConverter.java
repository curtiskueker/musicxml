package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.notation.technical.HarmonClosedValue;

import javax.persistence.AttributeConverter;

public class HarmonClosedValueConverter extends BaseConverter implements AttributeConverter<HarmonClosedValue, String> {
    public HarmonClosedValueConverter() {

    }

    @Override
    public String convertToDatabaseColumn(HarmonClosedValue enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public HarmonClosedValue convertToEntityAttribute(String value) {
        return (HarmonClosedValue) dbValueToEnum(value, HarmonClosedValue.class);
    }
}
