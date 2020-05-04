package org.curtis.musicxml.direction.directiontype.metronome;

import org.curtis.database.OrderedItem;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.note.NoteTypeValue;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "metronome_note")
public class MetronomeNote extends OrderedItem {
    @Enumerated(EnumType.STRING)
    @Column(name = "metronome_type")
    private NoteTypeValue metronomeType;
    @Column(name = "metronome_dots")
    private Integer metronomeDots = 0;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "metronome_note_id", nullable = false)
    @OrderBy("ordering")
    private List<MetronomeBeam> metronomeBeams = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    @Column(name = "metronome_tied")
    private Connection metronomeTied;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "metronome_tuplet_id")
    private MetronomeTuplet metronomeTuplet;

    public MetronomeNote() {

    }

    public NoteTypeValue getMetronomeType() {
        return metronomeType;
    }

    public void setMetronomeType(NoteTypeValue metronomeType) {
        this.metronomeType = metronomeType;
    }

    public Integer getMetronomeDots() {
        return metronomeDots;
    }

    public void setMetronomeDots(Integer metronomeDots) {
        this.metronomeDots = metronomeDots;
    }

    public List<MetronomeBeam> getMetronomeBeams() {
        return metronomeBeams;
    }

    public void setMetronomeBeams(List<MetronomeBeam> metronomeBeams) {
        this.metronomeBeams = metronomeBeams;
    }

    public Connection getMetronomeTied() {
        return metronomeTied;
    }

    public void setMetronomeTied(Connection metronomeTied) {
        this.metronomeTied = metronomeTied;
    }

    public MetronomeTuplet getMetronomeTuplet() {
        return metronomeTuplet;
    }

    public void setMetronomeTuplet(MetronomeTuplet metronomeTuplet) {
        this.metronomeTuplet = metronomeTuplet;
    }
}
