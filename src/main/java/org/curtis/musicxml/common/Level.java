package org.curtis.musicxml.common;

import org.curtis.database.DatabaseItem;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "level")
public class Level extends DatabaseItem {
    @Column
    private String value;
    @Column
    private Boolean reference;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "level_display_id")
    private LevelDisplay levelDisplay;

    public Level() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getReference() {
        return reference;
    }

    public void setReference(Boolean reference) {
        this.reference = reference;
    }

    public LevelDisplay getLevelDisplay() {
        return levelDisplay;
    }

    public void setLevelDisplay(LevelDisplay levelDisplay) {
        this.levelDisplay = levelDisplay;
    }
}
