package org.curtis.musicxml.converter;

import org.curtis.musicxml.attributes.time.TimeRelation;

import javax.persistence.AttributeConverter;

public class TimeRelationConverter extends BaseConverter implements AttributeConverter<TimeRelation, String> {
    public TimeRelationConverter() {

    }

    @Override
    public String convertToDatabaseColumn(TimeRelation enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public TimeRelation convertToEntityAttribute(String value) {
        return (TimeRelation) dbValueToEnum(value, TimeRelation.class);
    }
}
