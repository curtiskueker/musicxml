package org.curtis.musicxml.note;

import org.curtis.musicxml.converter.StepConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("unpitched")
public class Unpitched extends NoteType {
    @Convert(converter = StepConverter.class)
    @Column(name = "step")
    private Step step;
    @Column(name = "octave")
    private Integer octave;

    public Unpitched() {

    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    public Integer getOctave() {
        return octave;
    }

    public void setOctave(Integer octave) {
        this.octave = octave;
    }
}
