package org.curtis.musicxml.attributes.time;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("time signature")
public class TimeSignature extends Time {
    @Transient
    private List<TimeSignatureType> timeSignatureList = new ArrayList<>();
    @Transient
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
