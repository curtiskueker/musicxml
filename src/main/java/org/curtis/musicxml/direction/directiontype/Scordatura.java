package org.curtis.musicxml.direction.directiontype;

import java.util.ArrayList;
import java.util.List;

public class Scordatura extends DirectionType {
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
