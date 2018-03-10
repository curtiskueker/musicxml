package org.curtis.musicxml.attributes;

import org.curtis.musicxml.attributes.key.Key;
import org.curtis.musicxml.attributes.measure.MeasureStyle;
import org.curtis.musicxml.attributes.time.Time;
import org.curtis.musicxml.common.Editorial;
import org.curtis.musicxml.score.MusicData;
import org.curtis.musicxml.score.PartSymbol;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("attributes")
public class Attributes extends MusicData {
    @Transient
    private Editorial editorial;
    @Transient
    private BigDecimal divisions;
    @Transient
    private List<Key> keys = new ArrayList<>();
    @Transient
    private List<Time> timeList = new ArrayList<>();
    @Transient
    private Integer staves = 1;
    @Transient
    private PartSymbol partSymbol;
    @Transient
    private Integer instruments;
    @Transient
    private Clef clef;
    @Transient
    private List<StaffDetails> staffDetailsList = new ArrayList<>();
    @Transient
    private List<Transpose> transpositions = new ArrayList<>();
    @Transient
    private List<Directive> directives = new ArrayList<>();
    @Transient
    private List<MeasureStyle> measureStyles = new ArrayList<>();

    public Attributes() {

    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
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

    public List<Time> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<Time> timeList) {
        this.timeList = timeList;
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
