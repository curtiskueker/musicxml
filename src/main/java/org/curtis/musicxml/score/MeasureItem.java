package org.curtis.musicxml.score;

import org.curtis.database.OrderedItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "music_data")
public class MeasureItem extends OrderedItem {
    @Column(name = "music_data_type")
    private String musicDataType;

    public MeasureItem() {

    }

    public String getMusicDataType() {
        return musicDataType;
    }

    public void setMusicDataType(String musicDataType) {
        this.musicDataType = musicDataType;
    }
}
