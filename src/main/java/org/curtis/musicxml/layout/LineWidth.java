package org.curtis.musicxml.layout;

import java.math.BigDecimal;

public class LineWidth {
    private BigDecimal value;
    private String lineWidthType;

    public LineWidth() {

    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getLineWidthType() {
        return lineWidthType;
    }

    public void setLineWidthType(String lineWidthType) {
        this.lineWidthType = lineWidthType;
    }
}
