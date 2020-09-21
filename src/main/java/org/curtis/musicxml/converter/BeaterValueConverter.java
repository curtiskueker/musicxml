package org.curtis.musicxml.converter;

import org.curtis.musicxml.direction.directiontype.percussion.BeaterValue;

import javax.persistence.AttributeConverter;

public class BeaterValueConverter extends BaseConverter implements AttributeConverter<BeaterValue, String> {
    public BeaterValueConverter() {

    }

    @Override
    public String convertToDatabaseColumn(BeaterValue enumValue) {
        if (enumValue == null) return null;

        switch (enumValue) {
            case CHIME_HAMMER:
            case DRUM_STICK:
            case GUIRO_SCRAPER:
            case JAZZ_STICK:
            case KNITTING_NEEDLE:
            case METAL_HAMMER:
            case SLIDE_BRUSH_ON_GONG:
            case SNARE_STICK:
            case SPOON_MALLET:
            case TRIANGLE_BEATER:
            case TRIANGLE_BEATER_PLAIN:
            case WIRE_BRUSH:
                return enumValue.toString().toLowerCase().replace("_", " ");
        }

        return enumToDbValue(enumValue);
    }

    @Override
    public BeaterValue convertToEntityAttribute(String value) {
        return (BeaterValue) dbValueToEnum(value, BeaterValue.class);
    }
}
