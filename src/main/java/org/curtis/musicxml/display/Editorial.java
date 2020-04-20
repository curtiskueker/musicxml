package org.curtis.musicxml.display;

import org.curtis.database.DatabaseItem;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "editorial")
public class Editorial extends DatabaseItem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "footnote_id")
    private Footnote footnote;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "editorial_level_id")
    private EditorialLevel editorialLevel;

    public Editorial() {

    }

    public Footnote getFootnote() {
        return footnote;
    }

    public void setFootnote(Footnote footnote) {
        this.footnote = footnote;
    }

    public EditorialLevel getEditorialLevel() {
        return editorialLevel;
    }

    public void setEditorialLevel(EditorialLevel editorialLevel) {
        this.editorialLevel = editorialLevel;
    }
}
