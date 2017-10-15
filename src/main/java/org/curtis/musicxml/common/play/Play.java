package org.curtis.musicxml.common.play;

import java.util.List;

public class Play {
    private List<PlayType> playTypes;
    private String id;

    public Play() {

    }

    public List<PlayType> getPlayTypes() {
        return playTypes;
    }

    public void setPlayTypes(List<PlayType> playTypes) {
        this.playTypes = playTypes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
