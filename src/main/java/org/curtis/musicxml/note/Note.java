package org.curtis.musicxml.note;

import org.curtis.musicxml.common.EditorialVoice;
import org.curtis.musicxml.common.Font;
import org.curtis.musicxml.common.Printout;
import org.curtis.musicxml.common.play.Play;
import org.curtis.musicxml.note.lyric.Lyric;
import org.curtis.musicxml.score.MusicData;

import java.math.BigDecimal;
import java.util.List;

public class Note extends MusicData {
    private Grace grace;
    private Boolean cue;
    private FullNote fullNote;
    private BigDecimal duration;
    private List<Tie> ties;
    private String instrument;
    private EditorialVoice editorialVoice;
    private NoteType type;
    private List<EmptyPlacement> dots;
    private Accidental accidental;
    private TimeModification timeModification;
    private Stem stem;
    private Notehead notehead;
    private NoteheadText noteheadText;
    private Integer staff;
    private List<Beam> beams;
    private List<Notations> notationsList;
    private List<Lyric> lyrics;
    private Play play;
    private XPosition xPosition;
    private Font font;
    private String color;
    private Printout printout;
    private BigDecimal dynamics;
    private BigDecimal endDynamics;
    private BigDecimal attack;
    private BigDecimal release;
    private String timeOnly;
    private Boolean pizzicato;

    public Note() {

    }

    public Grace getGrace() {
        return grace;
    }

    public void setGrace(Grace grace) {
        this.grace = grace;
    }

    public Boolean getCue() {
        return cue;
    }

    public void setCue(Boolean cue) {
        this.cue = cue;
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

    public EditorialVoice getEditorialVoice() {
        return editorialVoice;
    }

    public void setEditorialVoice(EditorialVoice editorialVoice) {
        this.editorialVoice = editorialVoice;
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

    public Integer getStaff() {
        return staff;
    }

    public void setStaff(Integer staff) {
        this.staff = staff;
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

    public Play getPlay() {
        return play;
    }

    public void setPlay(Play play) {
        this.play = play;
    }

    public XPosition getxPosition() {
        return xPosition;
    }

    public void setxPosition(XPosition xPosition) {
        this.xPosition = xPosition;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Printout getPrintout() {
        return printout;
    }

    public void setPrintout(Printout printout) {
        this.printout = printout;
    }

    public BigDecimal getDynamics() {
        return dynamics;
    }

    public void setDynamics(BigDecimal dynamics) {
        this.dynamics = dynamics;
    }

    public BigDecimal getEndDynamics() {
        return endDynamics;
    }

    public void setEndDynamics(BigDecimal endDynamics) {
        this.endDynamics = endDynamics;
    }

    public BigDecimal getAttack() {
        return attack;
    }

    public void setAttack(BigDecimal attack) {
        this.attack = attack;
    }

    public BigDecimal getRelease() {
        return release;
    }

    public void setRelease(BigDecimal release) {
        this.release = release;
    }

    public String getTimeOnly() {
        return timeOnly;
    }

    public void setTimeOnly(String timeOnly) {
        this.timeOnly = timeOnly;
    }

    public Boolean getPizzicato() {
        return pizzicato;
    }

    public void setPizzicato(Boolean pizzicato) {
        this.pizzicato = pizzicato;
    }
}
