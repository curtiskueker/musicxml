package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyle;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("breath mark")
public class BreathMark extends Articulation {
    @Enumerated(EnumType.STRING)
    @Column(name = "breath_mark_value")
    private BreathMarkType breathMarkValue;
    @Transient
    private PrintStyle printStyle;
    @Transient
    private Location placement;

    public BreathMark() {

    }

    public BreathMarkType getBreathMarkValue() {
        return breathMarkValue;
    }

    public void setBreathMarkValue(BreathMarkType breathMarkValue) {
        this.breathMarkValue = breathMarkValue;
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
