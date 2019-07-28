package org.curtis.musicxml.score;

import org.curtis.database.OrderedItem;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "music_data")
@DiscriminatorColumn(name = "music_data_type")
public abstract class MusicData extends OrderedItem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "measure_id")
    private Measure measure;

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }
}
