package org.curtis.musicxml.score;

import org.curtis.musicxml.link.Bookmark;
import org.curtis.musicxml.link.Link;

import java.util.List;

public class Credit {
    private List<String> creditTypes;
    private List<Link> links;
    private List<Bookmark> bookmarks;
    // TODO: credit image
    // TODO: credit words
    private List<Link> creditWordsLinks;
    private List<Bookmark> creditWordsBookmarks;
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

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<Bookmark> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(List<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }

    public List<Link> getCreditWordsLinks() {
        return creditWordsLinks;
    }

    public void setCreditWordsLinks(List<Link> creditWordsLinks) {
        this.creditWordsLinks = creditWordsLinks;
    }

    public List<Bookmark> getCreditWordsBookmarks() {
        return creditWordsBookmarks;
    }

    public void setCreditWordsBookmarks(List<Bookmark> creditWordsBookmarks) {
        this.creditWordsBookmarks = creditWordsBookmarks;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
