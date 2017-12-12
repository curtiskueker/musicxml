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
            case "change":
                return Connection.CHANGE;
            case "continue":
                return Connection.CONTINUE;
            case "discontinue":
                return Connection.DISCONTINUE;
            case "single":
                return Connection.SINGLE;
            case "begin":
                return Connection.BEGIN;
            case "end":
                return Connection.END;
            case "middle":
                return Connection.MIDDLE;
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
            case "over":
                return Location.OVER;
            case "under":
                return Location.UNDER;
            case "top":
                return Location.TOP;
            case "middle":
                return Location.MIDDLE;
            case "bottom":
                return Location.BOTTOM;
            case "baseline":
                return Location.BASELINE;
            case "up":
                return Location.UP;
            case "down":
                return Location.DOWN;
            case "ltr":
                return Location.LTR;
            case "rtl":
                return Location.RTL;
            case "lro":
                return Location.LRO;
            case "rlo":
                return Location.RLO;
            default:
                return null;
        }
    }
}
