package org.curtis.musicxml.converter;

import org.curtis.musicxml.direction.directiontype.percussion.MembraneType;

import javax.persistence.AttributeConverter;

public class MembraneTypeConverter extends BaseConverter implements AttributeConverter<MembraneType, String> {
    public MembraneTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(MembraneType enumValue) {
        if (enumValue == null) return null;

        switch (enumValue) {
            case BASS_DRUM:
            case BASS_DRUM_ON_SIDE:
            case CONGA_DRUM:
            case GOBLET_DRUM:
            case MILITARY_DRUM:
            case SNARE_DRUM:
            case SHARE_DRUM_SNARES_OFF:
            case TENOR_DRUM:
                return enumValue.toString().toLowerCase().replace("_", " ");
            case CHINESE_TOMTOM:
            case JAPANESE_TOMTOM:
                String dbValue = enumValue.toString();
                return dbValue.substring(0, 1) + dbValue.substring(1).toLowerCase().replace("_", " ");

            case INDO_AMERICAN_TOMTOM:
                return "Indo-American tomtom";
        }

        return enumToDbValue(enumValue);
    }

    @Override
    public MembraneType convertToEntityAttribute(String value) {
        return (MembraneType) dbValueToEnum(value, MembraneType.class);
    }
}
