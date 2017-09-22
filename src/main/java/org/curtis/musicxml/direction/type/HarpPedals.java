package org.curtis.musicxml.direction.type;

import java.util.List;

public class HarpPedals extends DirectionType {
    private List<PedalTuning> pedalTunings;
    // TODO: print style align

    public HarpPedals() {

    }

    public List<PedalTuning> getPedalTunings() {
        return pedalTunings;
    }

    public void setPedalTunings(List<PedalTuning> pedalTunings) {
        this.pedalTunings = pedalTunings;
    }
}
