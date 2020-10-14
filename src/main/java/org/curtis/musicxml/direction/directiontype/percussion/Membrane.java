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
    @Column
    private MembraneType value;

    public Membrane() {

    }

    public MembraneType getValue() {
        return value;
    }

    public void setValue(MembraneType value) {
        this.value = value;
    }
}
