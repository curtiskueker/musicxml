package org.curtis.musicxml.converter;

import org.curtis.musicxml.direction.harmony.KindValue;

import javax.persistence.AttributeConverter;

public class KindValueConverter extends BaseConverter implements AttributeConverter<KindValue, String> {
    public KindValueConverter() {

    }

    @Override
    public String convertToDatabaseColumn(KindValue enumValue) {
        if (enumValue == null) return null;

        switch (enumValue) {
            case NEAPOLITAN:
            case ITALIAN:
            case FRENCH:
            case GERMAN:
            case TRISTAN:
                String dbValue = enumValue.toString();
                return dbValue.substring(0, 1) + dbValue.substring(1).toLowerCase();
        }

        return enumToDbValue(enumValue);
    }

    @Override
    public KindValue convertToEntityAttribute(String value) {
        return (KindValue) dbValueToEnum(value, KindValue.class);
    }
}
