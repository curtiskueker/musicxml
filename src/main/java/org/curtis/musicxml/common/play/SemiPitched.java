package org.curtis.musicxml.common.play;

public class SemiPitched extends PlayType {
    private SemiPitchcedType semiPitchcedType;

    public SemiPitched() {

    }

    public SemiPitchcedType getSemiPitchcedType() {
        return semiPitchcedType;
    }

    public void setSemiPitchcedType(SemiPitchcedType semiPitchcedType) {
        this.semiPitchcedType = semiPitchcedType;
    }
}
