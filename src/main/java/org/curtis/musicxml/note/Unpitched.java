package org.curtis.musicxml.note;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("unpitched")
public class Unpitched extends FullNoteType {
    @Enumerated(EnumType.STRING)
    @Column(name = "display_step")
    private Step displayStep;
    @Column(name = "display_octave")
    private Integer displayOctave;

    public Unpitched() {

    }

    public Step getDisplayStep() {
        return displayStep;
    }

    public void setDisplayStep(Step displayStep) {
        this.displayStep = displayStep;
    }

    public Integer getDisplayOctave() {
        return displayOctave;
    }

    public void setDisplayOctave(Integer displayOctave) {
        this.displayOctave = displayOctave;
    }
}
