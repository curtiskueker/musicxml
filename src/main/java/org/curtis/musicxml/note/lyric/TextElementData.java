package org.curtis.musicxml.note.lyric;

public class TextElementData {
    private String value;
    // TODO: font
    // TODO: color
    // TODO: text decoration
    // TODO: text rotation
    // TODO: letter spacing
    private String lang;
    // TODO: text direction

    public TextElementData() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
