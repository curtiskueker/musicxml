package org.curtis.musicxml.direction.harmony;

import org.curtis.musicxml.common.Editorial;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.direction.Offset;
import org.curtis.musicxml.score.MusicData;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("harmony")
public class Harmony extends MusicData {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "harmony_id", nullable = false)
    private List<HarmonyChord> harmonyChords = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "frame_id")
    private Frame frame;
    @Transient
    private Offset offset;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "editorial_id")
    private Editorial editorial;
    @Column
    private Integer staff;
    @Enumerated(EnumType.STRING)
    @Column
    private HarmonyType type;
    @Column(name = "print_object")
    private Boolean printObject;
    @Column(name = "print_frame")
    private Boolean printFrame;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "print_style_id")
    private PrintStyle printStyle;
    @Enumerated(EnumType.STRING)
    @Column
    private Location placement;
    @Transient
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

    public Offset getOffset() {
        return offset;
    }

    public void setOffset(Offset offset) {
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

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }

    public Location getPlacement() {
        return placement;
    }

    public void setPlacement(Location placement) {
        this.placement = placement;
    }

    public BigDecimal getTotalBeats() {
        return totalBeats;
    }

    public void setTotalBeats(BigDecimal totalBeats) {
        this.totalBeats = totalBeats;
    }
}
