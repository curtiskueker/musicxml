package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.direction.EditorialVoiceDirection;
import org.curtis.musicxml.direction.DirectionOffset;
import org.curtis.musicxml.direction.directiontype.DirectionType;
import org.curtis.musicxml.direction.directiontype.DirectionTypeList;

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
                buildStartElement("direction-type");
                append(directionTypeStringBuilder.toString());
                buildEndElement("direction-type");
            }
        }
        if (!hasDirectionTypeContent) {
            stringBuilder = new StringBuilder();
            return stringBuilder;
        }

        append(buildOffset(direction.getOffset()));
        EditorialVoiceDirection editorialVoiceDirection = direction.getEditorialVoiceDirection();
        if (editorialVoiceDirection != null) {
            buildFormattedText("footnote", editorialVoiceDirection.getFootnote());
            buildLevel(editorialVoiceDirection.getLevel());
            buildElementWithValue("voice", editorialVoiceDirection.getVoice());
        }
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
