package org.curtis.musicxml.direction.type;

import org.curtis.musicxml.common.DashedFormatting;
import org.curtis.musicxml.common.PrintStyle;

public class OctaveShift extends DirectionType {
    private OctaveShiftType type;
    private Integer number;
    private Integer size;
    private DashedFormatting dashedFormatting;
    private PrintStyle printStyle;

    public OctaveShift() {

    }

    public OctaveShiftType getType() {
        return type;
    }

    public void setType(OctaveShiftType type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public DashedFormatting getDashedFormatting() {
        return dashedFormatting;
    }

    public void setDashedFormatting(DashedFormatting dashedFormatting) {
        this.dashedFormatting = dashedFormatting;
    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }
}
