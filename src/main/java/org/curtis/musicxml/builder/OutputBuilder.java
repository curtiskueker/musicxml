package org.curtis.musicxml.builder;

import org.curtis.util.StringUtil;

import java.util.Map;

public abstract class OutputBuilder {
    protected StringBuilder stringBuilder = new StringBuilder();


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

    protected void buildElenent(String elementName) {
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

    protected void buildElementWithAttributes(String elementName, Map<String, String> attributes) {
        if (attributes == null || attributes.isEmpty()) return;

        append("<");
        append(elementName);
        for (String attributeName : attributes.keySet()) {
            buildAttribute(attributeName, attributes.get(attributeName));
        }
        appendLine("/>");
    }
}
