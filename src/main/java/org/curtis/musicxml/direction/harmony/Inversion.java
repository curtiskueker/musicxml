package org.curtis.musicxml.direction.harmony;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.PrintStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Inversion extends DatabaseItem {
    @Column
    private Integer value;
    @Transient
    private PrintStyle printStyle;

    public Inversion() {

    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }
}
