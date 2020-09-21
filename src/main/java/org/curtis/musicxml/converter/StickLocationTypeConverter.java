package org.curtis.musicxml.converter;

import org.curtis.musicxml.direction.directiontype.percussion.StickLocationType;

import javax.persistence.AttributeConverter;

public class StickLocationTypeConverter extends BaseConverter implements AttributeConverter<StickLocationType, String> {
    public StickLocationTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(StickLocationType enumValue) {
        if (enumValue == null) return null;

        switch (enumValue) {
            case CYMBAL_BELL:
            case CYMBAL_EDGE:
                return enumValue.toString().toLowerCase().replace("_", " ");
        }

        return enumToDbValue(enumValue);
    }

    @Override
    public StickLocationType convertToEntityAttribute(String value) {
        return (StickLocationType) dbValueToEnum(value, StickLocationType.class);
    }
}
