package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.BeamFan;

import javax.persistence.AttributeConverter;

public class BeamFanConverter extends BaseConverter implements AttributeConverter<BeamFan, String> {
    public BeamFanConverter() {

    }

    @Override
    public String convertToDatabaseColumn(BeamFan enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public BeamFan convertToEntityAttribute(String value) {
        return (BeamFan) dbValueToEnum(value, BeamFan.class);
    }
}
