package org.curtis.musicxml.common.play;

public class SemiPitched extends PlayType {
    private SemiPitchcedType value;

    public SemiPitched() {

    }

    public SemiPitchcedType getValue() {
        return value;
    }

    public void setValue(SemiPitchcedType value) {
        this.value = value;
    }
}
