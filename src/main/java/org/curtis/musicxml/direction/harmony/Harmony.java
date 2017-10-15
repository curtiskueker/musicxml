package org.curtis.musicxml.direction.harmony;

import org.curtis.musicxml.common.Editorial;
import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.direction.Offset;
import org.curtis.musicxml.score.MusicData;

import java.util.List;

public class Harmony extends MusicData {
    private List<HarmonyChord> harmonyChords;
    private Frame frame;
    private Offset offset;
    private Editorial editorial;
    private Integer staff;
    private String type;
    private Boolean printObject;
    private Boolean printFrame;
    private PrintStyle printStyle;
    private String placement;

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
