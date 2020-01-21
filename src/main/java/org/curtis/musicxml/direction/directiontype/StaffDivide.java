package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.display.Display;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("staff divide")
public class StaffDivide extends DirectionType {
    @Enumerated(EnumType.STRING)
    @Column(name = "staff_divide_symbol")
    private StaffDivideSymbol staffDivideSymbol;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;

    public StaffDivide() {

    }

    public StaffDivideSymbol getStaffDivideSymbol() {
        return staffDivideSymbol;
    }

    public void setStaffDivideSymbol(StaffDivideSymbol staffDivideSymbol) {
        this.staffDivideSymbol = staffDivideSymbol;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}
