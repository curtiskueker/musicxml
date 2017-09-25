package org.curtis.musicxml.note.lyric;

public class LyricSyllable extends LyricItem {
    private String syllabic;
    private TextElementData text;
    private TextFontColor elision;
    private Extend extend;

    public LyricSyllable() {

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

    public TextFontColor getElision() {
        return elision;
    }

    public void setElision(TextFontColor elision) {
        this.elision = elision;
    }

    public Extend getExtend() {
        return extend;
    }

    public void setExtend(Extend extend) {
        this.extend = extend;
    }
}
