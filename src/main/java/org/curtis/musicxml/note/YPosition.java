package org.curtis.musicxml.note;

import java.math.BigDecimal;

public class YPosition {
    private BigDecimal defaultX;
    private BigDecimal defaultY;
    private BigDecimal relativeX;
    private BigDecimal relativeY;

    public YPosition() {

    }

    public BigDecimal getDefaultX() {
        return defaultX;
    }

    public void setDefaultX(BigDecimal defaultX) {
        this.defaultX = defaultX;
    }

    public BigDecimal getDefaultY() {
        return defaultY;
    }

    public void setDefaultY(BigDecimal defaultY) {
        this.defaultY = defaultY;
    }

    public BigDecimal getRelativeX() {
        return relativeX;
    }

    public void setRelativeX(BigDecimal relativeX) {
        this.relativeX = relativeX;
    }

    public BigDecimal getRelativeY() {
        return relativeY;
    }

    public void setRelativeY(BigDecimal relativeY) {
        this.relativeY = relativeY;
    }
}
