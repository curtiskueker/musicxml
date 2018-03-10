package org.curtis.musicxml.score;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.common.Location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "part_name")
public class PartName extends DatabaseItem {
    @Column(name = "part_name")
    private String partName;
    @Transient
    private PrintStyle partNamePrintStyle;
    @Transient
    private Boolean partNamePrintObject;
    @Transient
    private Location partNameJustify;

    public PartName() {

    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public PrintStyle getPartNamePrintStyle() {
        return partNamePrintStyle;
    }

    public void setPartNamePrintStyle(PrintStyle partNamePrintStyle) {
        this.partNamePrintStyle = partNamePrintStyle;
    }

    public Boolean getPartNamePrintObject() {
        return partNamePrintObject;
    }

    public void setPartNamePrintObject(Boolean partNamePrintObject) {
        this.partNamePrintObject = partNamePrintObject;
    }

    public Location getPartNameJustify() {
        return partNameJustify;
    }

    public void setPartNameJustify(Location partNameJustify) {
        this.partNameJustify = partNameJustify;
    }
}
