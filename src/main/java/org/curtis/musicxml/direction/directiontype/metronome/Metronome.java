package org.curtis.musicxml.direction.directiontype.metronome;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyleAlign;
import org.curtis.musicxml.direction.directiontype.DirectionType;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public abstract class Metronome extends DirectionType {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "print_style_align_id")
    private PrintStyleAlign printStyleAlign;
    @Enumerated(EnumType.STRING)
    @Column
    private Location justify;
    @Column
    private Boolean parentheses;

    public PrintStyleAlign getPrintStyleAlign() {
        return printStyleAlign;
    }

    public void setPrintStyleAlign(PrintStyleAlign printStyleAlign) {
        this.printStyleAlign = printStyleAlign;
    }

    public Location getJustify() {
        return justify;
    }

    public void setJustify(Location justify) {
        this.justify = justify;
    }

    public Boolean getParentheses() {
        return parentheses;
    }

    public void setParentheses(Boolean parentheses) {
        this.parentheses = parentheses;
    }
}
