package org.curtis.musicxml.note.lyric;

import org.curtis.musicxml.common.Connection;

import java.util.ArrayList;
import java.util.List;

public class LyricSyllable extends LyricItem {
    private Connection syllabic;
    private TextData text;
    private List<LyricElision> lyricElisions = new ArrayList<>();
    private Extend extend;

    public LyricSyllable() {

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

    public List<LyricElision> getLyricElisions() {
        return lyricElisions;
    }

    public void setLyricElisions(List<LyricElision> lyricElisions) {
        this.lyricElisions = lyricElisions;
    }

    public Extend getExtend() {
        return extend;
    }

    public void setExtend(Extend extend) {
        this.extend = extend;
    }
}
