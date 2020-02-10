package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.display.TextFormat;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("rehearsal")
public class Rehearsal extends DirectionType {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "text_format_id")
    private TextFormat textFormat;

    public Rehearsal() {

    }

    public TextFormat getTextFormat() {
        return textFormat;
    }

    public void setTextFormat(TextFormat textFormat) {
        this.textFormat = textFormat;
    }
}
