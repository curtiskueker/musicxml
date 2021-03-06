package org.curtis.musicxml.score;

import org.curtis.database.OrderedElement;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "measure")
public class Measure extends OrderedElement {
    @Column(name = "measure_number")
    private String number;
    @Column
    private String text;
    @Type(type="yes_no")
    @Column
    private Boolean implicit;
    @Column(name = "non_controlling")
    @Type(type="yes_no")
    private Boolean nonControlling;
    @Column(precision = 12, scale = 4)
    private BigDecimal width;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "measure_id")
    @OrderBy("ordering")
    private List<MeasureItem> measureItems = new ArrayList<>();
    @Transient
    private List<MusicData> musicDataList = new ArrayList<>();

    public Measure() {
        setId(0);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getImplicit() {
        return implicit;
    }

    public void setImplicit(Boolean implicit) {
        this.implicit = implicit;
    }

    public Boolean getNonControlling() {
        return nonControlling;
    }

    public void setNonControlling(Boolean nonControlling) {
        this.nonControlling = nonControlling;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public List<MeasureItem> getMeasureItems() {
        return measureItems;
    }

    public void setMeasureItems(List<MeasureItem> measureItems) {
        this.measureItems = measureItems;
    }

    public List<MusicData> getMusicDataList() {
        return musicDataList;
    }

    public void setMusicDataList(List<MusicData> musicDataList) {
        this.musicDataList = musicDataList;
    }
}
