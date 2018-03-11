package org.curtis.musicxml.direction.directiontype;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("scordatura")
public class Scordatura extends DirectionType {
    @Transient
    private List<Accord> accords = new ArrayList<>();

    public Scordatura() {

    }

    public List<Accord> getAccords() {
        return accords;
    }

    public void setAccords(List<Accord> accords) {
        this.accords = accords;
    }
}
