package org.curtis.musicxml.note;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.LevelDisplay;
import org.curtis.musicxml.common.PrintStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "accidental")
public class Accidental extends DatabaseItem {
    @Transient
    private AccidentalType accidentalType;
    @Column
    private Boolean cautionary;
    @Column
    private Boolean editorial;
    @Transient
    private LevelDisplay levelDisplay;
    @Transient
    private PrintStyle printStyle;

    public Accidental() {

    }

    public AccidentalType getAccidentalType() {
        return accidentalType;
    }

    public void setAccidentalType(AccidentalType accidentalType) {
        this.accidentalType = accidentalType;
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

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }
}
