package org.curtis.musicxml.common.play;

import org.curtis.database.DatabaseItem;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "play")
public class Play extends DatabaseItem {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "play_id")
    private List<PlayType> playTypes = new ArrayList<>();
    @Column(name = "play_id")
    private String playId;

    public Play() {

    }

    public List<PlayType> getPlayTypes() {
        return playTypes;
    }

    public void setPlayTypes(List<PlayType> playTypes) {
        this.playTypes = playTypes;
    }

    public String getPlayId() {
        return playId;
    }

    public void setPlayId(String playId) {
        this.playId = playId;
    }
}
