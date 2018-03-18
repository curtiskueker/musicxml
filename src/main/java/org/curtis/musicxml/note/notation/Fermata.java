package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.common.PrintStyle;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("fermata")
public class Fermata extends Notation {
    @Transient
    private FermataShape fermataShape;
    @Transient
    private FermataType type;
    @Transient
    private PrintStyle printStyle;

    public Fermata() {

    }

    public FermataShape getFermataShape() {
        return fermataShape;
    }

    public void setFermataShape(FermataShape fermataShape) {
        this.fermataShape = fermataShape;
    }

    public FermataType getType() {
        return type;
    }

    public void setType(FermataType type) {
        this.type = type;
    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }
}
