package org.curtis.musicxml.direction.directiontype;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("segno")
public class Segno extends DirectionType {
    @Column
    private String smufl;

    public Segno() {

    }

    public String getSmufl() {
        return smufl;
    }

    public void setSmufl(String smufl) {
        this.smufl = smufl;
    }
}
