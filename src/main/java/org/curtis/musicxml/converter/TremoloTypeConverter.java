package org.curtis.musicxml.converter;

import org.curtis.musicxml.note.notation.ornament.TremoloType;

import javax.persistence.AttributeConverter;

public class TremoloTypeConverter extends BaseConverter implements AttributeConverter<TremoloType, String> {
    public TremoloTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(TremoloType enumValue) {
        return enumToDbValue(enumValue);
    }

    @Override
    public TremoloType convertToEntityAttribute(String value) {
        return (TremoloType) dbValueToEnum(value, TremoloType.class);
    }
}
