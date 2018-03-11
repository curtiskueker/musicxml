package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.common.FormattedText;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("words")
public class Words extends DirectionType {
    @Transient
    private FormattedText formattedText;
    @Transient
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
