package org.curtis.musicxml.direction.directiontype.metronome;

import org.curtis.musicxml.common.PrintStyleAlign;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.direction.directiontype.DirectionType;

public abstract class Metronome extends DirectionType {
    private PrintStyleAlign printStyleAlign;
    private Location justify;
    private Boolean parentheses;

    public PrintStyleAlign getPrintStyleAlign() {
        return printStyleAlign;
    }

    public void setPrintStyleAlign(PrintStyleAlign printStyleAlign) {
        this.printStyleAlign = printStyleAlign;
    }

    public Location getJustify() {
        return justify;
    }

    public void setJustify(Location justify) {
        this.justify = justify;
    }

    public Boolean getParentheses() {
        return parentheses;
    }

    public void setParentheses(Boolean parentheses) {
        this.parentheses = parentheses;
    }
}
