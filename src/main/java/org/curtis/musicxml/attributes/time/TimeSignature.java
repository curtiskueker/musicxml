package org.curtis.musicxml.attributes.time;

import java.util.ArrayList;
import java.util.List;

public class TimeSignature extends Time {
    private List<TimeSignatureType> timeSignatureList = new ArrayList<>();
    private Interchangeable interchangeable;

    public TimeSignature() {

    }

    public List<TimeSignatureType> getTimeSignatureList() {
        return timeSignatureList;
    }

    public void setTimeSignatureList(List<TimeSignatureType> timeSignatureList) {
        this.timeSignatureList = timeSignatureList;
    }

    public Interchangeable getInterchangeable() {
        return interchangeable;
    }

    public void setInterchangeable(Interchangeable interchangeable) {
        this.interchangeable = interchangeable;
    }
}
