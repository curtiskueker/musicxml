package org.curtis.musicxml.builder.util;

import org.curtis.musicxml.common.Location;

public class BuildUtil {
    private BuildUtil() {

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
}
