package org.curtis.musicxml.direction.directiontype.metronome;

import org.curtis.musicxml.note.BeamType;

public class MetronomeBeam {
    private BeamType beamValue;
    private Integer number = 1;

    public MetronomeBeam() {

    }

    public BeamType getBeamValue() {
        return beamValue;
    }

    public void setBeamValue(BeamType beamValue) {
        this.beamValue = beamValue;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}