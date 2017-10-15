package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.common.PrintStyle;

public class Fermata extends Notation {
    private String fermataShape;
    private String type;
    private PrintStyle printStyle;

    public Fermata() {

    }

    public String getFermataShape() {
        return fermataShape;
    }

    public void setFermataShape(String fermataShape) {
        this.fermataShape = fermataShape;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }
}
