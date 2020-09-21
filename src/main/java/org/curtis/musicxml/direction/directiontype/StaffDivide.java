package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.converter.StaffDivideSymbolConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("staff divide")
public class StaffDivide extends DirectionType {
    @Convert(converter = StaffDivideSymbolConverter.class)
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
