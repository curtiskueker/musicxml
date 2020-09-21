package org.curtis.musicxml.converter;

import org.curtis.musicxml.direction.directiontype.percussion.PitchedType;

import javax.persistence.AttributeConverter;

public class PitchedTypeConverter extends BaseConverter implements AttributeConverter<PitchedType, String> {
    public PitchedTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(PitchedType enumValue) {
        if (enumValue == null) return null;

        switch (enumValue) {
            case STEEL_DRUMS:
            case TUBULAR_CHIMES:
                return enumValue.toString().replace("_", " ");
        }

        return enumToDbValue(enumValue);
    }

    @Override
    public PitchedType convertToEntityAttribute(String value) {
        return (PitchedType) dbValueToEnum(value, PitchedType.class);
    }
}
