package org.curtis.musicxml.note.lyric;

import org.curtis.musicxml.common.Connection;

public class LyricElision {
    private TextFontColor elision;
    private Connection syllabic;
    private TextData text;

    public LyricElision() {

    }

    public TextFontColor getElision() {
        return elision;
    }

    public void setElision(TextFontColor elision) {
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
