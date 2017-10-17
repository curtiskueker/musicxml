package org.curtis.musicxml.note;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.DashedFormatting;
import org.curtis.musicxml.common.PrintStyle;

public class Line {
    private LineShape lineShape;
    private LineType lineType;
    private DashedFormatting dashedFormatting;
    private PrintStyle printStyle;
    private Location placement;

    public Line() {

    }

    public LineShape getLineShape() {
        return lineShape;
    }

    public void setLineShape(LineShape lineShape) {
        this.lineShape = lineShape;
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

    public Location getPlacement() {
        return placement;
    }

    public void setPlacement(Location placement) {
        this.placement = placement;
    }
}
