package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyle;

public class Harmonic extends Technical {
    private HarmonicType harmonicType;
    private HarmonicPitch harmonicPitch;
    private Boolean printObject;
    private PrintStyle printStyle;
    private Location placement;

    public Harmonic() {

    }

    public HarmonicType getHarmonicType() {
        return harmonicType;
    }

    public void setHarmonicType(HarmonicType harmonicType) {
        this.harmonicType = harmonicType;
    }

    public HarmonicPitch getHarmonicPitch() {
        return harmonicPitch;
    }

    public void setHarmonicPitch(HarmonicPitch harmonicPitch) {
        this.harmonicPitch = harmonicPitch;
    }

    public Boolean getPrintObject() {
        return printObject;
    }

    public void setPrintObject(Boolean printObject) {
        this.printObject = printObject;
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
