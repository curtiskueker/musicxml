package org.curtis.musicxml.direction.directiontype.percussion;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("membrane")
public class Membrane extends Percussion {
    @Enumerated(EnumType.STRING)
    @Column(name = "direction_type")
    private MembraneType type;

    public Membrane() {

    }

    public MembraneType getType() {
        return type;
    }

    public void setType(MembraneType type) {
        this.type = type;
    }
}
