package org.curtis.musicxml.converter;

import org.curtis.musicxml.attributes.ShowFrets;

import javax.persistence.AttributeConverter;

public class ShowFretsConverter extends BaseConverter implements AttributeConverter<ShowFrets, String> {
    public ShowFretsConverter() {

    }

    @Override
    public String convertToDatabaseColumn(ShowFrets enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public ShowFrets convertToEntityAttribute(String value) {
        return (ShowFrets)dbValueToEnum(value, ShowFrets.class);
    }
}
