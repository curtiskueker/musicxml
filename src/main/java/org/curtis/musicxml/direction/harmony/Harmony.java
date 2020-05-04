package org.curtis.musicxml.direction.harmony;

import org.curtis.musicxml.display.Editorial;
import org.curtis.musicxml.direction.DirectionOffset;
import org.curtis.musicxml.display.Display;
import org.curtis.musicxml.score.MusicDataElement;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("harmony")
public class Harmony extends MusicDataElement {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "harmony_id", nullable = false)
    @OrderBy("ordering")
    private List<HarmonyChord> harmonyChords = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "frame_id")
    private Frame frame;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "offset_id")
    private DirectionOffset offset;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "editorial_id")
    private Editorial editorial;
    @Column
    private Integer staff;
    @Enumerated(EnumType.STRING)
    @Column
    private HarmonyType type;
    @Column(name = "print_object")
    @Type(type="yes_no")
    private Boolean printObject;
    @Column(name = "print_frame")
    @Type(type="yes_no")
    private Boolean printFrame;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;
    @Transient
    // used by lilypond
    private BigDecimal totalBeats;

    public Harmony() {

    }

    public List<HarmonyChord> getHarmonyChords() {
        return harmonyChords;
    }

    public void setHarmonyChords(List<HarmonyChord> harmonyChords) {
        this.harmonyChords = harmonyChords;
    }

    public Frame getFrame() {
        return frame;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    public DirectionOffset getOffset() {
        return offset;
    }

    public void setOffset(DirectionOffset offset) {
        this.offset = offset;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public Integer getStaff() {
        return staff;
    }

    public void setStaff(Integer staff) {
        this.staff = staff;
    }

    public HarmonyType getType() {
        return type;
    }

    public void setType(HarmonyType type) {
        this.type = type;
    }

    public Boolean getPrintObject() {
        return printObject;
    }

    public void setPrintObject(Boolean printObject) {
        this.printObject = printObject;
    }

    public Boolean getPrintFrame() {
        return printFrame;
    }

    public void setPrintFrame(Boolean printFrame) {
        this.printFrame = printFrame;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public BigDecimal getTotalBeats() {
        return totalBeats;
    }

    public void setTotalBeats(BigDecimal totalBeats) {
        this.totalBeats = totalBeats;
    }
}
