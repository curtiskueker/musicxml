package org.curtis.musicxml.direction.directiontype;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("coda")
public class Coda extends DirectionType {
    @Column
    private String smufl;

    public Coda() {

    }

    public String getSmufl() {
        return smufl;
    }

    public void setSmufl(String smufl) {
        this.smufl = smufl;
    }
}
