package org.curtis.musicxml.score;

import org.curtis.musicxml.link.Bookmark;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("bookmark")
public class CreditBookmark extends CreditDisplay {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bookmark_id")
    private Bookmark bookmark;

    public CreditBookmark() {

    }

    public Bookmark getBookmark() {
        return bookmark;
    }

    public void setBookmark(Bookmark bookmark) {
        this.bookmark = bookmark;
    }
}
