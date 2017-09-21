package org.curtis.musicxml.score;

import java.util.List;

public class Credit {
    private List<String> creditTypes;
    // TODO: links
    // TODO: bookmarks
    // TODO: credit image
    // TODO: credit words
    // TODO: credit words links
    // TODO: credit words bookmarks
    // TODO: additional credit words
    private Integer page;

    public Credit() {

    }

    public List<String> getCreditTypes() {
        return creditTypes;
    }

    public void setCreditTypes(List<String> creditTypes) {
        this.creditTypes = creditTypes;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
