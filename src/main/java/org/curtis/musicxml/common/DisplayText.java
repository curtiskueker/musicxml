package org.curtis.musicxml.common;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("display_text")
public class DisplayText extends TextDisplay {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_text_id")
    private FormattedText displayText;

    public DisplayText() {

    }

    public FormattedText getDisplayText() {
        return displayText;
    }

    public void setDisplayText(FormattedText displayText) {
        this.displayText = displayText;
    }
}
