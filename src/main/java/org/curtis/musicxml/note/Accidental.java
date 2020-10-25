package org.curtis.musicxml.note;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.converter.AccidentalTypeConverter;
import org.curtis.musicxml.display.LevelDisplay;
import org.curtis.musicxml.display.Display;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "accidental")
public class Accidental extends DatabaseItem {
    @Convert(converter = AccidentalTypeConverter.class)
    @Column
    private AccidentalType value;
    @Column
    @Type(type = "true_false")
    private Boolean cautionary;
    @Column
    @Type(type = "true_false")
    private Boolean editorial;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "level_display_id")
    private LevelDisplay levelDisplay;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;
    @Column
    private String smufl;

    public Accidental() {

    }

    public AccidentalType getValue() {
        return value;
    }

    public void setValue(AccidentalType value) {
        this.value = value;
    }

    public Boolean getCautionary() {
        return cautionary;
    }

    public void setCautionary(Boolean cautionary) {
        this.cautionary = cautionary;
    }

    public Boolean getEditorial() {
        return editorial;
    }

    public void setEditorial(Boolean editorial) {
        this.editorial = editorial;
    }

    public LevelDisplay getLevelDisplay() {
        return levelDisplay;
    }

    public void setLevelDisplay(LevelDisplay levelDisplay) {
        this.levelDisplay = levelDisplay;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public String getSmufl() {
        return smufl;
    }

    public void setSmufl(String smufl) {
        this.smufl = smufl;
    }
}
