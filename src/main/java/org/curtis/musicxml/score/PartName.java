package org.curtis.musicxml.score;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.display.Display;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "part_name")
public class PartName extends DatabaseItem {
    @Column(name = "part_name")
    private String partName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;
    @Column(name = "part_name_print_object")
    @Type(type="yes_no")
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

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
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
