package org.curtis.musicxml.score;

import org.curtis.musicxml.common.MidiDevice;
import org.curtis.musicxml.common.MidiInstrument;
import org.curtis.musicxml.common.NameDisplay;
import org.curtis.musicxml.identity.Identification;
import org.curtis.musicxml.score.instrument.ScoreInstrument;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("score part")
public class ScorePart extends PartItem {
    @Column(name = "score_part_id")
    private String scorePartId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "identification_id")
    private Identification identification;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "part_name_id")
    private PartName partName;
    @Transient
    // transient collection
    private NameDisplay partNameDisplay;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "part_abbreviation_id")
    private PartName partAbbreviation;
    @Transient
    // transient collection
    private NameDisplay partAbbreviationDisplay;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "score_part_id", nullable = false)
    private List<ScorePartGroup> groups = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "score_part_id", nullable = false)
    private List<ScoreInstrument> scoreInstruments = new ArrayList<>();
    @Transient
    // transient collection
    private List<MidiDevice> midiDevices = new ArrayList<>();
    @Transient
    // transient collection
    private List<MidiInstrument> midiInstruments = new ArrayList<>();

    public ScorePart() {

    }

    public String getScorePartId() {
        return scorePartId;
    }

    public void setScorePartId(String scorePartId) {
        this.scorePartId = scorePartId;
    }

    public Identification getIdentification() {
        return identification;
    }

    public void setIdentification(Identification identification) {
        this.identification = identification;
    }

    public PartName getPartName() {
        return partName;
    }

    public void setPartName(PartName partName) {
        this.partName = partName;
    }

    public NameDisplay getPartNameDisplay() {
        return partNameDisplay;
    }

    public void setPartNameDisplay(NameDisplay partNameDisplay) {
        this.partNameDisplay = partNameDisplay;
    }

    public PartName getPartAbbreviation() {
        return partAbbreviation;
    }

    public void setPartAbbreviation(PartName partAbbreviation) {
        this.partAbbreviation = partAbbreviation;
    }

    public NameDisplay getPartAbbreviationDisplay() {
        return partAbbreviationDisplay;
    }

    public void setPartAbbreviationDisplay(NameDisplay partAbbreviationDisplay) {
        this.partAbbreviationDisplay = partAbbreviationDisplay;
    }

    public List<ScorePartGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<ScorePartGroup> groups) {
        this.groups = groups;
    }

    public List<ScoreInstrument> getScoreInstruments() {
        return scoreInstruments;
    }

    public void setScoreInstruments(List<ScoreInstrument> scoreInstruments) {
        this.scoreInstruments = scoreInstruments;
    }

    public List<MidiDevice> getMidiDevices() {
        return midiDevices;
    }

    public void setMidiDevices(List<MidiDevice> midiDevices) {
        this.midiDevices = midiDevices;
    }

    public List<MidiInstrument> getMidiInstruments() {
        return midiInstruments;
    }

    public void setMidiInstruments(List<MidiInstrument> midiInstruments) {
        this.midiInstruments = midiInstruments;
    }
}
