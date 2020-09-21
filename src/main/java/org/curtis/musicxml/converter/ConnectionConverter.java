package org.curtis.musicxml.converter;

import org.curtis.musicxml.common.Connection;

import javax.persistence.AttributeConverter;

public class ConnectionConverter extends BaseConverter implements AttributeConverter<Connection, String> {
    public ConnectionConverter() {

    }

    @Override
    public String convertToDatabaseColumn(Connection enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public Connection convertToEntityAttribute(String value) {
        return (Connection) dbValueToEnum(value, Connection.class);
    }
}
