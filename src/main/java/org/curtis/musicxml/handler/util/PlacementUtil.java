package org.curtis.musicxml.handler.util;

import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.common.Location;
import org.curtis.util.StringUtil;

public class PlacementUtil {
    private PlacementUtil() {

    }

    public static Connection getConnection(String type) {
        if(StringUtil.isEmpty(type)) {
            return null;
        }

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
        if(StringUtil.isEmpty(value)) {
            return null;
        }

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
