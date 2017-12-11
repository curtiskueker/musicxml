package org.curtis.musicxml.score;

import java.util.ArrayList;
import java.util.List;

public class PartList {
    private List<PartItem> partItems = new ArrayList<>();

    public PartList() {

    }

    public List<PartItem> getPartItems() {
        return partItems;
    }

    public void setPartItems(List<PartItem> partItems) {
        this.partItems = partItems;
    }
}
