package org.curtis.musicxml.direction.harmony;

import org.curtis.musicxml.common.Position;
import org.curtis.musicxml.common.Location;

import java.math.BigDecimal;
import java.util.List;

public class Frame {
    private Integer frameStrings;
    private Integer frameFrets;
    private FirstFret firstFret;
    private List<FrameNote> frameNotes;
    private Position position;
    private String color;
    private Location halign;
    private Location valignImage;
    private BigDecimal height;
    private BigDecimal width;
    private String unployed;

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

    public String getUnployed() {
        return unployed;
    }

    public void setUnployed(String unployed) {
        this.unployed = unployed;
    }
}
