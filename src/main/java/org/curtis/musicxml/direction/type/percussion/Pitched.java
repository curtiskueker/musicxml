package org.curtis.musicxml.direction.type.percussion;

public class Pitched extends Percussion {
    private PitchedType value;

    public Pitched() {

    }

    public PitchedType getValue() {
        return value;
    }

    public void setValue(PitchedType value) {
        this.value = value;
    }
}
