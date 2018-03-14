package org.curtis.musicxml.direction.directiontype.metronome;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.note.BeamType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "metronome_beam")
public class MetronomeBeam extends DatabaseItem {
    @Enumerated(EnumType.STRING)
    @Column(name = "beam_type")
    private BeamType beamType;
    @Column
    private Integer number = 1;

    public MetronomeBeam() {

    }

    public BeamType getBeamType() {
        return beamType;
    }

    public void setBeamType(BeamType beamType) {
        this.beamType = beamType;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
