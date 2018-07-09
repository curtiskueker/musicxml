package org.curtis.musicxml.score;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.attributes.Image;
import org.curtis.musicxml.common.FormattedText;
import org.curtis.musicxml.link.Bookmark;
import org.curtis.musicxml.link.Link;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "credit")
public class Credit extends DatabaseItem {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "credit_id", nullable = false)
    private List<CreditType> creditTypes = new ArrayList<>();
    @Transient
    // transient collection
    private List<Link> links = new ArrayList<>();
    @Transient
    // transient collection
    private List<Bookmark> bookmarks = new ArrayList<>();
    @Transient
    // transient collection
    private Image creditImage;
    @Transient
    // transient collection
    private List<FormattedText> creditWordsList = new ArrayList<>();
    @Transient
    // transient collection
    private List<Link> creditWordsLinks = new ArrayList<>();
    @Transient
    // transient collection
    private List<Bookmark> creditWordsBookmarks = new ArrayList<>();
    @Column
    private Integer page;

    public Credit() {

    }

    public List<CreditType> getCreditTypes() {
        return creditTypes;
    }

    public void setCreditTypes(List<CreditType> creditTypes) {
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
