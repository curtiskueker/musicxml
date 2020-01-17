package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.notation.articulation.BreathMarkType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class BreathMarkTypeConverter extends EmptyValueConverter implements AttributeConverter<BreathMarkType, String> {
    public BreathMarkTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(BreathMarkType breathMarkType) {
        return enumToDbValue(breathMarkType);
    }

    @Override
    public BreathMarkType convertToEntityAttribute(String value) {
        return (BreathMarkType) dbValueToEnum(value, BreathMarkType.class);
    }
}
