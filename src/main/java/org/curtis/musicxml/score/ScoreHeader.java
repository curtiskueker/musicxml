package org.curtis.musicxml.score;

import org.curtis.musicxml.identity.Identification;
import org.curtis.musicxml.link.LinkAttributes;

import java.util.List;

public class ScoreHeader {
    private String workNumber;
    private String workTitle;
    private LinkAttributes opus;
    private String movementNumber;
    private String movementTitle;
    private Identification identification;
    private Defaults defaults;
    private List<Credit> credits;
    private PartList partList;

    public ScoreHeader() {

    }

    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public String getWorkTitle() {
        return workTitle;
    }

    public void setWorkTitle(String workTitle) {
        this.workTitle = workTitle;
    }

    public LinkAttributes getOpus() {
        return opus;
    }

    public void setOpus(LinkAttributes opus) {
        this.opus = opus;
    }

    public String getMovementNumber() {
        return movementNumber;
    }

    public void setMovementNumber(String movementNumber) {
        this.movementNumber = movementNumber;
    }

    public String getMovementTitle() {
        return movementTitle;
    }

    public void setMovementTitle(String movementTitle) {
        this.movementTitle = movementTitle;
    }

    public Identification getIdentification() {
        return identification;
    }

    public void setIdentification(Identification identification) {
        this.identification = identification;
    }

    public Defaults getDefaults() {
        return defaults;
    }

    public void setDefaults(Defaults defaults) {
        this.defaults = defaults;
    }

    public List<Credit> getCredits() {
        return credits;
    }

    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }

    public PartList getPartList() {
        return partList;
    }

    public void setPartList(PartList partList) {
        this.partList = partList;
    }
}
