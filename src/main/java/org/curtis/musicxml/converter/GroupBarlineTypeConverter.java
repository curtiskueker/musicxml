package org.curtis.musicxml.converter;

import org.curtis.musicxml.score.GroupBarlineType;

import javax.persistence.AttributeConverter;

public class GroupBarlineTypeConverter extends BaseConverter implements AttributeConverter<GroupBarlineType, String> {
    public GroupBarlineTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(GroupBarlineType enumValue) {
        if (enumValue == null) return null;
        if (enumValue == GroupBarlineType.MENSURSTRICH) return "Mensurstrich";

        return enumToDbValue(enumValue);
    }

    @Override
    public GroupBarlineType convertToEntityAttribute(String value) {
        return (GroupBarlineType) dbValueToEnum(value, GroupBarlineType.class);
    }
}
