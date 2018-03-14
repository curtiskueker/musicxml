package org.curtis.musicxml.note;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("pitch")
public class Pitch extends FullNoteType {
    @Transient
    private Step step;
    @Transient
    private BigDecimal alter;
    @Transient
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
