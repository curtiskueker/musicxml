package org.curtis.musicxml.note;

import org.curtis.musicxml.common.DashedFormatting;
import org.curtis.musicxml.common.PrintStyle;

public class Line {
    private String lineShape;
    private String lineType;
    private DashedFormatting dashedFormatting;
    private PrintStyle printStyle;
    private String placement;

    public Line() {

    }

    public String getLineShape() {
        return lineShape;
    }

    public void setLineShape(String lineShape) {
        this.lineShape = lineShape;
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
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

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }
}
