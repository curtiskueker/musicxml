package org.curtis.musicxml.note.notation.ornament;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.common.Connection;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("tremolo")
public class Tremolo extends Ornament {
    @Column(name = "tremolo_marks")
    private Integer tremoloMarks;
    @Transient
    private Connection type = Connection.SINGLE;
    @Transient
    private PrintStyle printStyle;
    @Transient
    private Location placement;

    public Tremolo() {

    }

    public Integer getTremoloMarks() {
        return tremoloMarks;
    }

    public void setTremoloMarks(Integer tremoloMarks) {
        this.tremoloMarks = tremoloMarks;
    }

    public Connection getType() {
        return type;
    }

    public void setType(Connection type) {
        this.type = type;
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
