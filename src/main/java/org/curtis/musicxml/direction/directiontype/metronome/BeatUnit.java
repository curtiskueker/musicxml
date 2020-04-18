package org.curtis.musicxml.direction.directiontype.metronome;

import org.curtis.musicxml.note.NoteTypeValue;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("beat unit")
public class BeatUnit extends MetronomeMark {
    @Enumerated(EnumType.STRING)
    @Column(name = "beat_unit")
    private NoteTypeValue beatUnit;
    @Column(name = "beat_unit_dots")
    private Integer beatUnitDots = 0;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "beat_unit_tied_id"  )
    private List<BeatUnit> beatUnitTieds = new ArrayList<>();

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

    public List<BeatUnit> getBeatUnitTieds() {
        return beatUnitTieds;
    }

    public void setBeatUnitTieds(List<BeatUnit> beatUnitTieds) {
        this.beatUnitTieds = beatUnitTieds;
    }
}
