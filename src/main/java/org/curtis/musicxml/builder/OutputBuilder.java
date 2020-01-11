package org.curtis.musicxml.builder;

import org.apache.commons.text.StringEscapeUtils;
import org.curtis.util.StringUtil;

import java.math.BigDecimal;
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

    protected void buildAttribute(String attributeName, String attributeValue) {
        if (StringUtil.isEmpty(attributeValue)) return;

        if (attributeValue.equals(BuilderUtil.REQUIRED_ATTRIBUTE)) attributeValue = "";

        append(" ");
        append(attributeName);
        append("=\"");
        append(attributeValue);
        append("\"");
    }

    protected void buildAttribute(String attributeName, Integer attributeValue) {
        buildAttribute(attributeName, BuilderUtil.stringValue(attributeValue));
    }

    protected void buildAttribute(String attributeName, BigDecimal attributeValue) {
        buildAttribute(attributeName, BuilderUtil.stringValue(attributeValue));
    }

    protected void buildAttribute(String attributeName, Boolean attributeValue) {
        buildAttribute(attributeName, BuilderUtil.yesOrNo(attributeValue));
    }

    protected void buildAttribute(String attributeName, Enum attributeValue) {
        buildAttribute(attributeName, BuilderUtil.enumValue(attributeValue));
    }

    protected void buildAttributes(Map<String, String> attributes) {
        if (attributes == null || attributes.isEmpty()) return;

        attributes.forEach(this::buildAttribute);
    }

    protected void buildElement(String elementName) {
        buildOpenElement(elementName);
        buildCloseEmptyElement();
    }

    protected void buildElement(Enum elementName) {
        buildElement(BuilderUtil.enumValue(elementName));
    }

    protected void buildOpenElement(String elementName) {
        append("<");
        append(elementName);
    }

    protected void buildCloseElement() {
        appendLine(">");
    }

    protected void buildCloseEmptyElement() {
        appendLine("/>");
    }

    protected void buildStartElement(String elementName) {
        append("<");
        append(elementName);
        buildCloseElement();
    }

    protected void buildEndElement(String elementName) {
        append("</");
        append(elementName);
        buildCloseElement();
    }

    protected void buildElementValue(String elementValue) {
        if (StringUtil.isEmpty(elementValue)) return;

        append(StringEscapeUtils.escapeXml11(elementValue));
    }

    protected void buildElementWithValue(String elementName, String elementValue) {
        if (StringUtil.isEmpty(elementValue)) return;

        buildOpenElement(elementName);
        append(">");
        buildElementValue(elementValue);
        buildEndElement(elementName);
    }

    protected void buildElementWithValue(String elementName, Integer elementValue) {
        buildElementWithValue(elementName, BuilderUtil.stringValue(elementValue));
    }

    protected void buildElementWithValue(String elementName, BigDecimal elementValue) {
        buildElementWithValue(elementName, BuilderUtil.stringValue(elementValue));
    }

    protected void buildElementWithValue(String elementName, Enum elementValue) {
        buildElementWithValue(elementName, BuilderUtil.enumValue(elementValue));
    }

    protected void buildElementWithOptionalValue(String elementName, String elementValue) {
        if (StringUtil.isEmpty(elementValue)) buildElement(elementName);
        else buildElementWithValue(elementName, elementValue);
    }

    protected void buildElementWithOptionalValue(String elementName, Integer elementValue) {
        if (elementValue == null) buildElement(elementName);
        else buildElementWithValue(elementName, BuilderUtil.stringValue(elementValue));
    }

    protected void buildElementWithAttribute(String elementName, String attributeName, String attributeValue) {
        if (StringUtil.isEmpty(attributeValue)) return;

        buildOpenElement(elementName);
        buildAttribute(attributeName, attributeValue);
        buildCloseEmptyElement();
    }

    protected void buildElementWithAttributes(String elementName, Map<String, String> attributes) {
        if (attributes == null || attributes.isEmpty()) return;

        buildElementWithOptionalAttributes(elementName, attributes);
    }

    protected void buildElementWithOptionalAttributes(String elementName, Map<String, String> attributes) {
        buildOpenElement(elementName);

        if (attributes != null) {
            for (String attributeName : attributes.keySet()) {
                buildAttribute(attributeName, attributes.get(attributeName));
            }
        }

        buildCloseEmptyElement();
    }

    protected void buildElementWithValueAndAttribute(String elementName, String elementValue, String attributeName, String attributeValue) {
        if (StringUtil.isEmpty(elementValue) && StringUtil.isEmpty(attributeValue)) return;

        buildOpenElement(elementName);
        buildAttribute(attributeName, attributeValue);
        append(">");
        buildElementValue(elementValue);
        buildEndElement(elementName);
    }

    protected void buildElementWithValueAndAttribute(String elementName, String elementValue, String attributeName, Integer attributeValue) {
        buildElementWithValueAndAttribute(elementName, elementValue, attributeName, BuilderUtil.stringValue(attributeValue));
    }

    protected void buildElementWithValueAndAttribute(String elementName, String elementValue, String attributeName, Enum attributeValue) {
        buildElementWithValueAndAttribute(elementName, elementValue, attributeName, BuilderUtil.enumValue(attributeValue));
    }

    protected void buildElementWithValueAndAttribute(String elementName, Integer elementValue, String attributeName, Boolean attributeValue) {
        buildElementWithValueAndAttribute(elementName, BuilderUtil.stringValue(elementValue), attributeName, BuilderUtil.yesOrNo(attributeValue));
    }

    protected void buildElementWithValueAndAttribute(String elementName, BigDecimal elementValue, String attributeName, String attributeValue) {
        buildElementWithValueAndAttribute(elementName, BuilderUtil.stringValue(elementValue), attributeName, attributeValue);
    }

    protected void buildElementWithValueAndAttribute(String elementName, BigDecimal elementValue, String attributeName, Boolean attributeValue) {
        buildElementWithValueAndAttribute(elementName, BuilderUtil.stringValue(elementValue), attributeName, BuilderUtil.yesOrNo(attributeValue));
    }

    protected void buildElementWithValueAndAttribute(String elementName, Integer elementValue, String attributeName, Enum attributeValue) {
        buildElementWithValueAndAttribute(elementName, BuilderUtil.stringValue(elementValue), attributeName, BuilderUtil.enumValue(attributeValue));
    }

    protected void buildElementWithValueAndAttribute(String elementName, Enum elementValue, String attributeName, String attributeValue) {
        buildElementWithValueAndAttribute(elementName, BuilderUtil.enumValue(elementValue), attributeName, attributeValue);
    }

    protected void buildElementWithValueAndAttribute(String elementName, Enum elementValue, String attributeName, Enum attributeValue) {
        buildElementWithValueAndAttribute(elementName, BuilderUtil.enumValue(elementValue), attributeName, BuilderUtil.enumValue(attributeValue));
    }

    protected void buildElementWithValueAndAttributes(String elementName, String elementValue, Map<String, String> attributes) {
        if ((attributes == null || attributes.isEmpty()) && StringUtil.isEmpty(elementValue)) {
            buildElement(elementName);
            return;
        }

        buildOpenElement(elementName);
        if (attributes != null && !attributes.isEmpty()) {
            for (String attributeName : attributes.keySet()) {
                buildAttribute(attributeName, attributes.get(attributeName));
            }
        }
        append(">");
        buildElementValue(elementValue);
        buildEndElement(elementName);
    }

    protected void buildElementWithValueAndAttributes(String elementName, Integer elementValue, Map<String, String> attributes) {
        buildElementWithValueAndAttributes(elementName, BuilderUtil.stringValue(elementValue), attributes);
    }

    protected void buildElementWithValueAndAttributes(String elementName, BigDecimal elementValue, Map<String, String> attributes) {
        buildElementWithValueAndAttributes(elementName, BuilderUtil.stringValue(elementValue), attributes);
    }

    protected void buildElementWithValueAndAttributes(String elementName, Enum elementValue, Map<String, String> attributes) {
        buildElementWithValueAndAttributes(elementName, BuilderUtil.enumValue(elementValue), attributes);
    }
}
