package org.curtis.musicxml.direction.type.percussion;

import org.curtis.musicxml.direction.type.DirectionType;

public abstract class Percussion extends DirectionType {
    private String otherPercussion;
    // TODO: print style align
    // TODO: enclosure

    public String getOtherPercussion() {
        return otherPercussion;
    }

    public void setOtherPercussion(String otherPercussion) {
        this.otherPercussion = otherPercussion;
    }
}
