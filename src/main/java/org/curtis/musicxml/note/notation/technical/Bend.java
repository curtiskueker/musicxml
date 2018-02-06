package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.note.PlacementText;

import java.math.BigDecimal;

public class Bend extends Technical {
    private BigDecimal bendAlter;
    private BendType bendType;
    private PlacementText withBar;
    private PrintStyle printStyle;
    private BendSound bendSound;

    public Bend() {

    }

    public BigDecimal getBendAlter() {
        return bendAlter;
    }

    public void setBendAlter(BigDecimal bendAlter) {
        this.bendAlter = bendAlter;
    }

    public BendType getBendType() {
        return bendType;
    }

    public void setBendType(BendType bendType) {
        this.bendType = bendType;
    }

    public PlacementText getWithBar() {
        return withBar;
    }

    public void setWithBar(PlacementText withBar) {
        this.withBar = withBar;
    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }

    public BendSound getBendSound() {
        return bendSound;
    }

    public void setBendSound(BendSound bendSound) {
        this.bendSound = bendSound;
    }
}