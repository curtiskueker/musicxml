package org.curtis.musicxml.converter;

import org.curtis.musicxml.common.SymbolSize;

import javax.persistence.AttributeConverter;

public class SymbolSizeConverter extends BaseConverter implements AttributeConverter<SymbolSize, String> {
    public SymbolSizeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(SymbolSize enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public SymbolSize convertToEntityAttribute(String value) {
        return (SymbolSize) dbValueToEnum(value, SymbolSize.class);
    }
}
