package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyle;

public class Arrow extends Technical {
    private ArrowDirection arrowDirection;
    private ArrowStyle arrowStyle;
    private CircularArrow circularArrow;
    private PrintStyle printStyle;
    private Location placement;

    public Arrow() {

    }

    public ArrowDirection getArrowDirection() {
        return arrowDirection;
    }

    public void setArrowDirection(ArrowDirection arrowDirection) {
        this.arrowDirection = arrowDirection;
    }

    public ArrowStyle getArrowStyle() {
        return arrowStyle;
    }

    public void setArrowStyle(ArrowStyle arrowStyle) {
        this.arrowStyle = arrowStyle;
    }

    public CircularArrow getCircularArrow() {
        return circularArrow;
    }

    public void setCircularArrow(CircularArrow circularArrow) {
        this.circularArrow = circularArrow;
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
