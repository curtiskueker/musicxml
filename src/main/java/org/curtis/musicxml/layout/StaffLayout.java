package org.curtis.musicxml.layout;

import java.math.BigDecimal;

public class StaffLayout {
    private BigDecimal staffDistance;
    private Integer number;

    public StaffLayout() {

    }

    public BigDecimal getStaffDistance() {
        return staffDistance;
    }

    public void setStaffDistance(BigDecimal staffDistance) {
        this.staffDistance = staffDistance;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
