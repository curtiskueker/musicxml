package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.notation.ornament.TrillStep;

import javax.persistence.AttributeConverter;

public class TrillStepConverter extends BaseConverter implements AttributeConverter<TrillStep, String> {
    public TrillStepConverter() {

    }

    @Override
    public String convertToDatabaseColumn(TrillStep enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public TrillStep convertToEntityAttribute(String value) {
        return (TrillStep) dbValueToEnum(value, TrillStep.class);
    }
}
