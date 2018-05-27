package org.curtis.musicxml.direction.harmony;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.Position;
import org.curtis.musicxml.common.Location;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "frame")
public class Frame extends DatabaseItem {
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
    private List<FrameNote> frameNotes = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id")
    private Position position;
    @Transient
    private String color;
    @Enumerated(EnumType.STRING)
    @Column
    private Location halign;
    @Enumerated(EnumType.STRING)
    @Column(name = "valign_image")
    private Location valignImage;
    @Column
    private BigDecimal height;
    @Column
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Location getHalign() {
        return halign;
    }

    public void setHalign(Location halign) {
        this.halign = halign;
    }

    public Location getValignImage() {
        return valignImage;
    }

    public void setValignImage(Location valignImage) {
        this.valignImage = valignImage;
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
