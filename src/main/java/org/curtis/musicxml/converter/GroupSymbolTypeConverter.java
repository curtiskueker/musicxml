package org.curtis.musicxml.converter;

import org.curtis.musicxml.score.GroupSymbolType;

import javax.persistence.AttributeConverter;

public class GroupSymbolTypeConverter extends BaseConverter implements AttributeConverter<GroupSymbolType, String> {
    public GroupSymbolTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(GroupSymbolType enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public GroupSymbolType convertToEntityAttribute(String value) {
        return (GroupSymbolType) dbValueToEnum(value, GroupSymbolType.class);
    }
}
