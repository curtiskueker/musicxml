package org.curtis.musicxml.direction.directiontype.metronome;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("beat metronome")
public class BeatMetronome extends Metronome {
    private List<MetronomeMark> metronomeMarks = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "metronome_mark_1_id")
    private MetronomeMark metronomeMark1;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "metronome_mark_2_id")
    private MetronomeMark metronomeMark2;

    public BeatMetronome() {

    }

    public MetronomeMark getMetronomeMark1() {
        return metronomeMark1;
    }

    public void setMetronomeMark1(MetronomeMark metronomeMark1) {
        this.metronomeMark1 = metronomeMark1;
    }

    public MetronomeMark getMetronomeMark2() {
        return metronomeMark2;
    }

    public void setMetronomeMark2(MetronomeMark metronomeMark2) {
        this.metronomeMark2 = metronomeMark2;
    }
}
