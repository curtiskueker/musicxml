package org.curtis.musicxml.converter;

import org.curtis.musicxml.direction.MeasureNumberingType;

import javax.persistence.AttributeConverter;

public class MeasureNumberingTypeConverter extends BaseConverter implements AttributeConverter<MeasureNumberingType, String> {
    public MeasureNumberingTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(MeasureNumberingType enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public MeasureNumberingType convertToEntityAttribute(String value) {
        return (MeasureNumberingType) dbValueToEnum(value, MeasureNumberingType.class);
    }
}
