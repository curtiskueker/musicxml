package org.curtis.musicxml.score;

import org.curtis.musicxml.common.FormattedText;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("credit words")
public class CreditWords extends CreditDisplay {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "formatted_text_id")
    private FormattedText creditWords;

    public CreditWords() {

    }

    public FormattedText getCreditWords() {
        return creditWords;
    }

    public void setCreditWords(FormattedText creditWords) {
        this.creditWords = creditWords;
    }
}
