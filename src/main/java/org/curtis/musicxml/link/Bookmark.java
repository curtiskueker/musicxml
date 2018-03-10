package org.curtis.musicxml.link;

import org.curtis.musicxml.score.MusicData;

public class Bookmark extends MusicData {
    private String bookmarkId;
    private String name;
    private ElementPosition elementPosition;

    public Bookmark() {

    }

    public String getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(String bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ElementPosition getElementPosition() {
        return elementPosition;
    }

    public void setElementPosition(ElementPosition elementPosition) {
        this.elementPosition = elementPosition;
    }
}
