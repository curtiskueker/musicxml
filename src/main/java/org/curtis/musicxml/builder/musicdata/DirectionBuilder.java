package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.direction.DirectionOffset;
import org.curtis.musicxml.direction.directiontype.DirectionType;
import org.curtis.musicxml.direction.directiontype.DirectionTypeList;
import org.curtis.musicxml.display.Editorial;

import java.util.List;

public class DirectionBuilder extends MusicDataBuilder {
    private Direction direction;

    public DirectionBuilder() {}

    public DirectionBuilder(Direction direction) {
        this.direction = direction;
    }

    public StringBuilder build() {
        if (direction == null) return stringBuilder;

        buildOpenElement("direction");
        buildAttribute("id", direction.getElementId());
        buildAttribute("placement", direction.getPlacement());
        buildAttribute("directive", direction.getDirective());
        buildCloseElement();

        // direction must have direction-type content
        boolean hasDirectionTypeContent = false;
        for (DirectionTypeList directionTypeList : direction.getDirectionTypeLists()) {
            List<DirectionType> directionTypes = directionTypeList.getDirectionTypes();
            if (directionTypes.isEmpty()) continue;

            StringBuilder directionTypeStringBuilder = new StringBuilder();
            for (DirectionType directionType : directionTypes) {
                DirectionTypeBuilder directionTypeBuilder = new DirectionTypeBuilder(directionType);
                directionTypeStringBuilder.append(directionTypeBuilder.build().toString());
            }
            if (directionTypeStringBuilder.length() > 0) {
                hasDirectionTypeContent = true;
                buildOpenElement("direction-type");
                buildAttribute("id", directionTypeList.getElementId());
                buildCloseElement();
                append(directionTypeStringBuilder.toString());
                buildEndElement("direction-type");
            }
        }
        if (!hasDirectionTypeContent) {
            stringBuilder = new StringBuilder();
            return stringBuilder;
        }

        append(buildOffset(direction.getOffset()));
        Editorial editorial = direction.getEditorial();
        if (editorial != null) {
            buildFormattedDisplay("footnote", editorial.getFootnote());
            buildLevel(editorial.getLevel());
        }
        buildElementWithValue("voice", direction.getVoice());
        buildElementWithValue("staff", direction.getStaff());
        SoundBuilder soundBuilder = new SoundBuilder(direction.getSound());
        append(soundBuilder.build().toString());
        buildEndElement("direction");

        return stringBuilder;
    }

    public static String buildOffset(DirectionOffset offset) {
        if (offset == null) return "";

        DirectionBuilder directionBuilder = new DirectionBuilder();
        directionBuilder.buildElementWithValueAndAttribute("offset", offset.getDivisions(), "sound", offset.getSound());

        return directionBuilder.stringBuilder.toString();
    }
}
