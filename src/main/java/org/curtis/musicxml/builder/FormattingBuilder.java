package org.curtis.musicxml.builder;

import org.curtis.musicxml.common.Font;

public class FormattingBuilder extends OutputBuilder {
    public FormattingBuilder() {

    }

    public String buildFont(String elementName, Font font) {
        clear();
        if (font == null) return "";

        buildElement(elementName);

        return stringBuilder.toString();
    }
}
