package org.curtis.musicxml.direction.harmony;

import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.direction.Offset;
import org.curtis.musicxml.score.MusicData;

public class Harmony extends MusicData {
    private HarmonyChord harmonyChord;
    private Frame frame;
    private Offset offset;
    // TODO: editorial
    // TODO: staff
    private String type;
    private Boolean printObject;
    private Boolean printFrame;
    private PrintStyle printStyle;
    private String placement;

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

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }
}
