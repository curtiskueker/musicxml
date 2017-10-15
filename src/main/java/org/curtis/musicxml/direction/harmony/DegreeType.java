package org.curtis.musicxml.direction.harmony;

import org.curtis.musicxml.common.PrintStyle;

public class DegreeType {
    private String value;
    private String text;
    private PrintStyle printStyle;

    public DegreeType() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }
}
