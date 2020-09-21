package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.notation.articulation.CaesuraValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class CaesuraValueConverter extends BaseConverter implements AttributeConverter<CaesuraValue, String> {
    public CaesuraValueConverter() {

    }

    @Override
    public String convertToDatabaseColumn(CaesuraValue enumValue) {
        if (enumValue == null) return null;
        if (enumValue == CaesuraValue.EMPTY_VALUE) return "";

        return enumToDbValue(enumValue);
    }

    @Override
    public CaesuraValue convertToEntityAttribute(String value) {
        if (value == null) return null;
        if (value.equals("")) return CaesuraValue.EMPTY_VALUE;

        return (CaesuraValue)dbValueToEnum(value, CaesuraValue.class);
    }
}
