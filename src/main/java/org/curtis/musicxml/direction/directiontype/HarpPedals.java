package org.curtis.musicxml.direction.directiontype;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("harp pedals")
public class HarpPedals extends DirectionType {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "harp_pedals_id", nullable = false)
    private List<PedalTuning> pedalTunings = new ArrayList<>();

    public HarpPedals() {

    }

    public List<PedalTuning> getPedalTunings() {
        return pedalTunings;
    }

    public void setPedalTunings(List<PedalTuning> pedalTunings) {
        this.pedalTunings = pedalTunings;
    }
}
