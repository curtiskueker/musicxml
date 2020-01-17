package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.converter.CaesuraValueConverter;
import org.curtis.musicxml.note.PrintPlacement;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("caesura")
public class Caesura extends Articulation {
    @Column(name = "caesura_value")
    @Convert(converter = CaesuraValueConverter.class)
    private CaesuraValue caesuraValue;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "print_placement_id")
    private PrintPlacement printPlacement;

    public Caesura() {

    }

    public CaesuraValue getCaesuraValue() {
        return caesuraValue;
    }

    public void setCaesuraValue(CaesuraValue caesuraValue) {
        this.caesuraValue = caesuraValue;
    }

    public PrintPlacement getPrintPlacement() {
        return printPlacement;
    }

    public void setPrintPlacement(PrintPlacement printPlacement) {
        this.printPlacement = printPlacement;
    }
}
