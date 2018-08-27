package org.curtis.musicxml.link;

import org.curtis.musicxml.score.CreditDisplay;
import org.curtis.musicxml.score.MusicData;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("bookmark")
public class Bookmark extends MusicData {
    @Column(name = "bookmark_id")
    private String bookmarkId;
    @Column
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "element_position_id")
    private ElementPosition elementPosition;
    @ManyToOne
    @JoinColumn(name = "credit_display_id")
    private CreditDisplay creditDisplay;

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

    public CreditDisplay getCreditDisplay() {
        return creditDisplay;
    }

    public void setCreditDisplay(CreditDisplay creditDisplay) {
        this.creditDisplay = creditDisplay;
    }
}
