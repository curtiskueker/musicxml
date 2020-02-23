package org.curtis.musicxml.direction;

import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.score.MusicDataElement;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("grouping")
public class Grouping extends MusicDataElement {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "grouping_id", nullable = false)
    private List<Feature> features = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    @Column
    private Connection type;
    @Column(name = "grouping_number")
    private String number = "1";
    @Column(name = "number_of")
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
