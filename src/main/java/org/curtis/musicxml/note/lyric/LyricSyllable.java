package org.curtis.musicxml.note.lyric;

import org.curtis.database.OrderedItem;
import org.curtis.musicxml.converter.SyllabicConverter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lyric_syllable")
public class LyricSyllable extends OrderedItem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "elision_id")
    private Elision elision;
    @Convert(converter = SyllabicConverter.class)
    @Column
    private Syllabic syllabic;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lyric_text_data_id")
    private LyricTextData text;

    public LyricSyllable() {

    }

    public Elision getElision() {
        return elision;
    }

    public void setElision(Elision elision) {
        this.elision = elision;
    }

    public Syllabic getSyllabic() {
        return syllabic;
    }

    public void setSyllabic(Syllabic syllabic) {
        this.syllabic = syllabic;
    }

    public LyricTextData getText() {
        return text;
    }

    public void setText(LyricTextData text) {
        this.text = text;
    }
}
