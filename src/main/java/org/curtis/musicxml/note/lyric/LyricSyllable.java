package org.curtis.musicxml.note.lyric;

import org.curtis.musicxml.common.Connection;

public class LyricSyllable extends LyricItem {
    private Connection syllabic;
    private TextElementData text;
    private LyricElision lyricElision;
    private Extend extend;

    public LyricSyllable() {

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

    public LyricElision getLyricElision() {
        return lyricElision;
    }

    public void setLyricElision(LyricElision lyricElision) {
        this.lyricElision = lyricElision;
    }

    public Extend getExtend() {
        return extend;
    }

    public void setExtend(Extend extend) {
        this.extend = extend;
    }
}
