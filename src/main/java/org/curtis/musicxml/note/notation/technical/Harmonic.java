package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyle;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("harmonic")
public class Harmonic extends Technical {
    @Transient
    private HarmonicType harmonicType;
    @Transient
    private HarmonicPitch harmonicPitch;
    @Transient
    private Boolean printObject;
    @Transient
    private PrintStyle printStyle;
    @Transient
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
