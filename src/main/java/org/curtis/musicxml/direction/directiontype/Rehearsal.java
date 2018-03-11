package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.common.FormattedText;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("rehearsal")
public class Rehearsal extends DirectionType {
    @Transient
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
