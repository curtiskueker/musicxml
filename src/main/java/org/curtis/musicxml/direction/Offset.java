package org.curtis.musicxml.direction;

import java.math.BigDecimal;

public class Offset {
    private BigDecimal divisions;
    private Boolean sound;

    public Offset() {

    }

    public BigDecimal getDivisions() {
        return divisions;
    }

    public void setDivisions(BigDecimal divisions) {
        this.divisions = divisions;
    }

    public Boolean getSound() {
        return sound;
    }

    public void setSound(Boolean sound) {
        this.sound = sound;
    }
}
