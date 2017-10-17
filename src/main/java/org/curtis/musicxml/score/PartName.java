package org.curtis.musicxml.score;

import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.common.Location;

public class PartName {
    private String partName;
    private PrintStyle partNamePrintStyle;
    private Boolean partNamePrintObject;
    private Location partNameJustify;

    public PartName() {

    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public PrintStyle getPartNamePrintStyle() {
        return partNamePrintStyle;
    }

    public void setPartNamePrintStyle(PrintStyle partNamePrintStyle) {
        this.partNamePrintStyle = partNamePrintStyle;
    }

    public Boolean getPartNamePrintObject() {
        return partNamePrintObject;
    }

    public void setPartNamePrintObject(Boolean partNamePrintObject) {
        this.partNamePrintObject = partNamePrintObject;
    }

    public Location getPartNameJustify() {
        return partNameJustify;
    }

    public void setPartNameJustify(Location partNameJustify) {
        this.partNameJustify = partNameJustify;
    }
}
