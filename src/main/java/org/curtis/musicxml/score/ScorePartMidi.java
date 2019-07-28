package org.curtis.musicxml.score;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.MidiDevice;
import org.curtis.musicxml.common.MidiInstrument;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "score_midi")
public class ScorePartMidi extends DatabaseItem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "midi_device_id")
    private MidiDevice midiDevice;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "midi_instrument_id")
    private MidiInstrument midiInstrument;
    // used for grouping
    @Transient
    private String scorePartMidiId;

    public ScorePartMidi() {

    }

    public MidiDevice getMidiDevice() {
        return midiDevice;
    }

    public void setMidiDevice(MidiDevice midiDevice) {
        this.midiDevice = midiDevice;
    }

    public MidiInstrument getMidiInstrument() {
        return midiInstrument;
    }

    public void setMidiInstrument(MidiInstrument midiInstrument) {
        this.midiInstrument = midiInstrument;
    }

    public String getScorePartMidiId() {
        return scorePartMidiId;
    }

    public void setScorePartMidiId(String scorePartMidiId) {
        this.scorePartMidiId = scorePartMidiId;
    }
}
