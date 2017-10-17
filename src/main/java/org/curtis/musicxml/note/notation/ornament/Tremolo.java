package org.curtis.musicxml.note.notation.ornament;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.common.Connection;

public class Tremolo extends Ornament {
    private Integer tremoloMarks;
    private Connection type = Connection.SINGLE;
    private PrintStyle printStyle;
    private Location placement;

    public Tremolo() {

    }

    public Integer getTremoloMarks() {
        return tremoloMarks;
    }

    public void setTremoloMarks(Integer tremoloMarks) {
        this.tremoloMarks = tremoloMarks;
    }

    public Connection getType() {
        return type;
    }

    public void setType(Connection type) {
        this.type = type;
    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }

    public Location getPlacement() {
        return placement;
    }

    public void setPlacement(Location placement) {
        this.placement = placement;
    }
}
