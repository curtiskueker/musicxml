package org.curtis.musicxml.note;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("notehead_accidental_text")
public class NoteheadAccidentalText extends NoteheadText {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "text_id")
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
