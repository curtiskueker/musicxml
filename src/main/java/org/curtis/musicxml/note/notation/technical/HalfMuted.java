package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.note.PrintPlacement;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("half muted")
public class HalfMuted extends Technical {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "print_placement_id")
    private PrintPlacement printPlacement;
    @Column
    private String smufl;

    public HalfMuted() {

    }

    public PrintPlacement getPrintPlacement() {
        return printPlacement;
    }

    public void setPrintPlacement(PrintPlacement printPlacement) {
        this.printPlacement = printPlacement;
    }

    public String getSmufl() {
        return smufl;
    }

    public void setSmufl(String smufl) {
        this.smufl = smufl;
    }
}
