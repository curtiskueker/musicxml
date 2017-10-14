package org.curtis.musicxml.direction.type;

import java.math.BigDecimal;

public class Bracket extends DirectionType {
    private String type;
    // TODO: number
    private String lineEnd;
    private BigDecimal endLength;
    // TODO: line type
    // TODO: dashed formatting
    // TODO: position
    // TODO: color

    public Bracket() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLineEnd() {
        return lineEnd;
    }

    public void setLineEnd(String lineEnd) {
        this.lineEnd = lineEnd;
    }

    public BigDecimal getEndLength() {
        return endLength;
    }

    public void setEndLength(BigDecimal endLength) {
        this.endLength = endLength;
    }
}
