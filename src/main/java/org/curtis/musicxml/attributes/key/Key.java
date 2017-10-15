package org.curtis.musicxml.attributes.key;

import org.curtis.musicxml.common.PrintStyle;

import java.util.List;

public class Key {
    private TraditionalKey traditionalKey;
    private List<NonTraditionalKey> nonTraditionalKeys;
    private List<KeyOctave> keyOctaves;
    // TODO: number
    private PrintStyle printStyle;
    private Boolean printObject;

    public Key() {

    }

    public TraditionalKey getTraditionalKey() {
        return traditionalKey;
    }

    public void setTraditionalKey(TraditionalKey traditionalKey) {
        this.traditionalKey = traditionalKey;
    }

    public List<NonTraditionalKey> getNonTraditionalKeys() {
        return nonTraditionalKeys;
    }

    public void setNonTraditionalKeys(List<NonTraditionalKey> nonTraditionalKeys) {
        this.nonTraditionalKeys = nonTraditionalKeys;
    }

    public List<KeyOctave> getKeyOctaves() {
        return keyOctaves;
    }

    public void setKeyOctaves(List<KeyOctave> keyOctaves) {
        this.keyOctaves = keyOctaves;
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
