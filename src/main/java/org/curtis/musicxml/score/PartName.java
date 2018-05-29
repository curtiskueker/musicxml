package org.curtis.musicxml.score;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.common.Location;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "part_name")
public class PartName extends DatabaseItem {
    @Column(name = "part_name")
    private String partName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "print_style_id")
    private PrintStyle partNamePrintStyle;
    @Transient
    private Boolean partNamePrintObject;
    @Enumerated(EnumType.STRING)
    @Column(name = "part_name_justify")
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
