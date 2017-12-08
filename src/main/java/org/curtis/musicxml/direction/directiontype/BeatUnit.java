package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.note.NoteTypeValue;

public class BeatUnit {
    private NoteTypeValue beatUnit;
    private Integer beatUnitDots;

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
