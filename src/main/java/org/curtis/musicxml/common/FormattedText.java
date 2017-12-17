package org.curtis.musicxml.common;

import org.apache.commons.text.StringEscapeUtils;

public class FormattedText {
    private String value;
    private TextFormatting textFormatting;

    public FormattedText() {

    }

    public String getValue() {
        return StringEscapeUtils.unescapeXml(value);
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TextFormatting getTextFormatting() {
        return textFormatting;
    }

    public void setTextFormatting(TextFormatting textFormatting) {
        this.textFormatting = textFormatting;
    }
}
