package org.curtis.musicxml.score;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "music_data")
public class MeasureItem extends DatabaseItem {
    @Column
    private Integer ordering;
    @Column(name = "music_data_type")
    private String musicDataType;

    public MeasureItem() {

    }

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }

    public String getMusicDataType() {
        return musicDataType;
    }

    public void setMusicDataType(String musicDataType) {
        this.musicDataType = musicDataType;
    }
}
