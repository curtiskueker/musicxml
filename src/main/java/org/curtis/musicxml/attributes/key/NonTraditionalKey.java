package org.curtis.musicxml.attributes.key;

import java.util.ArrayList;
import java.util.List;

public class NonTraditionalKey extends Key {
    private List<NonTraditionalKeyType> nonTraditionalKeyList = new ArrayList<>();

    public NonTraditionalKey() {

    }

    public List<NonTraditionalKeyType> getNonTraditionalKeyList() {
        return nonTraditionalKeyList;
    }

    public void setNonTraditionalKeyList(List<NonTraditionalKeyType> nonTraditionalKeyList) {
        this.nonTraditionalKeyList = nonTraditionalKeyList;
    }
}
