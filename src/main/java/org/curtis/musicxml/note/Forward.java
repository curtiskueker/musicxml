package org.curtis.musicxml.note;

import org.curtis.musicxml.score.MusicData;

import java.math.BigDecimal;

public class Forward extends MusicData {
    private BigDecimal duration;
    // TODO: editorial voice
    // TODO: staff

    public Forward() {

    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }
}
