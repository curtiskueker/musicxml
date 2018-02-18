package org.curtis.musicxml.score;

import org.curtis.musicxml.attributes.Image;
import org.curtis.musicxml.common.FormattedText;
import org.curtis.musicxml.link.Bookmark;
import org.curtis.musicxml.link.Link;

import java.util.ArrayList;
import java.util.List;

public class Credit {
    private List<String> creditTypes = new ArrayList<>();
    private List<Link> links = new ArrayList<>();
    private List<Bookmark> bookmarks = new ArrayList<>();
    private Image creditImage;
    private List<FormattedText> creditWordsList = new ArrayList<>();
    private List<Link> creditWordsLinks = new ArrayList<>();
    private List<Bookmark> creditWordsBookmarks = new ArrayList<>();
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

    public Image getCreditImage() {
        return creditImage;
    }

    public void setCreditImage(Image creditImage) {
        this.creditImage = creditImage;
    }

    public List<FormattedText> getCreditWordsList() {
        return creditWordsList;
    }

    public void setCreditWordsList(List<FormattedText> creditWordsList) {
        this.creditWordsList = creditWordsList;
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
