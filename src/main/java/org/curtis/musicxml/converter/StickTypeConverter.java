package org.curtis.musicxml.converter;

import org.curtis.musicxml.direction.directiontype.percussion.StickType;

import javax.persistence.AttributeConverter;

public class StickTypeConverter extends BaseConverter implements AttributeConverter<StickType, String> {
    public StickTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(StickType enumValue) {
        if (enumValue == null) return null;

        switch (enumValue) {
            case BASS_DRUM:
            case DOUBLE_BASS_DRUM:
                return enumValue.toString().toLowerCase().replace("_", " ");
        }

        return enumToDbValue(enumValue);
    }

    @Override
    public StickType convertToEntityAttribute(String value) {
        return (StickType) dbValueToEnum(value, StickType.class);
    }
}
