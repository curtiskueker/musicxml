package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.note.Placement;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("stopped")
public class Stopped extends Technical {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "placement_id")
    private Placement placement;

    public Stopped() {

    }

    public Placement getPlacement() {
        return placement;
    }

    public void setPlacement(Placement placement) {
        this.placement = placement;
    }
}
