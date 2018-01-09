package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.direction.directiontype.Dynamics;

public class DynamicsNotation extends Notation {
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
