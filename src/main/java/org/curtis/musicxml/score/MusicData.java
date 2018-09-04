package org.curtis.musicxml.score;

import org.curtis.database.DatabaseItem;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
public abstract class MusicData extends DatabaseItem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "measure_id")
    private Measure measure;
    @Column
    private Integer ordering;

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }
}
