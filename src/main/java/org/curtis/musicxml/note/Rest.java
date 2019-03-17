package org.curtis.musicxml.note;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("rest")
public class Rest extends FullNoteType {
    @Enumerated(EnumType.STRING)
    @Column(name = "display_step")
    private Step displayStep;
    @Column(name = "display_octave")
    private Integer displayOctave;
    @Column
    @Type(type="yes_no")
    private Boolean measure;

    public Rest() {

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

    public Boolean getMeasure() {
        return measure;
    }

    public void setMeasure(Boolean measure) {
        this.measure = measure;
    }
}
