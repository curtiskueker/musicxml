package org.curtis.musicxml.score;

import org.curtis.database.DatabaseItem;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
@Table(name = "measure")
public class Measure extends DatabaseItem {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "measure_id", nullable = false)
    private List<MusicData> musicDataList = new ArrayList<>();
    @Column
    private String number;
    @Column
    private Boolean implicit = false;
    @Column(name = "non_controlling")
    private Boolean nonControlling;
    @Column
    private BigDecimal width;
    @Transient
    // transient lilypond
    private Integer staffNumber;
    @Transient
    // transient lilypond
    private SortedSet<String> voices = new TreeSet<>();

    public Measure() {
        setId(0);
    }

    public List<MusicData> getMusicDataList() {
        return musicDataList;
    }

    public void setMusicDataList(List<MusicData> musicDataList) {
        this.musicDataList = musicDataList;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public Integer getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(Integer staffNumber) {
        this.staffNumber = staffNumber;
    }

    public SortedSet<String> getVoices() {
        return voices;
    }

    public void setVoices(SortedSet<String> voices) {
        this.voices = voices;
    }
}
