package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.note.PlacementText;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("bend")
public class Bend extends Technical {
    @Column(name = "bend_alter", precision = 12, scale = 4)
    private BigDecimal bendAlter;
    @Enumerated(EnumType.STRING)
    @Column(name = "bend_type")
    private BendType bendType;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "with_bar_id")
    private PlacementText withBar;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "print_style_id")
    private PrintStyle printStyle;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bend_sound_id")
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
