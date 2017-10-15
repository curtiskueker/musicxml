package org.curtis.musicxml.note.notation.ornament;

import org.curtis.musicxml.common.PrintStyle;

public class Tremolo extends Ornament {
    private Integer tremoloMarks;
    private String type = "single";
    private PrintStyle printStyle;
    private String placement;

    public Tremolo() {

    }

    public Integer getTremoloMarks() {
        return tremoloMarks;
    }

    public void setTremoloMarks(Integer tremoloMarks) {
        this.tremoloMarks = tremoloMarks;
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

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }
}
