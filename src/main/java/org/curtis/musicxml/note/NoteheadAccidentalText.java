package org.curtis.musicxml.note;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("notehead_accidental_text")
public class NoteheadAccidentalText extends NoteheadText {
    @Transient
    private AccidentalText text;

    public NoteheadAccidentalText() {

    }

    public AccidentalText getText() {
        return text;
    }

    public void setText(AccidentalText text) {
        this.text = text;
    }
}
