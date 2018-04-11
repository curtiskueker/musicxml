package org.curtis.musicxml.layout;

import org.curtis.database.DatabaseItem;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "page_margins")
public class PageMargins extends DatabaseItem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "margins_id")
    private Margins margins;
    @Enumerated(EnumType.STRING)
    @Column
    private MarginType type;
    @Enumerated(EnumType.STRING)
    @Column(name = "margin_type_key")
    private MarginType marginTypeKey;

    public PageMargins() {

    }

    public Margins getMargins() {
        return margins;
    }

    public void setMargins(Margins margins) {
        this.margins = margins;
    }

    public MarginType getType() {
        return type;
    }

    public void setType(MarginType type) {
        this.type = type;
    }

    public MarginType getMarginTypeKey() {
        return marginTypeKey;
    }

    public void setMarginTypeKey(MarginType marginTypeKey) {
        this.marginTypeKey = marginTypeKey;
    }
}
