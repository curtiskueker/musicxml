package org.curtis.musicxml.score;

import org.curtis.musicxml.common.Connection;

public abstract class MusicData {
    private Integer staffNumber;
    private Connection polyphonicVoiceType;

    public Integer getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(Integer staffNumber) {
        this.staffNumber = staffNumber;
    }

    public Connection getPolyphonicVoiceType() {
        return polyphonicVoiceType;
    }

    public void setPolyphonicVoiceType(Connection polyphonicVoiceType) {
        this.polyphonicVoiceType = polyphonicVoiceType;
    }
}
