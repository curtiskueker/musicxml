package org.curtis.musicxml.converter;

import org.curtis.musicxml.direction.directiontype.percussion.GlassType;

import javax.persistence.AttributeConverter;

public class GlassTypeConverter extends BaseConverter implements AttributeConverter<GlassType, String> {
    public GlassTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(GlassType enumValue) {
        if (enumValue == null) return null;

        return enumValue.toString().toLowerCase().replace("_", " ");
    }

    @Override
    public GlassType convertToEntityAttribute(String value) {
        return (GlassType) dbValueToEnum(value, GlassType.class);
    }
}
