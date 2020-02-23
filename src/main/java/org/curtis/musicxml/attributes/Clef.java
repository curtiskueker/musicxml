package org.curtis.musicxml.attributes;

import org.curtis.database.DatabaseElement;
import org.curtis.musicxml.common.SymbolSize;
import org.curtis.musicxml.display.Display;
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
@Table(name = "clef")
public class Clef extends DatabaseElement {
    @Enumerated(EnumType.STRING)
    @Column
    private ClefSign sign;
    @Column
    private Integer line;
    @Column(name = "clef_octave_change")
    private Integer clefOctaveChange;
    @Column(name = "clef_number")
    private Integer number;
    @Column
    @Type(type="yes_no")
    private Boolean additional;
    @Enumerated(EnumType.STRING)
    @Column(name = "symbol_size")
    private SymbolSize size;
    @Column(name = "after_barline")
    @Type(type="yes_no")
    private Boolean afterBarline;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;
    @Column(name = "print_object")
    @Type(type="yes_no")
    private Boolean printObject;

    public Clef() {

    }

    public ClefSign getSign() {
        return sign;
    }

    public void setSign(ClefSign sign) {
        this.sign = sign;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public Integer getClefOctaveChange() {
        return clefOctaveChange;
    }

    public void setClefOctaveChange(Integer clefOctaveChange) {
        this.clefOctaveChange = clefOctaveChange;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getAdditional() {
        return additional;
    }

    public void setAdditional(Boolean additional) {
        this.additional = additional;
    }

    public SymbolSize getSize() {
        return size;
    }

    public void setSize(SymbolSize size) {
        this.size = size;
    }

    public Boolean getAfterBarline() {
        return afterBarline;
    }

    public void setAfterBarline(Boolean afterBarline) {
        this.afterBarline = afterBarline;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public Boolean getPrintObject() {
        return printObject;
    }

    public void setPrintObject(Boolean printObject) {
        this.printObject = printObject;
    }
}
