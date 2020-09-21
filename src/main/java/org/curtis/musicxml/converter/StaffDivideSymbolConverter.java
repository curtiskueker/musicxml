package org.curtis.musicxml.converter;

import org.curtis.musicxml.direction.directiontype.StaffDivideSymbol;

import javax.persistence.AttributeConverter;

public class StaffDivideSymbolConverter extends BaseConverter implements AttributeConverter<StaffDivideSymbol, String> {
    public StaffDivideSymbolConverter() {

    }

    @Override
    public String convertToDatabaseColumn(StaffDivideSymbol enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public StaffDivideSymbol convertToEntityAttribute(String value) {
        return (StaffDivideSymbol) dbValueToEnum(value, StaffDivideSymbol.class);
    }
}
