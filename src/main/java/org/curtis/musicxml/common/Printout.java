package org.curtis.musicxml.common;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "printout")
public class Printout extends DatabaseItem {
    @Column(name = "print_object")
    private Boolean printObject;
    @Column(name = "print_dot")
    private Boolean printDot;
    @Column(name = "print_spacing")
    private Boolean printSpacing;
    @Column(name = "print_lyric")
    private Boolean printLyric;

    public Printout() {

    }

    public Boolean getPrintObject() {
        return printObject;
    }

    public void setPrintObject(Boolean printObject) {
        this.printObject = printObject;
    }

    public Boolean getPrintDot() {
        return printDot;
    }

    public void setPrintDot(Boolean printDot) {
        this.printDot = printDot;
    }

    public Boolean getPrintSpacing() {
        return printSpacing;
    }

    public void setPrintSpacing(Boolean printSpacing) {
        this.printSpacing = printSpacing;
    }

    public Boolean getPrintLyric() {
        return printLyric;
    }

    public void setPrintLyric(Boolean printLyric) {
        this.printLyric = printLyric;
    }
}
