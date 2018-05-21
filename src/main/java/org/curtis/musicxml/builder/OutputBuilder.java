package org.curtis.musicxml.builder;

import org.curtis.musicxml.builder.util.BuilderUtil;
import org.curtis.musicxml.note.Line;
import org.curtis.musicxml.note.Placement;
import org.curtis.musicxml.note.PlacementText;
import org.curtis.musicxml.note.TimeModification;
import org.curtis.musicxml.note.lyric.Extend;
import org.curtis.util.StringUtil;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public abstract class OutputBuilder {
    protected StringBuilder stringBuilder = new StringBuilder();

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    protected void appendLine(String string) {
        stringBuilder.append(string);
        stringBuilder.append("\n");
    }

    protected void appendLine() {
        appendLine("");
    }

    protected void append(String string) {
        stringBuilder.append(string);
    }

    protected void clear() {
        stringBuilder = new StringBuilder();
    }

    protected void buildAttribute(String attributeName, String attributeValue) {
        if (StringUtil.isEmpty(attributeValue)) return;

        append(" ");
        append(attributeName);
        append("=\"");
        append(attributeValue);
        append("\"");
    }

    protected void buildAttribute(String attributeName, Integer attributeValue) {
        if (attributeValue == null) return;

        buildAttribute(attributeName, String.valueOf(attributeValue));
    }

    protected void buildElement(String elementName) {
        append("<");
        append(elementName);
        appendLine("/>");
    }

    protected void buildElementWithValue(String elementName, String elementValue) {
        if (StringUtil.isEmpty(elementValue)) return;

        append("<");
        append(elementName);
        append(">");
        append(elementValue);
        append("</");
        append(elementName);
        appendLine(">");
    }

    protected void buildElementWithValue(String elementName, Integer elementValue) {
        if (elementValue == null) return;

        buildElementWithValue(elementName, String.valueOf(elementValue));
    }

    protected void buildElementWithValue(String elementName, BigDecimal elementValue) {
        buildElementWithValue(elementName, elementValue.toString());
    }

    protected void buildElementWithAttribute(String elementName, String attributeName, String attributeValue) {
        if (StringUtil.isEmpty(attributeValue)) return;

        append("<");
        append(elementName);
        buildAttribute(attributeName, attributeValue);
        appendLine("/>");
    }

    protected void buildElementWithAttribute(String elementName, String attributeName, Integer attributeValue) {
        if (attributeValue == null) return;

        buildElementWithAttribute(elementName, attributeName, String.valueOf(attributeValue));
    }

    protected void buildElementWithAttributes(String elementName, Map<String, String> attributes) {
        if (attributes == null || attributes.isEmpty()) return;

        append("<");
        append(elementName);
        for (String attributeName : attributes.keySet()) {
            buildAttribute(attributeName, attributes.get(attributeName));
        }
        appendLine("/>");
    }

    protected void buildElementWithValueAndAttribute(String elementName, String elementValue, String attributeName, String attributeValue) {
        if (StringUtil.isEmpty(elementValue)) return;

        append("<");
        append(elementName);
        buildAttribute(attributeName, attributeValue);
        append(">");
        append(elementValue);
        append("</");
        append(elementName);
        appendLine(">");
    }

    protected void buildElementWithValueAndAttribute(String elementName, String elementValue, String attributeName, Integer attributeValue) {
        buildElementWithValueAndAttribute(elementName, elementValue, attributeName, BuilderUtil.stringValue(attributeValue));
    }


    protected void buildElementWithValueAndAttribute(String elementName, Integer elementValue, String attributeName, String attributeValue) {
        if (elementValue == null) return;

        buildElementWithValueAndAttribute(elementName, String.valueOf(elementValue), attributeName, attributeValue);
    }

    protected void buildElementWithValueAndAttributes(String elementName, String elementValue, Map<String, String> attributes) {
        append("<");
        append(elementName);
        if (attributes != null && !attributes.isEmpty()) {
            for (String attributeName : attributes.keySet()) {
                buildAttribute(attributeName, attributes.get(attributeName));
            }
        }
        append(">");
        if (StringUtil.isNotEmpty(elementValue)) append(elementValue);
        append("</");
        append(elementName);
        appendLine(">");
    }

    protected void buildElementWithValueAndAttributes(String elementName, Integer elementValue, Map<String, String> attributes) {
        buildElementWithValueAndAttributes(elementName, BuilderUtil.stringValue(elementValue), attributes);
    }

    protected void buildTimeModification(TimeModification timeModification) {
        if (timeModification == null) return;

        buildElementWithValue("actual-notes", timeModification.getActualNotes());
        buildElementWithValue("normal-notes", timeModification.getNormalNotes());
        buildElementWithValue("normal-type", BuilderUtil.noteTypeValue(timeModification.getNormalType()));
    }

    protected void buildExtend(Extend extend) {
        if (extend == null) return;

        buildElementWithAttribute("extend", "type", BuilderUtil.enumValue(extend.getType()));
    }

    protected void buildPlacement(String elementName, Placement placement) {
        buildPlacementWithAttribute(elementName, placement, "", "");
    }

    protected void buildPlacementWithAttribute(String elementName, Placement placement, String attributeName, String attributeValue) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("placement", BuilderUtil.enumValue(placement.getPlacement()));
        if (StringUtil.isNotEmpty(attributeValue)) attributes.put(attributeName, attributeValue);
        buildElementWithAttributes(elementName, attributes);
    }

    protected void buildPlacementText(String elementName, PlacementText placementText) {
        buildElementWithValueAndAttribute(elementName, placementText.getValue(), "placement", BuilderUtil.enumValue(placementText.getPlacement()));
    }

    protected void buildLine(String elementName, Line line) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("line-shape", BuilderUtil.enumValue(line.getLineShape()));
        attributes.put("placement", BuilderUtil.enumValue(line.getPlacement()));
        buildElementWithAttributes(elementName, attributes);
    }
}
