package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.common.PrintStyle;

public class Arrow extends Technical {
    private String arrowDirection;
    private String arrowStyle;
    private String circularArrow;
    private PrintStyle printStyle;
    private String placement;

    public Arrow() {

    }

    public String getArrowDirection() {
        return arrowDirection;
    }

    public void setArrowDirection(String arrowDirection) {
        this.arrowDirection = arrowDirection;
    }

    public String getArrowStyle() {
        return arrowStyle;
    }

    public void setArrowStyle(String arrowStyle) {
        this.arrowStyle = arrowStyle;
    }

    public String getCircularArrow() {
        return circularArrow;
    }

    public void setCircularArrow(String circularArrow) {
        this.circularArrow = circularArrow;
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
