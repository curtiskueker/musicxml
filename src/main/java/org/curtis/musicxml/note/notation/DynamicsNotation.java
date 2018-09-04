package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.direction.directiontype.Dynamics;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("dynamics")
public class DynamicsNotation extends Notation {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dynamics_id")
    private Dynamics dynamics;

    public DynamicsNotation() {

    }

    public Dynamics getDynamics() {
        return dynamics;
    }

    public void setDynamics(Dynamics dynamics) {
        this.dynamics = dynamics;
    }
}
