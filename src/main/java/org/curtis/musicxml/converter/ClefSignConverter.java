package org.curtis.musicxml.converter;

import org.curtis.musicxml.attributes.ClefSign;

import javax.persistence.AttributeConverter;

public class ClefSignConverter extends BaseConverter implements AttributeConverter<ClefSign, String> {
    public ClefSignConverter() {

    }


    @Override
    public String convertToDatabaseColumn(ClefSign enumValue) {
        if (enumValue == null) return null;

        switch (enumValue) {
            case G:
            case C:
            case F:
            case TAB:
                return enumValue.toString();
        }

        return enumToDbValue(enumValue);
    }

    @Override
    public ClefSign convertToEntityAttribute(String value) {
        return (ClefSign)dbValueToEnum(value, ClefSign.class);
    }
}
