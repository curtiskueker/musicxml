package org.curtis.musicxml.builder;

import org.curtis.musicxml.display.Display;
import org.curtis.musicxml.display.Font;

import java.util.HashMap;
import java.util.Map;

public class DisplayBuilder {
    private DisplayBuilder() {

    }

    public static Map<String, String> buildDisplay(Display display) {
        Map<String, String> attributes = new HashMap<>();
        if (display == null) return attributes;

        attributes.put("default-x", BuilderUtil.stringValue(display.getDefaultX()));
        attributes.put("default-y", BuilderUtil.stringValue(display.getDefaultY()));
        attributes.put("relative-x", BuilderUtil.stringValue(display.getRelativeX()));
        attributes.put("relative-y", BuilderUtil.stringValue(display.getRelativeY()));
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
        attributes.put("font-size", font.getFontSize());
        attributes.put("font-weight", BuilderUtil.enumValue(font.getFontWeight()));

        return attributes;
    }
}
