package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.direction.EditorialVoiceDirection;
import org.curtis.musicxml.direction.Offset;
import org.curtis.musicxml.direction.directiontype.DirectionType;

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
        for (DirectionType directionType : direction.getDirectionTypes()) {
            buildStartElement("direction-type");
            DirectionTypeBuilder directionTypeBuilder = new DirectionTypeBuilder(directionType);
            append(directionTypeBuilder.build().toString());
            buildEndElement("direction-type");
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

    public static String buildOffset(Offset offset) {
        if (offset == null) return "";

        DirectionBuilder directionBuilder = new DirectionBuilder();
        directionBuilder.buildElementWithValueAndAttribute("offset", offset.getDivisions(), "sound", offset.getSound());

        return directionBuilder.stringBuilder.toString();
    }
}
