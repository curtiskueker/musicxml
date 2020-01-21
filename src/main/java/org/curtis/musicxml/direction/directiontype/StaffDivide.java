package org.curtis.musicxml.direction.directiontype;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("staff divide")
public class StaffDivide extends DirectionType {
    @Enumerated(EnumType.STRING)
    @Column(name = "staff_divide_symbol")
    private StaffDivideSymbol staffDivideSymbol;

    public StaffDivide() {

    }

    public StaffDivideSymbol getStaffDivideSymbol() {
        return staffDivideSymbol;
    }

    public void setStaffDivideSymbol(StaffDivideSymbol staffDivideSymbol) {
        this.staffDivideSymbol = staffDivideSymbol;
    }
}
