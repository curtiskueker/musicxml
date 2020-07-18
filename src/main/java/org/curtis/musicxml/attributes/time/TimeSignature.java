package org.curtis.musicxml.attributes.time;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("time signature")
public class TimeSignature extends Time {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "time_signature_id")
    @OrderBy("ordering")
    private List<TimeSignatureType> timeSignatureList = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "interchangeable_id")
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
