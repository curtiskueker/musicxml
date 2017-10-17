package org.curtis.musicxml.direction.harmony;

import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.note.Step;

public class RootStep {
    private Step step;
    private String text;
    private PrintStyle printStyle;

    public RootStep() {

    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
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
