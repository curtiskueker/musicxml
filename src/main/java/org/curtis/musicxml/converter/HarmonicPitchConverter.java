package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.notation.technical.HarmonicPitch;

import javax.persistence.AttributeConverter;

public class HarmonicPitchConverter extends BaseConverter implements AttributeConverter<HarmonicPitch, String> {
    public HarmonicPitchConverter() {

    }

    @Override
    public String convertToDatabaseColumn(HarmonicPitch enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public HarmonicPitch convertToEntityAttribute(String value) {
        return (HarmonicPitch) dbValueToEnum(value, HarmonicPitch.class);
    }
}
