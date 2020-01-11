package org.curtis.musicxml.builder;

import org.curtis.musicxml.common.Position;

import java.util.HashMap;
import java.util.Map;

public class PlacementBuilder extends OutputBuilder {
    private PlacementBuilder() {

    }

    public static Map<String, String> buildPosition(Position position) {
        Map<String, String> attributes = new HashMap<>();
        if (position == null) return attributes;

        attributes.put("default-x", BuilderUtil.stringValue(position.getDefaultX()));
        attributes.put("default-y", BuilderUtil.stringValue(position.getDefaultY()));
        attributes.put("relative-x", BuilderUtil.stringValue(position.getRelativeX()));
        attributes.put("relative-y", BuilderUtil.stringValue(position.getRelativeY()));

        return attributes;
    }
}
