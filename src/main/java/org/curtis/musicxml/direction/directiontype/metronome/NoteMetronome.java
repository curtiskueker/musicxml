package org.curtis.musicxml.direction.directiontype.metronome;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("note metronome")
public class NoteMetronome extends Metronome {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "note_metronome_1_id", nullable = false)
    private List<MetronomeNote> metronomeNotes1 = new ArrayList<>();
    @Column(name = "metronome_relation")
    private String metronomeRelation;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "note_metronome_2_id", nullable = false)
    private List<MetronomeNote> metronomeNotes2 = new ArrayList<>();

    public NoteMetronome() {

    }

    public List<MetronomeNote> getMetronomeNotes1() {
        return metronomeNotes1;
    }

    public void setMetronomeNotes1(List<MetronomeNote> metronomeNotes1) {
        this.metronomeNotes1 = metronomeNotes1;
    }

    public String getMetronomeRelation() {
        return metronomeRelation;
    }

    public void setMetronomeRelation(String metronomeRelation) {
        this.metronomeRelation = metronomeRelation;
    }

    public List<MetronomeNote> getMetronomeNotes2() {
        return metronomeNotes2;
    }

    public void setMetronomeNotes2(List<MetronomeNote> metronomeNotes2) {
        this.metronomeNotes2 = metronomeNotes2;
    }

}
