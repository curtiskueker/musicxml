package org.curtis.musicxml.common;

import java.math.BigDecimal;

public class FontSize {
    private BigDecimal fontSize;
    private CssFontSize cssFontSize;

    public FontSize() {

    }

    public BigDecimal getFontSize() {
        return fontSize;
    }

    public void setFontSize(BigDecimal fontSize) {
        this.fontSize = fontSize;
    }

    public CssFontSize getCssFontSize() {
        return cssFontSize;
    }

    public void setCssFontSize(CssFontSize cssFontSize) {
        this.cssFontSize = cssFontSize;
    }
}
