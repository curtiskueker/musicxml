package org.curtis.musicxml.attributes;

import org.curtis.musicxml.attributes.key.Key;
import org.curtis.musicxml.attributes.measure.MeasureStyle;
import org.curtis.musicxml.score.MusicData;

import java.math.BigDecimal;
import java.util.List;

public class Attributes extends MusicData {
    // TODO: editorial
    private BigDecimal divisions;
    private List<Key> keys;
    private List<Time> time;
    private Integer staves;
    private PartSymbol partSymbol;
    private Integer instruments;
    private Clef clef;
    private List<StaffDetails> staffDetailsList;
    private List<Transpose> transpositions;
    private List<Directive> directives;
    private List<MeasureStyle> measureStyles;

    public Attributes() {

    }

    public BigDecimal getDivisions() {
        return divisions;
    }

    public void setDivisions(BigDecimal divisions) {
        this.divisions = divisions;
    }

    public List<Key> getKeys() {
        return keys;
    }

    public void setKeys(List<Key> keys) {
        this.keys = keys;
    }

    public List<Time> getTime() {
        return time;
    }

    public void setTime(List<Time> time) {
        this.time = time;
    }

    public Integer getStaves() {
        return staves;
    }

    public void setStaves(Integer staves) {
        this.staves = staves;
    }

    public PartSymbol getPartSymbol() {
        return partSymbol;
    }

    public void setPartSymbol(PartSymbol partSymbol) {
        this.partSymbol = partSymbol;
    }

    public Integer getInstruments() {
        return instruments;
    }

    public void setInstruments(Integer instruments) {
        this.instruments = instruments;
    }

    public Clef getClef() {
        return clef;
    }

    public void setClef(Clef clef) {
        this.clef = clef;
    }

    public List<StaffDetails> getStaffDetailsList() {
        return staffDetailsList;
    }

    public void setStaffDetailsList(List<StaffDetails> staffDetailsList) {
        this.staffDetailsList = staffDetailsList;
    }

    public List<Transpose> getTranspositions() {
        return transpositions;
    }

    public void setTranspositions(List<Transpose> transpositions) {
        this.transpositions = transpositions;
    }

    public List<Directive> getDirectives() {
        return directives;
    }

    public void setDirectives(List<Directive> directives) {
        this.directives = directives;
    }

    public List<MeasureStyle> getMeasureStyles() {
        return measureStyles;
    }

    public void setMeasureStyles(List<MeasureStyle> measureStyles) {
        this.measureStyles = measureStyles;
    }
}
