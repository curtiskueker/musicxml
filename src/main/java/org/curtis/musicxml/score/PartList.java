package org.curtis.musicxml.score;

import java.util.ArrayList;
import java.util.List;

public class PartList {
    private List<PartGroup> partGroups = new ArrayList<>();
    private List<ScorePart> scoreParts = new ArrayList<>();

    public PartList() {

    }

    public List<PartGroup> getPartGroups() {
        return partGroups;
    }

    public void setPartGroups(List<PartGroup> partGroups) {
        this.partGroups = partGroups;
    }

    public List<ScorePart> getScoreParts() {
        return scoreParts;
    }

    public void setScoreParts(List<ScorePart> scoreParts) {
        this.scoreParts = scoreParts;
    }
}
