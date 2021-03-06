package org.curtis.musicxml.note;

import org.curtis.musicxml.converter.StepConverter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("rest")
public class Rest extends NoteType {
    @Convert(converter = StepConverter.class)
    @Column(name = "step")
    private Step step;
    @Column(name = "octave")
    private Integer octave;
    @Column
    @Type(type="yes_no")
    private Boolean measure;

    public Rest() {

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

    public Boolean getMeasure() {
        return measure;
    }

    public void setMeasure(Boolean measure) {
        this.measure = measure;
    }
}
