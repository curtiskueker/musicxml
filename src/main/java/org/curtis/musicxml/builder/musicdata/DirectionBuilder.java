package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.direction.Offset;
import org.curtis.musicxml.direction.directiontype.DirectionType;

public class DirectionBuilder extends BaseBuilder {
    private Direction direction;

    public DirectionBuilder() {}

    public DirectionBuilder(Direction direction) {
        this.direction = direction;
    }

    public StringBuilder build() {
        if (direction == null) return stringBuilder;

        appendLine("<direction>");
        for (DirectionType directionType : direction.getDirectionTypes()) {
            appendLine("<direction-type>");
            DirectionTypeBuilder directionTypeBuilder = new DirectionTypeBuilder(directionType);
            append(directionTypeBuilder.build().toString());
            appendLine("</direction-type>");
        }
        DirectionBuilder directionBuilder = new DirectionBuilder();
        append(directionBuilder.buildOffset(direction.getOffset()).toString());
        SoundBuilder soundBuilder = new SoundBuilder(direction.getSound());
        append(soundBuilder.build().toString());
        appendLine("</direction>");

        return stringBuilder;
    }

    public StringBuilder buildOffset(Offset offset) {
        if (offset == null) return stringBuilder;

        clear();
        buildElementWithValue("offset", 0);

        return stringBuilder;
    }
}
