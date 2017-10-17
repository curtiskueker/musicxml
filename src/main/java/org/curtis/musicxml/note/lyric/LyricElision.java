package org.curtis.musicxml.note.lyric;

public class LyricElision {
    private TextFontColor elision;
    private Syllabic syllabic;
    private TextElementData text;

    public LyricElision() {

    }

    public TextFontColor getElision() {
        return elision;
    }

    public void setElision(TextFontColor elision) {
        this.elision = elision;
    }

    public Syllabic getSyllabic() {
        return syllabic;
    }

    public void setSyllabic(Syllabic syllabic) {
        this.syllabic = syllabic;
    }

    public TextElementData getText() {
        return text;
    }

    public void setText(TextElementData text) {
        this.text = text;
    }
}
