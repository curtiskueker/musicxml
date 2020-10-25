package org.curtis.musicxml.direction.harmony;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.converter.HalignConverter;
import org.curtis.musicxml.display.Display;
import org.curtis.musicxml.display.Halign;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "root_alter")
public class RootAlter extends DatabaseItem {
    @Column(precision = 12, scale = 4)
    private BigDecimal value;
    @Column(name = "print_object")
    @Type(type="yes_no")
    private Boolean printObject;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;
    @Convert(converter = HalignConverter.class)
    @Column
    private Halign location;

    public RootAlter() {

    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Boolean getPrintObject() {
        return printObject;
    }

    public void setPrintObject(Boolean printObject) {
        this.printObject = printObject;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public Halign getLocation() {
        return location;
    }

    public void setLocation(Halign location) {
        this.location = location;
    }
}
