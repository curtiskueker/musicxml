package org.curtis.musicxml.note.notation.technical;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "bend_sound")
public class BendSound extends DatabaseItem {
    @Column
    private Boolean accelerate;
    @Column(precision = 12, scale = 4)
    private BigDecimal beats;
    @Column(name = "first_beat", precision = 12, scale = 4)
    private BigDecimal firstBeat;
    @Column(name = "last_beat", precision = 12, scale = 4)
    private BigDecimal lastBeat;

    public BendSound() {

    }

    public Boolean getAccelerate() {
        return accelerate;
    }

    public void setAccelerate(Boolean accelerate) {
        this.accelerate = accelerate;
    }

    public BigDecimal getBeats() {
        return beats;
    }

    public void setBeats(BigDecimal beats) {
        this.beats = beats;
    }

    public BigDecimal getFirstBeat() {
        return firstBeat;
    }

    public void setFirstBeat(BigDecimal firstBeat) {
        this.firstBeat = firstBeat;
    }

    public BigDecimal getLastBeat() {
        return lastBeat;
    }

    public void setLastBeat(BigDecimal lastBeat) {
        this.lastBeat = lastBeat;
    }
}
