package org.curtis.musicxml.converter;

import org.curtis.musicxml.link.Actuate;

import javax.persistence.AttributeConverter;

public class ActuateConverter extends BaseConverter implements AttributeConverter<Actuate, String> {
    public ActuateConverter() {

    }

    @Override
    public String convertToDatabaseColumn(Actuate enumValue) {
        if (enumValue == null) return null;

        switch (enumValue) {
            case ON_REQUEST:
                return "onRequest";
            case ON_LOAD:
                return "onLoad";
        }

        return enumToDbValue(enumValue);
    }

    @Override
    public Actuate convertToEntityAttribute(String value) {
        if (value == null) return null;

        switch (value) {
            case "onRequest":
                return Actuate.ON_REQUEST;
            case "onLoad":
                return Actuate.ON_LOAD;
        }

        return (Actuate) dbValueToEnum(value, Actuate.class);
    }
}
