package org.curtis.musicxml.score;

import org.curtis.database.DatabaseItem;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "music_data")
@DiscriminatorColumn(name = "music_data_type")
public abstract class MusicData extends DatabaseItem {
    @ManyToOne
    @JoinColumn(name = "measure_id", insertable = false, updatable = false)
    private Measure measure;

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }
}
