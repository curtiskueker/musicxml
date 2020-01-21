package org.curtis.lilypond.util;

import org.curtis.musicxml.display.Display;
import org.curtis.musicxml.display.Placement;
import org.curtis.util.StringUtil;

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

    public static String getPlacement(Display display) {
        if (display == null) return "";
        Placement placement = display.getPlacement();
        if(placement == null) return "";

        switch (placement) {
            case ABOVE:
                return "^";
            case BELOW:
                return "_";
            default:
                return "";
        }
    }

    public static String getPlacementDefaultAbove(Display display) {
        String placementValue = getPlacement(display);
        return StringUtil.isEmpty(placementValue) ? "^" : placementValue;
    }

    // Skips placement of objects where placement is ignored in DirectionBuilder
    public static String getPlacement(Display display, String className) {
        if(NON_PLACED_OBJECTS.contains(className)) return "";

        return getPlacement(display);
    }
}
