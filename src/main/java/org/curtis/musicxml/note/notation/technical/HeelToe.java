package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.note.Placement;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public abstract class HeelToe extends Technical {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "placement_id")
    private Placement placement;
    @Column
    private Boolean substitution;

    public Placement getPlacement() {
        return placement;
    }

    public void setPlacement(Placement placement) {
        this.placement = placement;
    }

    public Boolean getSubstitution() {
        return substitution;
    }

    public void setSubstitution(Boolean substitution) {
        this.substitution = substitution;
    }
}
