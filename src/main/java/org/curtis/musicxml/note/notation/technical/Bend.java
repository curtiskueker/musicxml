package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.note.PlacementText;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("bend")
public class Bend extends Technical {
    @Transient
    private BigDecimal bendAlter;
    @Transient
    private BendType bendType;
    @Transient
    private PlacementText withBar;
    @Transient
    private PrintStyle printStyle;
    @Transient
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
