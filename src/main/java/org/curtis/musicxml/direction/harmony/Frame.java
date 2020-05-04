package org.curtis.musicxml.direction.harmony;

import org.curtis.database.DatabaseElement;
import org.curtis.musicxml.display.Display;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "frame")
public class Frame extends DatabaseElement {
    @Column(name = "frame_strings")
    private Integer frameStrings;
    @Column(name = "frame_frets")
    private Integer frameFrets;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "first_fret_id")
    private FirstFret firstFret;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "frame_id", nullable = false)
    @OrderBy("ordering")
    private List<FrameNote> frameNotes = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;
    @Column(precision = 12, scale = 4)
    private BigDecimal height;
    @Column(precision = 12, scale = 4)
    private BigDecimal width;
    @Column
    private String unplayed;

    public Frame() {

    }

    public Integer getFrameStrings() {
        return frameStrings;
    }

    public void setFrameStrings(Integer frameStrings) {
        this.frameStrings = frameStrings;
    }

    public Integer getFrameFrets() {
        return frameFrets;
    }

    public void setFrameFrets(Integer frameFrets) {
        this.frameFrets = frameFrets;
    }

    public FirstFret getFirstFret() {
        return firstFret;
    }

    public void setFirstFret(FirstFret firstFret) {
        this.firstFret = firstFret;
    }

    public List<FrameNote> getFrameNotes() {
        return frameNotes;
    }

    public void setFrameNotes(List<FrameNote> frameNotes) {
        this.frameNotes = frameNotes;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public String getUnplayed() {
        return unplayed;
    }

    public void setUnplayed(String unplayed) {
        this.unplayed = unplayed;
    }
}
