package org.curtis.musicxml.note;

import org.curtis.musicxml.score.MusicData;

import java.math.BigDecimal;

public class Backup extends MusicData {
    private BigDecimal duration;
    // TODO: editorial

    public Backup() {

    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }
}
