package org.curtis.musicxml.direction.harmony;

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
    // TODO: height
    // TODO: width
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

    public String getUnployed() {
        return unployed;
    }

    public void setUnployed(String unployed) {
        this.unployed = unployed;
    }
}
