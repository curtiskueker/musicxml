package org.curtis.musicxml.attributes;

import org.curtis.database.DatabaseItem;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "staff_details")
public class StaffDetails extends DatabaseItem {
    @Enumerated(EnumType.STRING)
    @Column(name = "staff_type")
    private StaffType staffType;
    @Column(name = "staff_lines")
    private Integer staffLines;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "staff_details_id", nullable = false)
    private List<StaffTuning> staffTunings;
    @Column
    private Integer capo;
    @Column(name = "staff_size")
    private BigDecimal staffSize;
    @Column
    private Integer number;
    @Enumerated(EnumType.STRING)
    @Column(name = "show_frets")
    private ShowFrets showFrets;
    @Column(name = "print_object")
    private Boolean printObject;
    @Column(name = "print_spacing")
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
