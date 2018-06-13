package org.curtis.musicxml.direction;

import org.curtis.musicxml.common.MidiDevice;
import org.curtis.musicxml.common.MidiInstrument;
import org.curtis.musicxml.common.play.Play;
import org.curtis.musicxml.score.MusicData;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("sound")
public class Sound extends MusicData {
    @Transient
    private List<MidiDevice> midiDevices = new ArrayList<>();
    @Transient
    private List<MidiInstrument> midiInstruments = new ArrayList<>();
    @Transient
    private List<Play> playList = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "offset_id")
    private Offset offset;
    @Transient
    private BigDecimal tempo;
    @Transient
    private BigDecimal dynamics;
    @Column
    private Boolean dacapo;
    @Column
    private String segno;
    @Column
    private String dalsegno;
    @Column
    private String coda;
    @Column
    private String tocoda;
    @Column
    private BigDecimal divisions;
    @Column(name = "forward_repeat")
    private Boolean forwardRepeat;
    @Column
    private String fine;
    @Column(name = "time_only")
    private String timeOnly;
    @Column
    private Boolean pizzicato;
    @Column
    private BigDecimal pan;
    @Column
    private BigDecimal elevation;
    @Column(name = "damper_pedal")
    private String damperPedal;
    @Column(name = "soft_pedal")
    private String softPedal;
    @Column(name = "sostenuto_pedal")
    private String sostenutoPedal;

    public Sound() {

    }

    public List<MidiDevice> getMidiDevices() {
        return midiDevices;
    }

    public void setMidiDevices(List<MidiDevice> midiDevices) {
        this.midiDevices = midiDevices;
    }

    public List<MidiInstrument> getMidiInstruments() {
        return midiInstruments;
    }

    public void setMidiInstruments(List<MidiInstrument> midiInstruments) {
        this.midiInstruments = midiInstruments;
    }

    public List<Play> getPlayList() {
        return playList;
    }

    public void setPlayList(List<Play> playList) {
        this.playList = playList;
    }

    public Offset getOffset() {
        return offset;
    }

    public void setOffset(Offset offset) {
        this.offset = offset;
    }

    public BigDecimal getTempo() {
        return tempo;
    }

    public void setTempo(BigDecimal tempo) {
        this.tempo = tempo;
    }

    public BigDecimal getDynamics() {
        return dynamics;
    }

    public void setDynamics(BigDecimal dynamics) {
        this.dynamics = dynamics;
    }

    public Boolean getDacapo() {
        return dacapo;
    }

    public void setDacapo(Boolean dacapo) {
        this.dacapo = dacapo;
    }

    public String getSegno() {
        return segno;
    }

    public void setSegno(String segno) {
        this.segno = segno;
    }

    public String getDalsegno() {
        return dalsegno;
    }

    public void setDalsegno(String dalsegno) {
        this.dalsegno = dalsegno;
    }

    public String getCoda() {
        return coda;
    }

    public void setCoda(String coda) {
        this.coda = coda;
    }

    public String getTocoda() {
        return tocoda;
    }

    public void setTocoda(String tocoda) {
        this.tocoda = tocoda;
    }

    public BigDecimal getDivisions() {
        return divisions;
    }

    public void setDivisions(BigDecimal divisions) {
        this.divisions = divisions;
    }

    public Boolean getForwardRepeat() {
        return forwardRepeat;
    }

    public void setForwardRepeat(Boolean forwardRepeat) {
        this.forwardRepeat = forwardRepeat;
    }

    public String getFine() {
        return fine;
    }

    public void setFine(String fine) {
        this.fine = fine;
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

    public BigDecimal getPan() {
        return pan;
    }

    public void setPan(BigDecimal pan) {
        this.pan = pan;
    }

    public BigDecimal getElevation() {
        return elevation;
    }

    public void setElevation(BigDecimal elevation) {
        this.elevation = elevation;
    }

    public String getDamperPedal() {
        return damperPedal;
    }

    public void setDamperPedal(String damperPedal) {
        this.damperPedal = damperPedal;
    }

    public String getSoftPedal() {
        return softPedal;
    }

    public void setSoftPedal(String softPedal) {
        this.softPedal = softPedal;
    }

    public String getSostenutoPedal() {
        return sostenutoPedal;
    }

    public void setSostenutoPedal(String sostenutoPedal) {
        this.sostenutoPedal = sostenutoPedal;
    }
}
