package org.curtis.musicxml.layout;

import java.util.List;

public class PageLayout {
    // TODO: page height
    // TODO: page width
    private List<PageMargins> pageMarginsList;

    public PageLayout() {

    }

    public List<PageMargins> getPageMarginsList() {
        return pageMarginsList;
    }

    public void setPageMarginsList(List<PageMargins> pageMarginsList) {
        this.pageMarginsList = pageMarginsList;
    }
}
