package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.notation.articulation.BreathMarkType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class BreathMarkTypeConverter extends BaseConverter implements AttributeConverter<BreathMarkType, String> {
    public BreathMarkTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(BreathMarkType enumValue) {
        if (enumValue == null) return null;
        if (enumValue == BreathMarkType.EMPTY_VALUE) return "";

        return enumToDbValue(enumValue);
    }

    @Override
    public BreathMarkType convertToEntityAttribute(String value) {
        if (value == null) return null;
        if (value.equals("")) return BreathMarkType.EMPTY_VALUE;

        return (BreathMarkType) dbValueToEnum(value, BreathMarkType.class);
    }
}
