package org.curtis.musicxml.score;

import org.curtis.musicxml.common.SymbolFormatting;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("credit symbol")
public class CreditSymbol extends CreditDisplay {
    @Column
    private String smufl;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "symbol_formatting_id")
    private SymbolFormatting symbolFormatting;

    public CreditSymbol() {

    }

    public String getSmufl() {
        return smufl;
    }

    public void setSmufl(String smufl) {
        this.smufl = smufl;
    }

    public SymbolFormatting getSymbolFormatting() {
        return symbolFormatting;
    }

    public void setSymbolFormatting(SymbolFormatting symbolFormatting) {
        this.symbolFormatting = symbolFormatting;
    }
}
