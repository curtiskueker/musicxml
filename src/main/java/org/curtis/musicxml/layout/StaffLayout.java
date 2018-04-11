package org.curtis.musicxml.layout;

import org.curtis.database.DatabaseItem;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@Table(name = "staff_layout")
public class StaffLayout extends DatabaseItem {
    @Transient
    private BigDecimal staffDistance;
    @Transient
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
