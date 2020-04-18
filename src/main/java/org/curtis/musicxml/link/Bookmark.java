package org.curtis.musicxml.link;

import org.curtis.musicxml.score.MusicData;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("bookmark")
public class Bookmark extends MusicData {
    @Column(name = "bookmark_id")
    private String bookmarkId;
    @Column
    private String name;
    @Column
    private String element;
    @Column
    private Integer position;

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

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
