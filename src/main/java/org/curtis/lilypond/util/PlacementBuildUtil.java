package org.curtis.lilypond.util;

import org.curtis.musicxml.common.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlacementBuildUtil {
    private static List<String> NON_PLACED_OBJECTS = new ArrayList<>(
            Arrays.asList(
                    "BeatMetronome"
            )
    );

    private PlacementBuildUtil() {

    }

    public static String getPlacement(Location placement) {
        if(placement == null) {
            return "";
        }

        switch (placement) {
            case ABOVE:
                return "^";
            case BELOW:
                return "_";
            default:
                return "";
        }
    }

    // Skips placement of objects where placement is ignored in DirectionBuilder
    public static String getPlacement(Location placement, String className) {
        if(NON_PLACED_OBJECTS.contains(className)) return "";

        return getPlacement(placement);
    }
}
