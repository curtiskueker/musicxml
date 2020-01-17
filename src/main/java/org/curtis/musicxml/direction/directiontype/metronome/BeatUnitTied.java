package org.curtis.musicxml.direction.directiontype.metronome;

import org.curtis.database.DatabaseItem;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "beat_unit_tied")
public class BeatUnitTied extends DatabaseItem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "beat_unit_tied_parent_id")
    private BeatUnit beatUnit;

    public BeatUnitTied() {

    }

    public BeatUnit getBeatUnit() {
        return beatUnit;
    }

    public void setBeatUnit(BeatUnit beatUnit) {
        this.beatUnit = beatUnit;
    }
}
