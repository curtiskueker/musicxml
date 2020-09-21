package org.curtis.musicxml.converter;

import org.curtis.musicxml.direction.directiontype.percussion.WoodType;

import javax.persistence.AttributeConverter;

public class WoodTypeConverter extends BaseConverter implements AttributeConverter<WoodType, String> {
    public WoodTypeConverter() {

    }

    @Override
    public String convertToDatabaseColumn(WoodType enumValue) {
        if (enumValue == null) return null;

        switch (enumValue) {
            case BAMBOO_SCRAPER:
            case BOARD_CLAPPER:
            case CASTANETS_WITH_HANDLE:
            case FOOTBALL_RATTLE:
            case LOG_DRUM:
            case SANDPAPER_BLOCKS:
            case SLIT_DRUM:
            case TEMPLE_BLOCK:
            case WOOD_BLOCK:
                return enumValue.toString().toLowerCase().replace("_", " ");
            case RECO_RECO:
                return "reco-reco";
        }

        return enumToDbValue(enumValue);
    }

    @Override
    public WoodType convertToEntityAttribute(String value) {
        return (WoodType) dbValueToEnum(value, WoodType.class);
    }
}
