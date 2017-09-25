package org.curtis.musicxml.direction.type;

import java.util.List;

public class MetronomeNote {
    private String metronomeType;
    // TODO: metronome dot
    private List<MetronomeBeam> metronomeBeams;
    private MetronomeTuplet metronomeTuplet;

    public MetronomeNote() {

    }

    public String getMetronomeType() {
        return metronomeType;
    }

    public void setMetronomeType(String metronomeType) {
        this.metronomeType = metronomeType;
    }

    public List<MetronomeBeam> getMetronomeBeams() {
        return metronomeBeams;
    }

    public void setMetronomeBeams(List<MetronomeBeam> metronomeBeams) {
        this.metronomeBeams = metronomeBeams;
    }

    public MetronomeTuplet getMetronomeTuplet() {
        return metronomeTuplet;
    }

    public void setMetronomeTuplet(MetronomeTuplet metronomeTuplet) {
        this.metronomeTuplet = metronomeTuplet;
    }
}
