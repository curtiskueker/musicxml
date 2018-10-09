package org.curtis.musicxml.attributes;

import org.curtis.musicxml.attributes.key.Key;
import org.curtis.musicxml.attributes.measure.MeasureStyle;
import org.curtis.musicxml.attributes.time.Time;
import org.curtis.musicxml.common.Editorial;
import org.curtis.musicxml.score.MusicData;
import org.curtis.musicxml.score.PartSymbol;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("attributes")
public class Attributes extends MusicData {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "editorial_id")
    private Editorial editorial;
    @Column(precision = 12, scale = 4)
    private BigDecimal divisions;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "attributes_id", nullable = false)
    private List<Key> keys = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "attributes_id", nullable = false)
    private List<Time> timeList = new ArrayList<>();
    @Column
    private Integer staves;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "part_symbol_id")
    private PartSymbol partSymbol;
    @Column
    private Integer instruments;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "attributes_id", nullable = false)
    private List<Clef> clefs = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "attributes_id", nullable = false)
    private List<StaffDetails> staffDetailsList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "attributes_id", nullable = false)
    private List<Transpose> transpositions = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "attributes_id", nullable = false)
    private List<Directive> directives = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "attributes_id", nullable = false)
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

    public List<Clef> getClefs() {
        return clefs;
    }

    public void setClefs(List<Clef> clefs) {
        this.clefs = clefs;
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
