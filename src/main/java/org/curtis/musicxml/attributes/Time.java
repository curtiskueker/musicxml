package org.curtis.musicxml.attributes;

import java.util.List;

public class Time {
    private List<TimeSignature> timeSignatures;
    private Interchangeable interchangeable;
    private String senzaMisura;
    // TODO: number
    private String symbol;
    private String separator;
    // TODO: print style align
    // TODO: print object

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
}
