package org.curtis.musicxml.note.lyric;

public class LyricElision {
    private TextFontColor elision;
    private String syllabic;
    private TextElementData text;

    public LyricElision() {

    }

    public TextFontColor getElision() {
        return elision;
    }

    public void setElision(TextFontColor elision) {
        this.elision = elision;
    }

    public String getSyllabic() {
        return syllabic;
    }

    public void setSyllabic(String syllabic) {
        this.syllabic = syllabic;
    }

    public TextElementData getText() {
        return text;
    }

    public void setText(TextElementData text) {
        this.text = text;
    }
}
