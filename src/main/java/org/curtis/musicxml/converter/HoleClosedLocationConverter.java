package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.notation.technical.HoleClosedLocation;

import javax.persistence.AttributeConverter;

public class HoleClosedLocationConverter extends BaseConverter implements AttributeConverter<HoleClosedLocation, String> {
    public HoleClosedLocationConverter() {

    }

    @Override
    public String convertToDatabaseColumn(HoleClosedLocation enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public HoleClosedLocation convertToEntityAttribute(String value) {
        return (HoleClosedLocation) dbValueToEnum(value, HoleClosedLocation.class);
    }
}
