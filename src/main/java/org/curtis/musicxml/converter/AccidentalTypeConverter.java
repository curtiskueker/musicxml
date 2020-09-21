package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.AccidentalType;

import javax.persistence.AttributeConverter;

public class AccidentalTypeConverter extends BaseConverter implements AttributeConverter<AccidentalType, String> {
    public AccidentalTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(AccidentalType enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public AccidentalType convertToEntityAttribute(String value) {
        return (AccidentalType) dbValueToEnum(value, AccidentalType.class);
    }
}
