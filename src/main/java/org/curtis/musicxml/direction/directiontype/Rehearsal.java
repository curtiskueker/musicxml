package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.common.FormattedText;

public class Rehearsal extends DirectionType {
    private FormattedText formattedText;

    public Rehearsal() {

    }

    public FormattedText getFormattedText() {
        return formattedText;
    }

    public void setFormattedText(FormattedText formattedText) {
        this.formattedText = formattedText;
    }
}
