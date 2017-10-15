package org.curtis.musicxml.direction;

import org.curtis.musicxml.common.MidiDevice;
import org.curtis.musicxml.common.MidiInstrument;
import org.curtis.musicxml.common.play.Play;
import org.curtis.musicxml.score.MusicData;

import java.math.BigDecimal;
import java.util.List;

public class Sound extends MusicData {
    private List<MidiDevice> midiDevices;
    private List<MidiInstrument> midiInstruments;
    private List<Play> playList;
    private Offset offset;
    private BigDecimal tempo;
    private BigDecimal dynamics;
    private Boolean dacapo;
    private String segno;
    private String dalsegno;
    private String coda;
    private String tocoda;
    private BigDecimal divisions;
    private Boolean forwardRepeat;
    private String fine;
    private String timeOnly;
    private Boolean pizzicato;
    private BigDecimal pan;
    private BigDecimal elevation;
    private BigDecimal damperPedal;
    private BigDecimal softPedal;
    private BigDecimal sostenutoPedal;

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

    public BigDecimal getDamperPedal() {
        return damperPedal;
    }

    public void setDamperPedal(BigDecimal damperPedal) {
        this.damperPedal = damperPedal;
    }

    public BigDecimal getSoftPedal() {
        return softPedal;
    }

    public void setSoftPedal(BigDecimal softPedal) {
        this.softPedal = softPedal;
    }

    public BigDecimal getSostenutoPedal() {
        return sostenutoPedal;
    }

    public void setSostenutoPedal(BigDecimal sostenutoPedal) {
        this.sostenutoPedal = sostenutoPedal;
    }
}
