package org.curtis.musicxml.builder;

import org.curtis.musicxml.common.Font;

public class FormattingBuilder extends OutputBuilder {
    public FormattingBuilder() {

    }

    public String buildFont(String elementName, Font font) {
        if (font == null) return "";
        clear();

        buildElement(elementName);

        return stringBuilder.toString();
    }
}
