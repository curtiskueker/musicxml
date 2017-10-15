package org.curtis.musicxml.note.notation.technical.bend;

import org.curtis.musicxml.common.BendSound;
import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.note.PlacementText;
import org.curtis.musicxml.note.notation.technical.Technical;

import java.math.BigDecimal;

public class Bend extends Technical {
    private BigDecimal bendAlter;
    private PreBend preBend;
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

    public PreBend getPreBend() {
        return preBend;
    }

    public void setPreBend(PreBend preBend) {
        this.preBend = preBend;
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
