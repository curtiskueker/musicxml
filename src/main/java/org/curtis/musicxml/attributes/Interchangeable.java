package org.curtis.musicxml.attributes;

import java.util.List;

public class Interchangeable {
    private String timeRelation;
    private List<TimeSignature> timeSignatures;
    private String symbol;
    private String separator;

    public Interchangeable() {

    }

    public String getTimeRelation() {
        return timeRelation;
    }

    public void setTimeRelation(String timeRelation) {
        this.timeRelation = timeRelation;
    }

    public List<TimeSignature> getTimeSignatures() {
        return timeSignatures;
    }

    public void setTimeSignatures(List<TimeSignature> timeSignatures) {
        this.timeSignatures = timeSignatures;
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
