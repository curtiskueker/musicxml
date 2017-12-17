package org.curtis.musicxml.attributes.time;

import org.curtis.musicxml.common.PrintStyleAlign;
import org.curtis.musicxml.score.MusicData;

public abstract class Time extends MusicData {
    private Integer number;
    private TimeSymbol symbol;
    private TimeSeparator separator;
    private PrintStyleAlign printStyleAlign;
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
