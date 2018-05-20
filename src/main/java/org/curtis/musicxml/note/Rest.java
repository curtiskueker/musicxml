package org.curtis.musicxml.note;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("rest")
public class Rest extends FullNoteType {
    @Transient
    private Step displayStep;
    @Transient
    private Integer displayOctave;
    @Column
    private Boolean measure = false;

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
