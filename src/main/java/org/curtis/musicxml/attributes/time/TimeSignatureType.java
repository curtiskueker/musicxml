package org.curtis.musicxml.attributes.time;

public class TimeSignatureType {
    private String beats;
    private String beatType;

    public TimeSignatureType() {

    }

    public String getBeats() {
        return beats;
    }

    public void setBeats(String beats) {
        this.beats = beats;
    }

    public String getBeatType() {
        return beatType;
    }

    public void setBeatType(String beatType) {
        this.beatType = beatType;
    }
}
