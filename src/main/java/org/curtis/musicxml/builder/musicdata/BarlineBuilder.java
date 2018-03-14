package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.barline.BarStyleColor;
import org.curtis.musicxml.barline.Barline;
import org.curtis.musicxml.barline.Ending;
import org.curtis.musicxml.barline.Repeat;
import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.builder.util.BuilderUtil;

import java.util.HashMap;
import java.util.Map;

public class BarlineBuilder extends BaseBuilder {
    private Barline barline;

    public BarlineBuilder(Barline barline) {
        this.barline = barline;
    }

    public StringBuilder build() {
        if (barline == null) return stringBuilder;

        append("<barline");
        buildAttribute("location", BuilderUtil.enumValue(barline.getLocation()));
        buildAttribute("segno", barline.getSegno());
        buildAttribute("coda", barline.getCoda());
        appendLine(">");
        BarStyleColor barStyleColor = barline.getBarStyle();
        if (barStyleColor != null) {
            buildElementWithValue("bar-style", BuilderUtil.enumValue(barStyleColor.getBarStyle()));
        }
        Ending ending = barline.getEnding();
        if (ending != null) {
            Map<String, String> attributes = new HashMap<>();
            attributes.put("number", ending.getNumber());
            attributes.put("type", BuilderUtil.enumValue(ending.getType()));
            buildElementWithValueAndAttributes("ending", ending.getValue(), attributes);
        }
        Repeat repeat = barline.getRepeat();
        if (repeat != null) {
            Map<String, String> attributes = new HashMap<>();
            attributes.put("direction", BuilderUtil.enumValue(repeat.getDirection()));
            attributes.put("times", BuilderUtil.stringValue(repeat.getTimes()));
            attributes.put("winged", BuilderUtil.enumValue(repeat.getWinged()));
            buildElementWithAttributes("repeat", attributes);
        }
        appendLine("</barline>");

        return stringBuilder;
    }
}