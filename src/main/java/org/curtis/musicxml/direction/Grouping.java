package org.curtis.musicxml.direction;

import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.score.MusicData;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("grouping")
public class Grouping extends MusicData {
    @Transient
    private List<Feature> features = new ArrayList<>();
    @Transient
    private Connection type;
    @Transient
    private String number = "1";
    @Transient
    private String numberOf;

    public Grouping() {

    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public Connection getType() {
        return type;
    }

    public void setType(Connection type) {
        this.type = type;
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
