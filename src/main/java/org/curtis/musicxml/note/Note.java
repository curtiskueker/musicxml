package org.curtis.musicxml.note;

import org.curtis.musicxml.note.lyric.Lyric;
import org.curtis.musicxml.score.MusicData;

import java.math.BigDecimal;
import java.util.List;

public class Note extends MusicData {
    private Grace grace;
    // TODO: cue
    private FullNote fullNote;
    private BigDecimal duration;
    private List<Tie> ties;
    private String instrument;
    // TODO: editorial voice
    private NoteType type;
    private List<EmptyPlacement> dots;
    private Accidental accidental;
    private TimeModification timeModification;
    private Stem stem;
    private Notehead notehead;
    private NoteheadText noteheadText;
    // TODO: staff
    private List<Beam> beams;
    private List<Notations> notationsList;
    private List<Lyric> lyrics;
    // TODO: play
    private XPosition xPosition;
    // TODO: font
    // TODO: color
    // TODO: printout
    // TODO: dynamics
    // TODO: end dynamics
    // TODO: attack
    // TODO: release
    // TODO: time only
    // TODO: pizzicato

    public Note() {

    }

    public Grace getGrace() {
        return grace;
    }

    public void setGrace(Grace grace) {
        this.grace = grace;
    }

    public FullNote getFullNote() {
        return fullNote;
    }

    public void setFullNote(FullNote fullNote) {
        this.fullNote = fullNote;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    public List<Tie> getTies() {
        return ties;
    }

    public void setTies(List<Tie> ties) {
        this.ties = ties;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public NoteType getType() {
        return type;
    }

    public void setType(NoteType type) {
        this.type = type;
    }

    public List<EmptyPlacement> getDots() {
        return dots;
    }

    public void setDots(List<EmptyPlacement> dots) {
        this.dots = dots;
    }

    public Accidental getAccidental() {
        return accidental;
    }

    public void setAccidental(Accidental accidental) {
        this.accidental = accidental;
    }

    public TimeModification getTimeModification() {
        return timeModification;
    }

    public void setTimeModification(TimeModification timeModification) {
        this.timeModification = timeModification;
    }

    public Stem getStem() {
        return stem;
    }

    public void setStem(Stem stem) {
        this.stem = stem;
    }

    public Notehead getNotehead() {
        return notehead;
    }

    public void setNotehead(Notehead notehead) {
        this.notehead = notehead;
    }

    public NoteheadText getNoteheadText() {
        return noteheadText;
    }

    public void setNoteheadText(NoteheadText noteheadText) {
        this.noteheadText = noteheadText;
    }

    public List<Beam> getBeams() {
        return beams;
    }

    public void setBeams(List<Beam> beams) {
        this.beams = beams;
    }

    public List<Notations> getNotationsList() {
        return notationsList;
    }

    public void setNotationsList(List<Notations> notationsList) {
        this.notationsList = notationsList;
    }

    public List<Lyric> getLyrics() {
        return lyrics;
    }

    public void setLyrics(List<Lyric> lyrics) {
        this.lyrics = lyrics;
    }

    public XPosition getxPosition() {
        return xPosition;
    }

    public void setxPosition(XPosition xPosition) {
        this.xPosition = xPosition;
    }
}
