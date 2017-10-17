package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.common.DashedFormatting;
import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.note.LineType;

public class Glissando extends Notation {
    private String value;
    private Connection type;
    private Integer number = 1;
    private LineType lineType;
    private DashedFormatting dashedFormatting;
    private PrintStyle printStyle;

    public Glissando() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Connection getType() {
        return type;
    }

    public void setType(Connection type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public LineType getLineType() {
        return lineType;
    }

    public void setLineType(LineType lineType) {
        this.lineType = lineType;
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
