package org.curtis.musicxml.direction.directiontype;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("damp")
public class Damp extends DirectionType {
    public Damp() {

    }
}
