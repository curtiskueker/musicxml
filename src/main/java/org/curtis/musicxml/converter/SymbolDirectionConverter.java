package org.curtis.musicxml.converter;

import org.curtis.musicxml.display.SymbolDirection;

import javax.persistence.AttributeConverter;

public class SymbolDirectionConverter extends BaseConverter implements AttributeConverter<SymbolDirection, String> {
    public SymbolDirectionConverter() {

    }

    @Override
    public String convertToDatabaseColumn(SymbolDirection enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public SymbolDirection convertToEntityAttribute(String value) {
        return (SymbolDirection) dbValueToEnum(value, SymbolDirection.class);
    }
}
