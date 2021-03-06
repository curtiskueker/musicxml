package org.curtis.musicxml.note;

import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.score.MusicData;

import java.util.ArrayList;
import java.util.List;

public class Chord extends MusicData {
    private List<Note> notes = new ArrayList<>();
    private List<Direction> directions = new ArrayList<>();
    private String voice;
    private List<Notations> notationsList = new ArrayList<>();

    public Chord() {

    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public List<Direction> getDirections() {
        return directions;
    }

    public void setDirections(List<Direction> directions) {
        this.directions = directions;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public List<Notations> getNotationsList() {
        return notationsList;
    }

    public void setNotationsList(List<Notations> notationsList) {
        this.notationsList = notationsList;
    }
}
