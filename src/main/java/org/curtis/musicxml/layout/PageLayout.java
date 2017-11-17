package org.curtis.musicxml.layout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PageLayout {
    private BigDecimal pageHeight;
    private BigDecimal pageWidth;
    private List<PageMargins> pageMarginsList = new ArrayList<>();

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

    public List<PageMargins> getPageMarginsList() {
        return pageMarginsList;
    }

    public void setPageMarginsList(List<PageMargins> pageMarginsList) {
        this.pageMarginsList = pageMarginsList;
    }
}
