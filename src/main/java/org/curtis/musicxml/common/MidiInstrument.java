package org.curtis.musicxml.common;

import java.math.BigDecimal;

public class MidiInstrument {
    private Integer midiChannel;
    private String midiName;
    private Integer midiBank;
    private Integer midiProgram;
    private Integer midiUnpitched;
    private BigDecimal volume;
    private BigDecimal pan;
    private BigDecimal elevation;
    private String id;

    public MidiInstrument() {

    }

    public Integer getMidiChannel() {
        return midiChannel;
    }

    public void setMidiChannel(Integer midiChannel) {
        this.midiChannel = midiChannel;
    }

    public String getMidiName() {
        return midiName;
    }

    public void setMidiName(String midiName) {
        this.midiName = midiName;
    }

    public Integer getMidiBank() {
        return midiBank;
    }

    public void setMidiBank(Integer midiBank) {
        this.midiBank = midiBank;
    }

    public Integer getMidiProgram() {
        return midiProgram;
    }

    public void setMidiProgram(Integer midiProgram) {
        this.midiProgram = midiProgram;
    }

    public Integer getMidiUnpitched() {
        return midiUnpitched;
    }

    public void setMidiUnpitched(Integer midiUnpitched) {
        this.midiUnpitched = midiUnpitched;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
