package org.curtis.musicxml.layout;

import java.math.BigDecimal;

public class SystemMargins {
    private BigDecimal leftMargin;
    private BigDecimal rightMargin;


    public SystemMargins() {

    }

    public BigDecimal getLeftMargin() {
        return leftMargin;
    }

    public void setLeftMargin(BigDecimal leftMargin) {
        this.leftMargin = leftMargin;
    }

    public BigDecimal getRightMargin() {
        return rightMargin;
    }

    public void setRightMargin(BigDecimal rightMargin) {
        this.rightMargin = rightMargin;
    }
}
