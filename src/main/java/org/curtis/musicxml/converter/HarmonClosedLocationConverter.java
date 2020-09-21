package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.notation.technical.HarmonClosedLocation;

import javax.persistence.AttributeConverter;

public class HarmonClosedLocationConverter extends BaseConverter implements AttributeConverter<HarmonClosedLocation, String> {
    public HarmonClosedLocationConverter() {

    }

    @Override
    public String convertToDatabaseColumn(HarmonClosedLocation enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public HarmonClosedLocation convertToEntityAttribute(String value) {
        return (HarmonClosedLocation) dbValueToEnum(value, HarmonClosedLocation.class);
    }
}
