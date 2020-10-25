package org.curtis.musicxml.direction.directiontype.metronome;

import org.curtis.database.OrderedItem;
import org.curtis.musicxml.converter.BeamTypeConverter;
import org.curtis.musicxml.note.BeamType;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "metronome_beam")
public class MetronomeBeam extends OrderedItem {
    @Convert(converter = BeamTypeConverter.class)
    @Column
    private BeamType value;
    @Column(name = "metronome_beam_number")
    private Integer number = 1;

    public MetronomeBeam() {

    }

    public BeamType getValue() {
        return value;
    }

    public void setValue(BeamType value) {
        this.value = value;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
