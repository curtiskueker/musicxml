package org.curtis.musicxml.score.instrument;

import org.curtis.database.OrderedItem;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "score_instrument")
public class ScoreInstrument extends OrderedItem {
    @Column(name = "instrument_name")
    private String instrumentName;
    @Column(name = "instrument_abbreviation")
    private String instrumentAbbreviation;
    @Column(name = "instrument_sound")
    private String instrumentSound;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instrument_type_id")
    private InstrumentType instrumentType;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "virtual_instrument_id")
    private VirtualInstrument virtualInstrument;
    @Column(name = "score_instrument_id")
    private String scoreInstrumentId;

    public ScoreInstrument() {

    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    public String getInstrumentAbbreviation() {
        return instrumentAbbreviation;
    }

    public void setInstrumentAbbreviation(String instrumentAbbreviation) {
        this.instrumentAbbreviation = instrumentAbbreviation;
    }

    public String getInstrumentSound() {
        return instrumentSound;
    }

    public void setInstrumentSound(String instrumentSound) {
        this.instrumentSound = instrumentSound;
    }

    public InstrumentType getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(InstrumentType instrumentType) {
        this.instrumentType = instrumentType;
    }

    public VirtualInstrument getVirtualInstrument() {
        return virtualInstrument;
    }

    public void setVirtualInstrument(VirtualInstrument virtualInstrument) {
        this.virtualInstrument = virtualInstrument;
    }

    public String getScoreInstrumentId() {
        return scoreInstrumentId;
    }

    public void setScoreInstrumentId(String scoreInstrumentId) {
        this.scoreInstrumentId = scoreInstrumentId;
    }
}
