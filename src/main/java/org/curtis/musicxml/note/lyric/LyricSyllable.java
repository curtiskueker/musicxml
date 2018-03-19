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
import javax.persistence.Transient;

@Entity
@Table(name = "lyric_syllable")
public class LyricSyllable extends DatabaseItem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lyric_elision_id")
    private TextFontColor lyricElision;
    @Enumerated(EnumType.STRING)
    @Column
    private Connection syllabic;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "text_data_id")
    private TextData text;

    public LyricSyllable() {

    }

    public TextFontColor getLyricElision() {
        return lyricElision;
    }

    public void setLyricElision(TextFontColor lyricElision) {
        this.lyricElision = lyricElision;
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
