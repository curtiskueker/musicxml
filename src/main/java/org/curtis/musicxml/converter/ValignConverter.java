package org.curtis.musicxml.converter;

import org.curtis.musicxml.display.Valign;

import javax.persistence.AttributeConverter;

public class ValignConverter extends BaseConverter implements AttributeConverter<Valign, String> {
    public ValignConverter() {

    }

    @Override
    public String convertToDatabaseColumn(Valign enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public Valign convertToEntityAttribute(String value) {
        return (Valign) dbValueToEnum(value, Valign.class);
    }
}
