package org.curtis.musicxml.note.lyric;

import org.curtis.musicxml.common.Connection;

public class LyricElision {
    private TextFontColor elision;
    private Connection syllabic;
    private TextElementData text;

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

    public TextElementData getText() {
        return text;
    }

    public void setText(TextElementData text) {
        this.text = text;
    }
}
