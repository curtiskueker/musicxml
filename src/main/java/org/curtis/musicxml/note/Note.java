package org.curtis.musicxml.note;

import org.curtis.lilypond.util.TypeUtil;
import org.curtis.musicxml.common.EditorialVoice;
import org.curtis.musicxml.common.Printout;
import org.curtis.musicxml.common.play.Play;
import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.display.Display;
import org.curtis.musicxml.note.lyric.Lyric;
import org.curtis.musicxml.note.notation.Ornaments;
import org.curtis.musicxml.note.notation.Slur;
import org.curtis.musicxml.note.notation.Tied;
import org.curtis.musicxml.note.notation.Tuplet;
import org.curtis.musicxml.note.notation.ornament.Tremolo;
import org.curtis.musicxml.score.MusicData;
import org.curtis.util.MathUtil;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@DiscriminatorValue("note")
public class Note extends MusicData {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "grace_id")
    private Grace grace;
    @Column
    @Type(type="yes_no")
    private Boolean cue = false;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "full_note_id")
    private FullNote fullNote;
    @Column(precision = 12, scale = 4)
    private BigDecimal duration = MathUtil.ZERO;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "note_id", nullable = false)
    private List<Tie> ties = new ArrayList<>();
    @Column
    private String instrument;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "editorial_voice_id")
    private EditorialVoice editorialVoice = new EditorialVoice();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id")
    private NoteType type;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "note_id", nullable = false)
    private List<Dot> dots = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accidental_id")
    private Accidental accidental;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "time_modification_id")
    private TimeModification timeModification;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stem_id")
    private Stem stem;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "notehead_id")
    private Notehead notehead;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "notehead_text_id")
    private NoteheadText noteheadText;
    @Column
    private Integer staff;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "note_id", nullable = false)
    private List<Beam> beams = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "note_id", nullable = false)
    private List<Notations> notationsList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "note_id", nullable = false)
    private List<Lyric> lyrics = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "play_id")
    private Play play;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "printout_id")
    private Printout printout;
    @Column
    @Type(type="yes_no")
    private Boolean printLeger;
    @Column(precision = 12, scale = 4)
    private BigDecimal dynamics;
    @Column(name = "end_dynamics", precision = 12, scale = 4)
    private BigDecimal endDynamics;
    @Column(name = "attack_length", precision = 12, scale = 4)
    private BigDecimal attack;
    @Column(name = "release_length", precision = 12, scale = 4)
    private BigDecimal release;
    @Column(name = "time_only")
    private String timeOnly;
    @Column
    @Type(type="yes_no")
    private Boolean pizzicato;
    @Transient
    // used by lilypond
    private List<Direction> directions = new ArrayList<>();
    @Transient
    // used by lilypond
    private boolean changeStaff = false;

    public Note() {

    }

    public Grace getGrace() {
        return grace;
    }

    public void setGrace(Grace grace) {
        this.grace = grace;
    }

    public boolean isGraceNote() {
        return grace != null;
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

    public List<Dot> getDots() {
        return dots;
    }

    public void setDots(List<Dot> dots) {
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

    public Tremolo getTremolo() {
        return notationsList.stream().flatMap(notations -> notations.getNotations().stream())
                .filter(notations -> notations instanceof Ornaments)
                .map(ornaments -> (Ornaments)ornaments)
                .flatMap(ornaments -> ornaments.getOrnaments().stream())
                .filter(ornament -> ornament instanceof Tremolo)
                .map(notation -> (Tremolo)notation)
                .findAny().orElse(null);
    }

    public List<Slur> getSlurs() {
        return notationsList.stream()
                .flatMap(notations -> notations.getNotations().stream())
                .filter(notation -> notation instanceof Slur)
                .map(notation -> (Slur)notation)
                .collect(Collectors.toList());
    }

    public List<Tied> getTieds() {
        return notationsList.stream()
                .flatMap(notations -> notations.getNotations().stream())
                .filter(notation -> notation instanceof Tied)
                .map(notation -> (Tied)notation)
                .collect(Collectors.toList());
    }

    public Tuplet getTuplet() {
        return notationsList.stream()
                .flatMap(notations -> notations.getNotations().stream())
                .filter(notation -> notation instanceof Tuplet)
                .map(notation -> (Tuplet)notation)
                .findAny().orElse(null);
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

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public Printout getPrintout() {
        return printout;
    }

    public void setPrintout(Printout printout) {
        this.printout = printout;
    }

    public boolean isNotPrinted() {
        return (getPrintout() != null && !TypeUtil.getBooleanDefaultYes(getPrintout().getPrintObject())) || TypeUtil.getBoolean(getCue());
    }

    public Boolean getPrintLeger() {
        return printLeger;
    }

    public void setPrintLeger(Boolean printLeger) {
        this.printLeger = printLeger;
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

    public List<Direction> getDirections() {
        return directions;
    }

    public void setDirections(List<Direction> directions) {
        this.directions = directions;
    }

    public boolean isChangeStaff() {
        return changeStaff;
    }

    public void setChangeStaff() {
        this.changeStaff = true;
    }
}
