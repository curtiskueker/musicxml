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
@Table(name = "degree_value")
public class DegreeValue extends DatabaseItem {
    @Column
    private Integer value;
    @Enumerated(EnumType.STRING)
    @Column
    private DegreeSymbol symbol;
    @Column
    private String text;
    @Transient
    private PrintStyle printStyle;

    public DegreeValue() {

    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public DegreeSymbol getSymbol() {
        return symbol;
    }

    public void setSymbol(DegreeSymbol symbol) {
        this.symbol = symbol;
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
