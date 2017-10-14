package org.curtis.musicxml.direction.type;

import java.math.BigDecimal;

public class Wedge extends DirectionType {
    private String type;
    // TODO: number
    private BigDecimal spread;
    private Boolean niente;
    // TODO: line type
    // TODO: dashed formatting
    // TODO: position
    // TODO: color

    public Wedge() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getSpread() {
        return spread;
    }

    public void setSpread(BigDecimal spread) {
        this.spread = spread;
    }

    public Boolean getNiente() {
        return niente;
    }

    public void setNiente(Boolean niente) {
        this.niente = niente;
    }
}
