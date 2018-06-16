package org.curtis.musicxml.layout;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "staff_layout")
public class StaffLayout extends DatabaseItem {
    @Column(name = "staff_distance")
    private BigDecimal staffDistance;
    @Column
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
