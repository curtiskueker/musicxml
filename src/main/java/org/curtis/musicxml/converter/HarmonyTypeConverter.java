package org.curtis.musicxml.converter;

import org.curtis.musicxml.direction.harmony.HarmonyType;

import javax.persistence.AttributeConverter;

public class HarmonyTypeConverter extends BaseConverter implements AttributeConverter<HarmonyType, String> {
    public HarmonyTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(HarmonyType enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public HarmonyType convertToEntityAttribute(String value) {
        return (HarmonyType) dbValueToEnum(value, HarmonyType.class);
    }
}
