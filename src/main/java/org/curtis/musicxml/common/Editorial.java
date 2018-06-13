package org.curtis.musicxml.common;

import org.curtis.database.DatabaseItem;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "editorial")
public class Editorial extends DatabaseItem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "footnote_id")
    private FormattedText footnote;
    @Transient
    private Level level;

    public Editorial() {

    }

    public FormattedText getFootnote() {
        return footnote;
    }

    public void setFootnote(FormattedText footnote) {
        this.footnote = footnote;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
