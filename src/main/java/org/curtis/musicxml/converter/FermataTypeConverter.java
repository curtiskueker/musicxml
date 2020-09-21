package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.notation.FermataType;

import javax.persistence.AttributeConverter;

public class FermataTypeConverter extends BaseConverter implements AttributeConverter<FermataType, String> {
    public FermataTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(FermataType enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public FermataType convertToEntityAttribute(String value) {
        return (FermataType) dbValueToEnum(value, FermataType.class);
    }
}
