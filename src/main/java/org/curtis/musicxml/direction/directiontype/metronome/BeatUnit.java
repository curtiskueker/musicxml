package org.curtis.musicxml.direction.directiontype.metronome;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.note.NoteTypeValue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "beat_unit")
public class BeatUnit extends DatabaseItem {
    @Enumerated(EnumType.STRING)
    @Column(name = "beat_unit")
    private NoteTypeValue beatUnit;
    @Column(name = "beat_unit_dots")
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
