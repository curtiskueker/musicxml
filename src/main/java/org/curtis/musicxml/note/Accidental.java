package org.curtis.musicxml.note;

import org.curtis.musicxml.common.LevelDisplay;
import org.curtis.musicxml.common.PrintStyle;

public class Accidental {
    private AccidentalValue accidentalValue;
    private Boolean cautionary;
    private Boolean editorial;
    private LevelDisplay levelDisplay;
    private PrintStyle printStyle;

    public Accidental() {

    }

    public AccidentalValue getAccidentalValue() {
        return accidentalValue;
    }

    public void setAccidentalValue(AccidentalValue accidentalValue) {
        this.accidentalValue = accidentalValue;
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
