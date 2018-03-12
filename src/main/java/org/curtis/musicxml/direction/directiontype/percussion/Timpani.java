package org.curtis.musicxml.direction.directiontype.percussion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("timpani")
public class Timpani extends Percussion {
    public Timpani() {

    }
}
