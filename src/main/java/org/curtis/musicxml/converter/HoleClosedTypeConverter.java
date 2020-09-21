package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.notation.technical.HoleClosedType;

import javax.persistence.AttributeConverter;

public class HoleClosedTypeConverter extends BaseConverter implements AttributeConverter<HoleClosedType, String> {
    public HoleClosedTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(HoleClosedType enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public HoleClosedType convertToEntityAttribute(String value) {
        return (HoleClosedType) dbValueToEnum(value, HoleClosedType.class);
    }
}
