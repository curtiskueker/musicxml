package org.curtis.musicxml.direction.directiontype;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("damp all")
public class DampAll extends DirectionType {
    public DampAll() {

    }
}
