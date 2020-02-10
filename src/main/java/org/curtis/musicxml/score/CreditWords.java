package org.curtis.musicxml.score;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("credit words")
public class CreditWords extends CreditDisplay {
    public CreditWords() {

    }
}
