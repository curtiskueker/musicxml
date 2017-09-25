package org.curtis.musicxml.note.notation.technical;

public class Arrow extends Technical {
    private String arrowDirection;
    private String arrowStyle;
    private String circularArrow;
    // TODO: print style
    // TODO: placement

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
}
