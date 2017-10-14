package org.curtis.musicxml.direction.type;

import java.util.List;

public class Metronome extends DirectionType {
    private BeatUnit beatUnit1;
    private PerMinute perMinute;
    private BeatUnit beatUnit2;
    private List<MetronomeNote> metronomeNotes1;
    private String metronomeRelation;
    private List<MetronomeNote> metronomeNotes2;
    // TODO: print align
    // TODO: justify
    private Boolean parentheses;

    public Metronome() {

    }

    public BeatUnit getBeatUnit1() {
        return beatUnit1;
    }

    public void setBeatUnit1(BeatUnit beatUnit1) {
        this.beatUnit1 = beatUnit1;
    }

    public PerMinute getPerMinute() {
        return perMinute;
    }

    public void setPerMinute(PerMinute perMinute) {
        this.perMinute = perMinute;
    }

    public BeatUnit getBeatUnit2() {
        return beatUnit2;
    }

    public void setBeatUnit2(BeatUnit beatUnit2) {
        this.beatUnit2 = beatUnit2;
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

    public Boolean getParentheses() {
        return parentheses;
    }

    public void setParentheses(Boolean parentheses) {
        this.parentheses = parentheses;
    }
}
