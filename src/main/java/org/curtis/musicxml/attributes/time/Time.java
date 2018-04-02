package org.curtis.musicxml.attributes.time;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.PrintStyleAlign;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "time")
@DiscriminatorColumn(name = "time_type")
public abstract class Time extends DatabaseItem {
    @Transient
    private Integer number;
    @Enumerated(EnumType.STRING)
    @Column
    private TimeSymbol symbol;
    @Enumerated(EnumType.STRING)
    @Column(name = "time_separator")
    private TimeSeparator separator;
    @Transient
    private PrintStyleAlign printStyleAlign;
    @Transient
    private Boolean printObject;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public TimeSymbol getSymbol() {
        return symbol;
    }

    public void setSymbol(TimeSymbol symbol) {
        this.symbol = symbol;
    }

    public TimeSeparator getSeparator() {
        return separator;
    }

    public void setSeparator(TimeSeparator separator) {
        this.separator = separator;
    }

    public PrintStyleAlign getPrintStyleAlign() {
        return printStyleAlign;
    }

    public void setPrintStyleAlign(PrintStyleAlign printStyleAlign) {
        this.printStyleAlign = printStyleAlign;
    }

    public Boolean getPrintObject() {
        return printObject;
    }

    public void setPrintObject(Boolean printObject) {
        this.printObject = printObject;
    }
}
