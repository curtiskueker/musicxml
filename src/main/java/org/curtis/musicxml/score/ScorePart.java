package org.curtis.musicxml.score;

import org.curtis.musicxml.identity.Identification;

import java.util.List;

public class ScorePart {
    private String id;
    private Identification identification;
    private PartName partName;
    // TODO: part name displays
    private List<PartName> partAbbreviations;
    // TODO: part abbreviaton displays
    private List<String> groups;
    private List<ScoreInstrument> scoreInstruments;
    // TODO: midi devices
    // TODO: midi instruments

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

    public List<PartName> getPartAbbreviations() {
        return partAbbreviations;
    }

    public void setPartAbbreviations(List<PartName> partAbbreviations) {
        this.partAbbreviations = partAbbreviations;
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
}
