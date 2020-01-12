package org.curtis.musicxml.note.lyric;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.Connection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lyric_syllable")
public class LyricSyllable extends DatabaseItem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "elision_id")
    private Elision elision;
    @Enumerated(EnumType.STRING)
    @Column
    private Connection syllabic;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "text_data_id")
    private TextData text;

    public LyricSyllable() {

    }

    public Elision getElision() {
        return elision;
    }

    public void setElision(Elision elision) {
        this.elision = elision;
    }

    public Connection getSyllabic() {
        return syllabic;
    }

    public void setSyllabic(Connection syllabic) {
        this.syllabic = syllabic;
    }

    public TextData getText() {
        return text;
    }

    public void setText(TextData text) {
        this.text = text;
    }
}
