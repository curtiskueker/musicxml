package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.note.PrintPlacement;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("harmon mute")
public class HarmonMute extends Technical {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "harmon_closed_id")
    private HarmonClosed harmonClosed;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "print_placement_id")
    private PrintPlacement printPlacement;

    public HarmonMute() {

    }

    public HarmonClosed getHarmonClosed() {
        return harmonClosed;
    }

    public void setHarmonClosed(HarmonClosed harmonClosed) {
        this.harmonClosed = harmonClosed;
    }

    public PrintPlacement getPrintPlacement() {
        return printPlacement;
    }

    public void setPrintPlacement(PrintPlacement printPlacement) {
        this.printPlacement = printPlacement;
    }
}
