package org.curtis.musicxml.converter;

import org.curtis.musicxml.barline.Winged;

import javax.persistence.AttributeConverter;

public class WingedConverter extends BaseConverter implements AttributeConverter<Winged, String> {
    public WingedConverter() {

    }

    @Override
    public String convertToDatabaseColumn(Winged enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public Winged convertToEntityAttribute(String value) {
        return (Winged) dbValueToEnum(value, Winged.class);
    }
}
