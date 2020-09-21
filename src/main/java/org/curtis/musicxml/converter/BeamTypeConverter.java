package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.BeamType;

import javax.persistence.AttributeConverter;

public class BeamTypeConverter extends BaseConverter implements AttributeConverter<BeamType, String> {
    public BeamTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(BeamType enumValue) {
        if (enumValue == null) return null;

        switch (enumValue) {
            case FORWARD_HOOK:
            case BACKWARD_HOOK:
                return enumValue.toString().toLowerCase().replace("_", " ");
        }

        return enumToDbValue(enumValue);
    }

    @Override
    public BeamType convertToEntityAttribute(String value) {
        return (BeamType) dbValueToEnum(value, BeamType.class);
    }
}
