package org.curtis.musicxml.score;

import org.curtis.musicxml.common.PrintStyle;

public class PartName {
    private String partName;
    private PrintStyle partNamePrintStyle;
    private Boolean partNamePrintObject;
    private String partNameJustify;

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

    public String getPartNameJustify() {
        return partNameJustify;
    }

    public void setPartNameJustify(String partNameJustify) {
        this.partNameJustify = partNameJustify;
    }
}
