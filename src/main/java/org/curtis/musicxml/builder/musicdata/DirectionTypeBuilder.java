package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.direction.directiontype.DirectionType;

public class DirectionTypeBuilder extends BaseBuilder {
    private DirectionType directionType;

    public DirectionTypeBuilder(DirectionType directionType) {
        this.directionType = directionType;
    }

    public StringBuilder build() {
        if (directionType == null) return stringBuilder;

        return stringBuilder;
    }
}
