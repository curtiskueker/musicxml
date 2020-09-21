package org.curtis.musicxml.attributes.time;

import org.curtis.database.OrderedElement;
import org.curtis.musicxml.converter.TimeSeparatorConverter;
import org.curtis.musicxml.converter.TimeSymbolConverter;
import org.curtis.musicxml.display.Display;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "time")
@DiscriminatorColumn(name = "time_type")
public abstract class Time extends OrderedElement {
    @Column(name = "time_number")
    private Integer number;
    @Convert(converter = TimeSymbolConverter.class)
    @Column
    private TimeSymbol symbol;
    @Convert(converter = TimeSeparatorConverter.class)
    @Column(name = "time_separator")
    private TimeSeparator separator;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;
    @Column(name = "print_object")
    @Type(type="yes_no")
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

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public Boolean getPrintObject() {
        return printObject;
    }

    public void setPrintObject(Boolean printObject) {
        this.printObject = printObject;
    }
}
