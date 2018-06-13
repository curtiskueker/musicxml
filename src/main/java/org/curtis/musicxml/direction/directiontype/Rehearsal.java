package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.common.FormattedText;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("rehearsal")
public class Rehearsal extends DirectionType {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fomatted_text_id")
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
