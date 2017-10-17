package org.curtis.musicxml.attributes;

import java.math.BigDecimal;
import java.util.List;

public class StaffDetails {
    private StaffType staffType;
    private Integer staffLines;
    private List<StaffTuning> staffTunings;
    private Integer capo;
    private BigDecimal staffSize;
    private Integer number;
    private ShowFrets showFrets;
    private Boolean printObject;
    private Boolean printSpacing;

    public StaffDetails() {

    }

    public StaffType getStaffType() {
        return staffType;
    }

    public void setStaffType(StaffType staffType) {
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public ShowFrets getShowFrets() {
        return showFrets;
    }

    public void setShowFrets(ShowFrets showFrets) {
        this.showFrets = showFrets;
    }

    public Boolean getPrintObject() {
        return printObject;
    }

    public void setPrintObject(Boolean printObject) {
        this.printObject = printObject;
    }

    public Boolean getPrintSpacing() {
        return printSpacing;
    }

    public void setPrintSpacing(Boolean printSpacing) {
        this.printSpacing = printSpacing;
    }
}
