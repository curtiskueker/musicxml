package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.lilypond.util.PlacementBuildUtil;
import org.curtis.lilypond.util.TypeUtil;
import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.direction.Print;
import org.curtis.musicxml.direction.Sound;
import org.curtis.musicxml.direction.directiontype.DirectionType;
import org.curtis.musicxml.direction.directiontype.DirectionTypeList;
import org.curtis.musicxml.direction.directiontype.Words;
import org.curtis.musicxml.display.Placement;
import org.curtis.musicxml.factory.DisplayFactory;

import static org.curtis.musicxml.util.MusicXmlUtil.INCLUDE_BREAKS;

public class DirectionBuilder extends MusicDataBuilder {
    public DirectionBuilder() {

    }

    public StringBuilder buildDirection(Direction direction) throws BuildException {
        for (DirectionTypeList directionTypeList : direction.getDirectionTypeLists()) {
            for(DirectionType directionType : directionTypeList.getDirectionTypes()) {
                MusicDataBuilder musicDataBuilder = new MusicDataBuilder(directionType);
                String musicDataResults = musicDataBuilder.build().toString();
                if(!musicDataResults.isEmpty()) {
                    if (!TypeUtil.getBoolean(direction.getDirective())) append(PlacementBuildUtil.getPlacement(direction.getDisplay(), directionType.getClass().getSimpleName()));
                    append(musicDataResults);
                }
            }
        }

        return stringBuilder;
    }

    public StringBuilder buildPrint(Print print) {
        if (!INCLUDE_BREAKS) return stringBuilder;

        if(TypeUtil.getBoolean(print.getNewSystem())) {
            appendLine("\\break");
        } else if(TypeUtil.getBoolean(print.getNewPage())) {
            appendLine("\\pageBreak");
        }

        return stringBuilder;
    }

    public StringBuilder buildSound(Sound sound) {
        // do nothing
        return stringBuilder;
    }

    public static void setDirectionDefaults(Direction direction) {
        direction.getDirectionTypeLists().stream().flatMap(typeList -> typeList.getDirectionTypes().stream())
                .filter(directionType -> directionType instanceof Words && !TypeUtil.getBoolean(direction.getDirective()) && direction.getPlacement() == null)
                .findAny().ifPresent(words -> DisplayFactory.setDisplayPlacement(direction.getDisplay(), Placement.ABOVE));

    }
}
