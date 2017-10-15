package org.curtis.musicxml.attributes;

import org.curtis.musicxml.common.PrintStyleAlign;

import java.util.List;

public class Time {
    private List<TimeSignature> timeSignatures;
    private Interchangeable interchangeable;
    private String senzaMisura;
    // TODO: number
    private String symbol;
    private String separator;
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
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
