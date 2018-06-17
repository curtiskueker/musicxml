package org.curtis.musicxml.common;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "midi_device")
public class MidiDevice extends DatabaseItem {
    @Column
    private String value;
    @Transient
    private Integer port;
    @Column(name = "midi_device_id")
    private String midiDeviceId;

    public MidiDevice() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getMidiDeviceId() {
        return midiDeviceId;
    }

    public void setMidiDeviceId(String midiDeviceId) {
        this.midiDeviceId = midiDeviceId;
    }
}
