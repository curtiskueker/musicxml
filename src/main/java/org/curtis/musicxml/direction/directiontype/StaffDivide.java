package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.common.PrintStyleAlign;

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
    @JoinColumn(name = "print_style_align_id")
    private PrintStyleAlign printStyleAlign;

    public StaffDivide() {

    }

    public StaffDivideSymbol getStaffDivideSymbol() {
        return staffDivideSymbol;
    }

    public void setStaffDivideSymbol(StaffDivideSymbol staffDivideSymbol) {
        this.staffDivideSymbol = staffDivideSymbol;
    }

    public PrintStyleAlign getPrintStyleAlign() {
        return printStyleAlign;
    }

    public void setPrintStyleAlign(PrintStyleAlign printStyleAlign) {
        this.printStyleAlign = printStyleAlign;
    }
}
