package org.curtis.musicxml.converter;

import org.curtis.musicxml.link.LinkType;

import javax.persistence.AttributeConverter;

public class LinkTypeConverter extends BaseConverter implements AttributeConverter<LinkType, String> {
    public LinkTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(LinkType enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public LinkType convertToEntityAttribute(String value) {
        return (LinkType) dbValueToEnum(value, LinkType.class);
    }
}
