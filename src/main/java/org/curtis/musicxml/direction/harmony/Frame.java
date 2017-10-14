package org.curtis.musicxml.direction.harmony;

import java.math.BigDecimal;
import java.util.List;

public class Frame {
    private Integer frameStrings;
    private Integer frameFrets;
    private FirstFret firstFret;
    private List<FrameNote> frameNotes;
    // TODO: position
    // TODO: color
    // TODO: halign
    // TODO: valign image
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
