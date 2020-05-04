package org.curtis.musicxml.attributes.time;

import org.curtis.database.OrderedItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "time_signature_type")
public class TimeSignatureType extends OrderedItem {
    @Column
    private String beats;
    @Column(name = "beat_type")
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
