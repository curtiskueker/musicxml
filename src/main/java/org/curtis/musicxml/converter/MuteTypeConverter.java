package org.curtis.musicxml.converter;

import org.curtis.musicxml.common.play.MuteType;

import javax.persistence.AttributeConverter;

public class MuteTypeConverter extends BaseConverter implements AttributeConverter<MuteType, String> {
    public MuteTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(MuteType enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public MuteType convertToEntityAttribute(String value) {
        return (MuteType) dbValueToEnum(value, MuteType.class);
    }
}
