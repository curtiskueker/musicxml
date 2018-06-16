package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyle;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("harmonic")
public class Harmonic extends Technical {
    @Enumerated(EnumType.STRING)
    @Column(name = "harmonic_type")
    private HarmonicType harmonicType;
    @Enumerated(EnumType.STRING)
    @Column(name = "harmonic_pitch")
    private HarmonicPitch harmonicPitch;
    @Column(name = "print_object")
    private Boolean printObject;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "print_style_id")
    private PrintStyle printStyle;
    @Enumerated(EnumType.STRING)
    @Column
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
