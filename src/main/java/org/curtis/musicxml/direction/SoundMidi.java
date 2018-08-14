package org.curtis.musicxml.direction;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.MidiDevice;
import org.curtis.musicxml.common.MidiInstrument;
import org.curtis.musicxml.common.play.Play;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sound_midi")
public class SoundMidi extends DatabaseItem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "midi_device_id")
    private MidiDevice midiDevice;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "midi_instrument_id")
    private MidiInstrument midiInstrument;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "play_id")
    private Play play;
    // used for grouping
    @Transient
    private String soundMidiId;

    public SoundMidi() {

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

    public Play getPlay() {
        return play;
    }

    public void setPlay(Play play) {
        this.play = play;
    }

    public String getSoundMidiId() {
        return soundMidiId;
    }

    public void setSoundMidiId(String soundMidiId) {
        this.soundMidiId = soundMidiId;
    }
}
