package org.curtis.musicxml.direction.type;

import java.util.List;

public class Scordatura extends DirectionType {
    private List<Accord> accords;

    public Scordatura() {

    }

    public List<Accord> getAccords() {
        return accords;
    }

    public void setAccords(List<Accord> accords) {
        this.accords = accords;
    }
}
