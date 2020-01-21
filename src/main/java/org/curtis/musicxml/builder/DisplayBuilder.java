package org.curtis.musicxml.builder;

import org.curtis.musicxml.display.Display;
import org.curtis.musicxml.display.Font;
import org.curtis.musicxml.display.Position;

import java.util.HashMap;
import java.util.Map;

public class DisplayBuilder {
    private DisplayBuilder() {

    }

    public static Map<String, String> buildDisplay(Display display) {
        Map<String, String> attributes = new HashMap<>();
        if (display == null) return attributes;

        Position position = display.getPosition();
        if (position != null) {
            attributes.put("default-x", BuilderUtil.stringValue(position.getDefaultX()));
            attributes.put("default-y", BuilderUtil.stringValue(position.getDefaultY()));
            attributes.put("relative-x", BuilderUtil.stringValue(position.getRelativeX()));
            attributes.put("relative-y", BuilderUtil.stringValue(position.getRelativeY()));
        }

        attributes.put("placement", BuilderUtil.enumValue(display.getPlacement()));
        attributes.putAll(buildFont(display.getFont()));
        attributes.put("color", display.getColor());
        attributes.put("halign", BuilderUtil.enumValue(display.getHalign()));
        attributes.put("valign", BuilderUtil.enumValue(display.getValign()));

        return attributes;
    }

    public static Map<String, String> buildFont(Font font) {
        Map<String, String> attributes = new HashMap<>();
        if (font == null) return attributes;

        attributes.put("font-family", font.getFontFamily());
        attributes.put("font-style", BuilderUtil.enumValue(font.getFontStyle()));
        attributes.put("font-size", BuilderUtil.enumValue(font.getFontSize()));
        attributes.put("font-weight", BuilderUtil.enumValue(font.getFontWeight()));

        return attributes;
    }
}
