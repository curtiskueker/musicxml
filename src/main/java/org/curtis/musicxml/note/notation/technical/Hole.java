package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.display.Display;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("hole")
public class Hole extends Technical {
    @Column(name = "hole_type")
    private String holeType;
    @Enumerated(EnumType.STRING)
    @Column(name = "hole_close_type")
    private HoleClosedType holeClosedType;
    @Enumerated(EnumType.STRING)
    @Column(name = "hole_closed_location")
    private HoleClosedLocation holeClosedLocation;
    @Column(name = "hole_shape")
    private String holeShape;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;

    public Hole() {

    }

    public String getHoleType() {
        return holeType;
    }

    public void setHoleType(String holeType) {
        this.holeType = holeType;
    }

    public HoleClosedType getHoleClosedType() {
        return holeClosedType;
    }

    public void setHoleClosedType(HoleClosedType holeClosedType) {
        this.holeClosedType = holeClosedType;
    }

    public HoleClosedLocation getHoleClosedLocation() {
        return holeClosedLocation;
    }

    public void setHoleClosedLocation(HoleClosedLocation holeClosedLocation) {
        this.holeClosedLocation = holeClosedLocation;
    }

    public String getHoleShape() {
        return holeShape;
    }

    public void setHoleShape(String holeShape) {
        this.holeShape = holeShape;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}
