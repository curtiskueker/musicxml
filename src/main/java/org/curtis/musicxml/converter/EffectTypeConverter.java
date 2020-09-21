package org.curtis.musicxml.converter;

import org.curtis.musicxml.direction.directiontype.percussion.EffectType;

import javax.persistence.AttributeConverter;

public class EffectTypeConverter extends BaseConverter implements AttributeConverter<EffectType, String> {
    public EffectTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(EffectType enumValue) {
        if (enumValue == null) return null;

        switch (enumValue) {
            case AUTO_HORN:
            case BIRD_WHISTLE:
            case DUCK_CALL:
            case GUN_SHOT:
            case KLAXON_HORN:
            case LIONS_ROAR:
            case LOTUS_FLUTE:
            case POLICE_WHISTLE:
            case SLIDE_WHISTLE:
            case THUNDER_SHEET:
            case WIND_MACHINE:
            case WIND_WHISTLE:
                return enumValue.toString().toLowerCase().replace("_", " ");
        }

        return enumToDbValue(enumValue);
    }

    @Override
    public EffectType convertToEntityAttribute(String value) {
        return (EffectType) dbValueToEnum(value, EffectType.class);
    }
}
