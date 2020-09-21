package org.curtis.musicxml.converter;

import org.curtis.musicxml.direction.directiontype.PrincipalVoiceSymbol;

import javax.persistence.AttributeConverter;

public class PrincipalVoiceSymbolConverter extends BaseConverter implements AttributeConverter<PrincipalVoiceSymbol, String> {
    public PrincipalVoiceSymbolConverter() {

    }

    @Override
    public String convertToDatabaseColumn(PrincipalVoiceSymbol enumValue) {
        if (enumValue == null) return null;

        switch (enumValue) {
            case HAUPTSTIMME:
            case NEBENSTIMME:
                String dbValue = enumValue.toString();
                return dbValue.substring(0, 1) + dbValue.substring(1).toLowerCase();
        }

        return enumToDbValue(enumValue);
    }

    @Override
    public PrincipalVoiceSymbol convertToEntityAttribute(String value) {
        return (PrincipalVoiceSymbol) dbValueToEnum(value, PrincipalVoiceSymbol.class);
    }
}
