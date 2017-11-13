package org.curtis.musicxml.handler.util;

import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.common.Location;

public class HandlerUtil {
    private HandlerUtil() {

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
            case "left":
                return Location.LEFT;
            case "center":
                return Location.CENTER;
            case "right":
                return Location.RIGHT;
            case "top":
                return Location.TOP;
            case "middle":
                return Location.MIDDLE;
            case "bottom":
                return Location.BOTTOM;
            case "baseline":
                return Location.BASELINE;
            default:
                return null;
        }
    }
}
