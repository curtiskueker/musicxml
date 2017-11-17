package org.curtis.musicxml.layout;

import java.math.BigDecimal;

public class Margins {
    private BigDecimal leftMargin;
    private BigDecimal rightMargin;
    private BigDecimal topMargin;
    private BigDecimal bottomMargin;

    public Margins() {

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

    public BigDecimal getTopMargin() {
        return topMargin;
    }

    public void setTopMargin(BigDecimal topMargin) {
        this.topMargin = topMargin;
    }

    public BigDecimal getBottomMargin() {
        return bottomMargin;
    }

    public void setBottomMargin(BigDecimal bottomMargin) {
        this.bottomMargin = bottomMargin;
    }
}
