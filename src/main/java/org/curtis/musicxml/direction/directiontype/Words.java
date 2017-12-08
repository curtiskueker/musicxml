package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.common.FormattedText;

public class Words extends DirectionType {
    private FormattedText formattedText;

    public Words() {

    }

    public FormattedText getFormattedText() {
        return formattedText;
    }

    public void setFormattedText(FormattedText formattedText) {
        this.formattedText = formattedText;
    }
}
