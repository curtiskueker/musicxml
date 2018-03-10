package org.curtis.musicxml.link;

import org.curtis.musicxml.score.MusicData;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("bookmark")
public class Bookmark extends MusicData {
    @Transient
    private String bookmarkId;
    @Transient
    private String name;
    @Transient
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
