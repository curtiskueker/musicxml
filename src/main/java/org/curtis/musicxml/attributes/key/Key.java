package org.curtis.musicxml.attributes.key;

import java.util.List;

public class Key {
    private TraditionalKey traditionalKey;
    private List<NonTraditionalKey> nonTraditionalKeys;
    private List<KeyOctave> keyOctaves;
    // TODO: number
    // TODO: print style
    // TODO: print object

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
}
