package org.curtis.musicxml.score;

import org.curtis.musicxml.common.Connection;

public abstract class MusicData {
    private Integer staffNumber;
    private Connection polyphonicVoiceStart;
    private Connection polyphonicVoiceStop;

    public Integer getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(Integer staffNumber) {
        this.staffNumber = staffNumber;
    }

    public Connection getPolyphonicVoiceStart() {
        return polyphonicVoiceStart;
    }

    public void setPolyphonicVoiceStart(Connection polyphonicVoiceStart) {
        this.polyphonicVoiceStart = polyphonicVoiceStart;
    }

    public Connection getPolyphonicVoiceStop() {
        return polyphonicVoiceStop;
    }

    public void setPolyphonicVoiceStop(Connection polyphonicVoiceStop) {
        this.polyphonicVoiceStop = polyphonicVoiceStop;
    }
}
