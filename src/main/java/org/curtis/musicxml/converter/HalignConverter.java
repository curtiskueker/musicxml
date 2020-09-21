package org.curtis.musicxml.converter;

import org.curtis.musicxml.display.Halign;

import javax.persistence.AttributeConverter;

public class HalignConverter extends BaseConverter implements AttributeConverter<Halign, String> {
    public HalignConverter() {

    }

    @Override
    public String convertToDatabaseColumn(Halign enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public Halign convertToEntityAttribute(String value) {
        return (Halign) dbValueToEnum(value, Halign.class);
    }
}
