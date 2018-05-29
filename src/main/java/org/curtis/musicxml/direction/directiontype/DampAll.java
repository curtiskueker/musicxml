package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.common.PrintStyleAlign;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("damp all")
public class DampAll extends DirectionType {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "print_style_align_id")
    private PrintStyleAlign printStyleAlign;

    public DampAll() {

    }

    public PrintStyleAlign getPrintStyleAlign() {
        return printStyleAlign;
    }

    public void setPrintStyleAlign(PrintStyleAlign printStyleAlign) {
        this.printStyleAlign = printStyleAlign;
    }
}
