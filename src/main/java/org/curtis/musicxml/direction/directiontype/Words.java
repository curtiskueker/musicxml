package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.common.FormattedText;

public class Words extends DirectionType {
    private FormattedText formattedText;
    private boolean textMark = false;

    public Words() {

    }

    public FormattedText getFormattedText() {
        return formattedText;
    }

    public void setFormattedText(FormattedText formattedText) {
        this.formattedText = formattedText;
    }

    public boolean isTextMark() {
        return textMark;
    }

    public void setTextMark(boolean textMark) {
        this.textMark = textMark;
    }
}
