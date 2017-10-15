package org.curtis.musicxml.direction.harmony;

import org.curtis.musicxml.common.PrintStyle;

public class RootStep {
    // TODO: step
    private String text;
    private PrintStyle printStyle;

    public RootStep() {

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
