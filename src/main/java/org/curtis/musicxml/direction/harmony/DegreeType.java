package org.curtis.musicxml.direction.harmony;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.PrintStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "degree_type")
public class DegreeType extends DatabaseItem {
    @Enumerated(EnumType.STRING)
    @Column
    private DegreeTypeValue value;
    @Column
    private String text;
    @Transient
    private PrintStyle printStyle;

    public DegreeType() {

    }

    public DegreeTypeValue getValue() {
        return value;
    }

    public void setValue(DegreeTypeValue value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }
}
