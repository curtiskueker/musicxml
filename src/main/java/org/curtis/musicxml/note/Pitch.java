package org.curtis.musicxml.note;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("pitch")
public class Pitch extends FullNoteType {
    @Enumerated(EnumType.STRING)
    @Column
    private Step step;
    @Column(name = "pitch_alter")
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
