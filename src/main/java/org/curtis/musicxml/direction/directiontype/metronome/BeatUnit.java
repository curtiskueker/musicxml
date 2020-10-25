package org.curtis.musicxml.direction.directiontype.metronome;

import org.curtis.musicxml.converter.NoteTypeValueConverter;
import org.curtis.musicxml.note.NoteTypeValue;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("beat unit")
public class BeatUnit extends MetronomeMark {
    @Convert(converter = NoteTypeValueConverter.class)
    @Column
    private NoteTypeValue value;
    @Column(name = "beat_unit_dots")
    private Integer beatUnitDots = 0;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "beat_unit_tied_id")
    @OrderBy("ordering")
    private List<BeatUnit> beatUnitTieds = new ArrayList<>();

    public BeatUnit() {

    }

    public NoteTypeValue getValue() {
        return value;
    }

    public void setValue(NoteTypeValue value) {
        this.value = value;
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
