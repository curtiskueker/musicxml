package org.curtis.musicxml.note.notation.ornament;

import org.curtis.musicxml.common.Connection;
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

@Entity
@DiscriminatorValue("tremolo")
public class Tremolo extends Ornament {
    @Column(name = "tremolo_marks")
    private Integer tremoloMarks;
    @Enumerated(EnumType.STRING)
    @Column
    private Connection type = Connection.SINGLE;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "print_style_id")
    private PrintStyle printStyle;
    @Enumerated(EnumType.STRING)
    @Column
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
