package org.curtis.musicxml.score;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Measure {
    private List<MusicData> musicDataList = new ArrayList<>();
    private String number;
    private Boolean implicit = false;
    private Boolean nonControlling;
    private BigDecimal width;
    private RepeatBlock repeatBlock;
    private Integer staffNumber;
    private boolean isFirstMeasure = false;
    private boolean isLastMeasure = false;

    public Measure() {

    }

    public List<MusicData> getMusicDataList() {
        return musicDataList;
    }

    public void setMusicDataList(List<MusicData> musicDataList) {
        this.musicDataList = musicDataList;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Boolean getImplicit() {
        return implicit;
    }

    public void setImplicit(Boolean implicit) {
        this.implicit = implicit;
    }

    public Boolean getNonControlling() {
        return nonControlling;
    }

    public void setNonControlling(Boolean nonControlling) {
        this.nonControlling = nonControlling;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public RepeatBlock getRepeatBlock() {
        return repeatBlock;
    }

    public void setRepeatBlock(RepeatBlock repeatBlock) {
        this.repeatBlock = repeatBlock;
    }

    public Integer getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(Integer staffNumber) {
        this.staffNumber = staffNumber;
    }

    public boolean isFirstMeasure() {
        return isFirstMeasure;
    }

    public void setFirstMeasure(boolean firstMeasure) {
        isFirstMeasure = firstMeasure;
    }

    public boolean isLastMeasure() {
        return isLastMeasure;
    }

    public void setLastMeasure(boolean lastMeasure) {
        isLastMeasure = lastMeasure;
    }
}
