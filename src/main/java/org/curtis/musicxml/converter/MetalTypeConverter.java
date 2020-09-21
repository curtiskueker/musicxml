package org.curtis.musicxml.converter;

import org.curtis.musicxml.direction.directiontype.percussion.MetalType;

import javax.persistence.AttributeConverter;

public class MetalTypeConverter extends BaseConverter implements AttributeConverter<MetalType, String> {
    public MetalTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(MetalType enumValue) {
        if (enumValue == null) return null;

        switch (enumValue) {
            case BELL_PLATE:
            case BELL_TREE:
            case BRAKE_DRUM:
            case CHAIN_RATTLE:
            case CRASH_CYMBALS:
            case CYMBAL_TONGS:
            case DOMED_GONG:
            case FINGER_CYMBALS:
            case JAW_HARP:
            case JINGLE_BELLS:
            case MUSICAL_SAW:
            case SHELL_BELLS:
            case SIZZLE_CYMBAL:
            case SLEIGH_BELLS:
            case SUSPENDED_CYMBAL:
            case TAM_TAM:
            case TAM_TAM_WITH_BEATER:
                return enumValue.toString().toLowerCase().replace("_", " ");
            case CHINESE_CYMBAL:
            case VIETNAMESE_HAT:
                String dbValue = enumValue.toString();
                return dbValue.substring(0, 1) + dbValue.substring(1).toLowerCase().replace("_", " ");
            case HI_HAT_CYMBALS:
                return "high-hat cymbals";
        }

        return enumToDbValue(enumValue);
    }

    @Override
    public MetalType convertToEntityAttribute(String value) {
        return (MetalType) dbValueToEnum(value, MetalType.class);
    }
}
