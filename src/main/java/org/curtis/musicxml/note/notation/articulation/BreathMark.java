package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.converter.BreathMarkTypeConverter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("breath mark")
public class BreathMark extends Articulation {
    @Column(name = "breath_mark_value")
    @Convert(converter = BreathMarkTypeConverter.class)
    private BreathMarkType breathMarkValue;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "print_style_id")
    private PrintStyle printStyle;
    @Enumerated(EnumType.STRING)
    @Column
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
