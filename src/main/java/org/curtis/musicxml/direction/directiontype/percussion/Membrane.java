package org.curtis.musicxml.direction.directiontype.percussion;

import org.curtis.musicxml.converter.MembraneTypeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("membrane")
public class Membrane extends Percussion {
    @Convert(converter = MembraneTypeConverter.class)
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
