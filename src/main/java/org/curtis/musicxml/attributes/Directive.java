package org.curtis.musicxml.attributes;

import org.curtis.musicxml.common.PrintStyle;

public class Directive {
    private String value;
    private PrintStyle printStyle;
    private String lang;

    public Directive() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
