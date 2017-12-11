package org.curtis.musicxml.score;

import org.curtis.musicxml.common.MidiDevice;
import org.curtis.musicxml.common.MidiInstrument;
import org.curtis.musicxml.common.NameDisplay;
import org.curtis.musicxml.identity.Identification;
import org.curtis.musicxml.score.instrument.ScoreInstrument;

import java.util.ArrayList;
import java.util.List;

public class ScorePart extends PartItem {
    private String id;
    private Identification identification;
    private PartName partName;
    private NameDisplay partNameDisplay;
    private PartName partAbbreviation;
    private NameDisplay partAbbreviationDisplay;
    private List<String> groups;
    private List<ScoreInstrument> scoreInstruments;
    private List<MidiDevice> midiDevices;
    private List<MidiInstrument> midiInstruments = new ArrayList<>();

    public ScorePart() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
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
