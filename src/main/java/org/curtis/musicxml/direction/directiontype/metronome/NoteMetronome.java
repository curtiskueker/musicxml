package org.curtis.musicxml.direction.directiontype.metronome;

import java.util.ArrayList;
import java.util.List;

public class NoteMetronome extends Metronome {
    private List<MetronomeNote> metronomeNotes1 = new ArrayList<>();
    private String metronomeRelation;
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
