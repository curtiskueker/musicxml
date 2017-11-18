package org.curtis.musicxml.layout;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PageLayout {
    private BigDecimal pageHeight;
    private BigDecimal pageWidth;
    private Map<MarginType, PageMargins> pageMargins = new HashMap<>();

    public PageLayout() {

    }

    public BigDecimal getPageHeight() {
        return pageHeight;
    }

    public void setPageHeight(BigDecimal pageHeight) {
        this.pageHeight = pageHeight;
    }

    public BigDecimal getPageWidth() {
        return pageWidth;
    }

    public void setPageWidth(BigDecimal pageWidth) {
        this.pageWidth = pageWidth;
    }

    public Map<MarginType, PageMargins> getPageMargins() {
        return pageMargins;
    }

    public void setPageMargins(Map<MarginType, PageMargins> pageMargins) {
        this.pageMargins = pageMargins;
    }
}
