package org.curtis.musicxml.util;

import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.common.Location;

public class EnumUtil {
    private EnumUtil() {

    }

    public static Connection getConnection(String type) {
        switch (type) {
            case "start":
                return Connection.START;
            case "stop":
                return Connection.STOP;
            case "continue":
                return Connection.CONTINUE;
            default:
                return null;
        }

    }

    public static Location getLocation(String value) {
        switch (value) {
            case "above":
                return Location.ABOVE;
            case "below":
                return Location.BELOW;
            default:
                return null;
        }
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
