package org.curtis.musicxml.converter;

import org.curtis.musicxml.direction.harmony.DegreeSymbol;

import javax.persistence.AttributeConverter;

public class DegreeSymbolConverter extends BaseConverter implements AttributeConverter<DegreeSymbol, String> {
    public DegreeSymbolConverter() {

    }

    @Override
    public String convertToDatabaseColumn(DegreeSymbol enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public DegreeSymbol convertToEntityAttribute(String value) {
        return (DegreeSymbol) dbValueToEnum(value, DegreeSymbol.class);
    }
}
