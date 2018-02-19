package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.lilypond.util.PlacementBuildUtil;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.direction.Print;
import org.curtis.musicxml.direction.directiontype.DirectionType;
import org.curtis.musicxml.direction.directiontype.Words;

import java.util.List;

import static org.curtis.musicxml.handler.ScoreHandler.DEBUG;

public class DirectionBuilder extends MusicDataBuilder {
    public DirectionBuilder() {

    }

    public StringBuilder buildDirection(Direction direction) throws BuildException {
        List<DirectionType> directionTypes = direction.getDirectionTypes();
        for(DirectionType directionType : directionTypes) {
            MusicDataBuilder musicDataBuilder = new MusicDataBuilder(directionType);
            String musicDataResults = musicDataBuilder.build().toString();
            if(!musicDataResults.isEmpty()) {
                if (!direction.getDirective()) append(PlacementBuildUtil.getPlacement(direction.getPlacement(), directionType.getClass().getSimpleName()));
                append(musicDataResults);
            }
        }

        return stringBuilder;
    }

    public StringBuilder buildPrint(Print print) {
        if (DEBUG) return stringBuilder;

        if(print.getNewSystem()) {
            appendLine("\\break");
        } else if(print.getNewPage()) {
            appendLine("\\pageBreak");
        }

        return stringBuilder;
    }

    public static void setDirectionDefaults(Direction direction) {
        DirectionType words = direction.getDirectionTypes().stream()
                .filter(directionType -> directionType instanceof Words && !direction.getDirective() && direction.getPlacement() == null)
                .findAny().orElse(null);

        if (words != null) direction.setPlacement(Location.ABOVE);
    }
}
