package org.curtis.musicxml.common;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "midi_instrument")
public class MidiInstrument extends DatabaseItem {
    @Column(name = "midi_channel")
    private Integer midiChannel;
    @Column(name = "midi_name")
    private String midiName;
    @Column(name = "midi_bank")
    private Integer midiBank;
    @Column(name = "midi_program")
    private Integer midiProgram;
    @Column(name = "midi_unpitched")
    private Integer midiUnpitched;
    @Column(precision = 12, scale = 4)
    private BigDecimal volume;
    @Column(precision = 12, scale = 4)
    private BigDecimal pan;
    @Column(precision = 12, scale = 4)
    private BigDecimal elevation;
    @Column(name = "midi_instrumentj_id")
    private String midiInstrumentId;

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

    public String getMidiInstrumentId() {
        return midiInstrumentId;
    }

    public void setMidiInstrumentId(String midiInstrumentId) {
        this.midiInstrumentId = midiInstrumentId;
    }
}
