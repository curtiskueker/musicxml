package org.curtis.musicxml.note;

import org.curtis.musicxml.common.PrintStyle;

public class Accidental {
    // TODO: accidental value
    private Boolean cautionary;
    private Boolean editorial;
    // TODO: level display
    private PrintStyle printStyle;

    public Accidental() {

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

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }
}
