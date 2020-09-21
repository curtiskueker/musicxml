package org.curtis.musicxml.note;

import org.curtis.musicxml.converter.StepConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("pitch")
public class Pitch extends NoteType {
    @Convert(converter = StepConverter.class)
    @Column
    private Step step;
    @Column(name = "pitch_alter", precision = 12, scale = 4)
    private BigDecimal alter;
    @Column
    private Integer octave;

    public Pitch() {

    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    public BigDecimal getAlter() {
        return alter;
    }

    public void setAlter(BigDecimal alter) {
        this.alter = alter;
    }

    public Integer getOctave() {
        return octave;
    }

    public void setOctave(Integer octave) {
        this.octave = octave;
    }
}
