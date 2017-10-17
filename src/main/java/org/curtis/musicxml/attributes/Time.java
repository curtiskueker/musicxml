package org.curtis.musicxml.attributes;

import org.curtis.musicxml.common.PrintStyleAlign;

import java.util.List;

public class Time {
    private List<TimeSignature> timeSignatures;
    private Interchangeable interchangeable;
    private String senzaMisura;
    private Integer number;
    private TimeSymbol symbol;
    private TimeSeparator separator;
    private PrintStyleAlign printStyleAlign;
    private Boolean printObject;

    public Time() {

    }

    public List<TimeSignature> getTimeSignatures() {
        return timeSignatures;
    }

    public void setTimeSignatures(List<TimeSignature> timeSignatures) {
        this.timeSignatures = timeSignatures;
    }

    public Interchangeable getInterchangeable() {
        return interchangeable;
    }

    public void setInterchangeable(Interchangeable interchangeable) {
        this.interchangeable = interchangeable;
    }

    public String getSenzaMisura() {
        return senzaMisura;
    }

    public void setSenzaMisura(String senzaMisura) {
        this.senzaMisura = senzaMisura;
    }

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
