package org.curtis.musicxml.attributes;

public class Directive {
    private String value;
    // TODO: print style
    private String lang;

    public Directive() {

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
