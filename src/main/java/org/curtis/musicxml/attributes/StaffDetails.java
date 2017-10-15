package org.curtis.musicxml.attributes;

import java.math.BigDecimal;
import java.util.List;

public class StaffDetails {
    private String staffType;
    private Integer staffLines;
    private List<StaffTuning> staffTunings;
    private Integer capo;
    private BigDecimal staffSize;
    // TODO: number
    private String showFrets;
    private Boolean printObject;
    // TODO: print spacing;

    public StaffDetails() {

    }

    public String getStaffType() {
        return staffType;
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }

    public Integer getStaffLines() {
        return staffLines;
    }

    public void setStaffLines(Integer staffLines) {
        this.staffLines = staffLines;
    }

    public List<StaffTuning> getStaffTunings() {
        return staffTunings;
    }

    public void setStaffTunings(List<StaffTuning> staffTunings) {
        this.staffTunings = staffTunings;
    }

    public Integer getCapo() {
        return capo;
    }

    public void setCapo(Integer capo) {
        this.capo = capo;
    }

    public BigDecimal getStaffSize() {
        return staffSize;
    }

    public void setStaffSize(BigDecimal staffSize) {
        this.staffSize = staffSize;
    }

    public String getShowFrets() {
        return showFrets;
    }

    public void setShowFrets(String showFrets) {
        this.showFrets = showFrets;
    }

    public Boolean getPrintObject() {
        return printObject;
    }

    public void setPrintObject(Boolean printObject) {
        this.printObject = printObject;
    }
}
