package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.Step;

import javax.persistence.AttributeConverter;

public class StepConverter extends BaseConverter implements AttributeConverter<Step, String> {
    public StepConverter() {

    }

    @Override
    public String convertToDatabaseColumn(Step enumValue) {
        if (enumValue == null) return null;

        return enumValue.toString();
    }

    @Override
    public Step convertToEntityAttribute(String value) {
        return (Step) dbValueToEnum(value, Step.class);
    }
}
