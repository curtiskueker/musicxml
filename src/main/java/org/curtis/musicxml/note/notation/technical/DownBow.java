package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.note.PrintPlacement;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("down bow")
public class DownBow extends Technical {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "print_placement_id")
    private PrintPlacement printPlacement;

    public DownBow() {

    }

    public PrintPlacement getPrintPlacement() {
        return printPlacement;
    }

    public void setPrintPlacement(PrintPlacement printPlacement) {
        this.printPlacement = printPlacement;
    }
}
