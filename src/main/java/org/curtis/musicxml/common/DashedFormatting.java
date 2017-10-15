package org.curtis.musicxml.common;

import java.math.BigDecimal;

public class DashedFormatting {
    private BigDecimal dashLength;
    private BigDecimal spaceLength;

    public DashedFormatting() {

    }

    public BigDecimal getDashLength() {
        return dashLength;
    }

    public void setDashLength(BigDecimal dashLength) {
        this.dashLength = dashLength;
    }

    public BigDecimal getSpaceLength() {
        return spaceLength;
    }

    public void setSpaceLength(BigDecimal spaceLength) {
        this.spaceLength = spaceLength;
    }
}
