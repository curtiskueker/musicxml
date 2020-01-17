package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.notation.FermataShape;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class FermataShapeConverter extends EmptyValueConverter implements AttributeConverter<FermataShape, String> {
    public FermataShapeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(FermataShape fermataShape) {
        return enumToDbValue(fermataShape);
    }

    @Override
    public FermataShape convertToEntityAttribute(String value) {
        return (FermataShape) dbValueToEnum(value, FermataShape.class);
    }
}
