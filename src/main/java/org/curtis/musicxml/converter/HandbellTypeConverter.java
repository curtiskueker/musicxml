package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.notation.technical.HandbellType;

import javax.persistence.AttributeConverter;

public class HandbellTypeConverter extends BaseConverter implements AttributeConverter<HandbellType, String> {
    public HandbellTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(HandbellType enumValue) {
        if (enumValue == null) return null;

        switch (enumValue) {
            case HAND_MARTELLATO:
            case MALLET_LIFT:
            case MALLET_TABLE:
            case MARTELLATO_LIFT:
            case MUTED_MARTELLATO:
            case PLUCK_LIFT:
                return enumValue.toString().toLowerCase().replace("_", " ");
        }

        return enumToDbValue(enumValue);
    }

    @Override
    public HandbellType convertToEntityAttribute(String value) {
        return (HandbellType) dbValueToEnum(value, HandbellType.class);
    }
}
