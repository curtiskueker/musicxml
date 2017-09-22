package org.curtis.musicxml.direction;

import org.curtis.musicxml.score.MusicData;

import java.util.List;

public class Grouping extends MusicData {
    private List<Feature> features;
    // TODO: type
    private String number = "1";
    private String numberOf;

    public Grouping() {

    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumberOf() {
        return numberOf;
    }

    public void setNumberOf(String numberOf) {
        this.numberOf = numberOf;
    }
}
