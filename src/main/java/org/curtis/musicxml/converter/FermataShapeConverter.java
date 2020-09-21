package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.notation.FermataShape;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class FermataShapeConverter extends BaseConverter implements AttributeConverter<FermataShape, String> {
    public FermataShapeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(FermataShape enumValue) {
        if (enumValue == null) return null;
        if (enumValue == FermataShape.EMPTY_VALUE) return "";

        return enumToDbValue(enumValue);
    }

    @Override
    public FermataShape convertToEntityAttribute(String value) {
        if (value == null) return null;
        if (value.equals("")) return FermataShape.EMPTY_VALUE;

        return (FermataShape) dbValueToEnum(value, FermataShape.class);
    }
}
