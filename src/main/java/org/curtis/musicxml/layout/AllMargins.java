package org.curtis.musicxml.layout;

import java.math.BigDecimal;

public class AllMargins {
    private LeftRightMargins leftRightMargins;
    private BigDecimal topMargin;
    private BigDecimal bottomMargin;

    public AllMargins() {

    }

    public LeftRightMargins getLeftRightMargins() {
        return leftRightMargins;
    }

    public void setLeftRightMargins(LeftRightMargins leftRightMargins) {
        this.leftRightMargins = leftRightMargins;
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
