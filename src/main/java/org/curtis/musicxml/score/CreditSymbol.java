package org.curtis.musicxml.score;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("credit symbol")
public class CreditSymbol extends CreditDisplay {
    public CreditSymbol() {

    }
}
