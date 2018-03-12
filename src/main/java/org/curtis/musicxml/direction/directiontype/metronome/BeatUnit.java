package org.curtis.musicxml.direction.directiontype.metronome;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.note.NoteTypeValue;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "beat_unit")
public class BeatUnit extends DatabaseItem {
    @Transient
    private NoteTypeValue beatUnit;
    @Transient
    private Integer beatUnitDots = 0;

    public BeatUnit() {

    }

    public NoteTypeValue getBeatUnit() {
        return beatUnit;
    }

    public void setBeatUnit(NoteTypeValue beatUnit) {
        this.beatUnit = beatUnit;
    }

    public Integer getBeatUnitDots() {
        return beatUnitDots;
    }

    public void setBeatUnitDots(Integer beatUnitDots) {
        this.beatUnitDots = beatUnitDots;
    }
}
