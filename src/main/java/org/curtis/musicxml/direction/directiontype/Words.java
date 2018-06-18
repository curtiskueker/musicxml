package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.common.FormattedText;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("words")
public class Words extends DirectionType {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fomatted_text_id")
    private FormattedText formattedText;
    @Transient
    // transient lilypond
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
