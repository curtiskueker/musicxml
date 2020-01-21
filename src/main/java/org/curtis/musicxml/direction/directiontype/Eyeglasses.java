package org.curtis.musicxml.direction.directiontype;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("eyeglasses")
public class Eyeglasses extends DirectionType {
    public Eyeglasses() {

    }
}
