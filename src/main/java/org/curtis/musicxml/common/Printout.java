package org.curtis.musicxml.common;

import org.curtis.database.DatabaseItem;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "printout")
public class Printout extends DatabaseItem {
    @Column(name = "print_object")
    @Type(type="yes_no")
    private Boolean printObject;
    @Column(name = "print_dot")
    @Type(type="yes_no")
    private Boolean printDot;
    @Column(name = "print_spacing")
    @Type(type="yes_no")
    private Boolean printSpacing;
    @Column(name = "print_lyric")
    @Type(type="yes_no")
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
