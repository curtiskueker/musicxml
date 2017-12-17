package org.curtis.musicxml.attributes.key;

import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.score.MusicData;

import java.util.ArrayList;
import java.util.List;

public abstract class Key extends MusicData {
    private List<KeyOctave> keyOctaves = new ArrayList<>();
    private Integer number;
    private PrintStyle printStyle;
    private Boolean printObject;

    public List<KeyOctave> getKeyOctaves() {
        return keyOctaves;
    }

    public void setKeyOctaves(List<KeyOctave> keyOctaves) {
        this.keyOctaves = keyOctaves;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }

    public Boolean getPrintObject() {
        return printObject;
    }

    public void setPrintObject(Boolean printObject) {
        this.printObject = printObject;
    }
}
