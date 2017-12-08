package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.util.PlacementBuildUtil;
import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.direction.Print;
import org.curtis.musicxml.direction.directiontype.DirectionType;

import java.util.List;

public class DirectionBuilder extends MusicDataBuilder {
    public DirectionBuilder() {

    }

    public StringBuilder buildDirection(Direction direction) {
        List<DirectionType> directionTypes = direction.getDirectionTypes();
        for(DirectionType directionType : directionTypes) {
            MusicDataBuilder musicDataBuilder = new MusicDataBuilder(directionType);
            String musicDataResults = musicDataBuilder.build().toString();
            if(!musicDataResults.isEmpty()) {
                append(PlacementBuildUtil.getPlacement(direction.getPlacement()));
                append(musicDataResults);
            }
        }

        return stringBuilder;
    }

    public StringBuilder buildPrint(Print print) {
        if(print.getNewSystem()) {
            appendLine("\\break");
        } else if(print.getNewPage()) {
            appendLine("\\pageBreak");
        }

        return stringBuilder;
    }
}
