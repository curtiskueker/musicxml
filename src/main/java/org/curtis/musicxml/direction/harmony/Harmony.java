package org.curtis.musicxml.direction.harmony;

import org.curtis.musicxml.direction.Offset;
import org.curtis.musicxml.score.MusicData;

public class Harmony extends MusicData {
    private HarmonyChord harmonyChord;
    private Frame frame;
    private Offset offset;
    // TODO: editorial
    // TODO: staff
    private String type;
    // TODO: print object
    // TODO: print frame
    // TODO: print style
    // TODO: placement

    public Harmony() {

    }

    public HarmonyChord getHarmonyChord() {
        return harmonyChord;
    }

    public void setHarmonyChord(HarmonyChord harmonyChord) {
        this.harmonyChord = harmonyChord;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
