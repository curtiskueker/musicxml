package org.curtis.musicxml.note;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.LevelDisplay;
import org.curtis.musicxml.common.PrintStyle;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "accidental")
public class Accidental extends DatabaseItem {
    @Enumerated(EnumType.STRING)
    @Column(name = "accidental_type")
    private AccidentalType accidentalType;
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
    @JoinColumn(name = "print_style_id")
    private PrintStyle printStyle;
    @Column
    private String smufl;

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

    public String getSmufl() {
        return smufl;
    }

    public void setSmufl(String smufl) {
        this.smufl = smufl;
    }
}
