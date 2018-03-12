package org.curtis.musicxml.direction.directiontype.metronome;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.note.BeamType;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "metronome_beam")
public class MetronomeBeam extends DatabaseItem {
    @Transient
    private BeamType beamValue;
    @Transient
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
