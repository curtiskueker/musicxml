package org.curtis.musicxml.direction.harmony;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyle;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@Table(name = "root_alter")
public class RootAlter extends DatabaseItem {
    @Transient
    private BigDecimal semitones;
    @Transient
    private Boolean printObject;
    @Transient
    private PrintStyle printStyle;
    @Transient
    private Location location;

    public RootAlter() {

    }

    public BigDecimal getSemitones() {
        return semitones;
    }

    public void setSemitones(BigDecimal semitones) {
        this.semitones = semitones;
    }

    public Boolean getPrintObject() {
        return printObject;
    }

    public void setPrintObject(Boolean printObject) {
        this.printObject = printObject;
    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
