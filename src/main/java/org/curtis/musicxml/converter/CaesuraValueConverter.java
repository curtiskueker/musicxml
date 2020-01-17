package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.notation.articulation.CaesuraValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class CaesuraValueConverter extends EmptyValueConverter implements AttributeConverter<CaesuraValue, String> {
    public CaesuraValueConverter() {

    }

    @Override
    public String convertToDatabaseColumn(CaesuraValue caesuraValue) {
        return enumToDbValue(caesuraValue);
    }

    @Override
    public CaesuraValue convertToEntityAttribute(String value) {
        return (CaesuraValue)dbValueToEnum(value, CaesuraValue.class);
    }
}
