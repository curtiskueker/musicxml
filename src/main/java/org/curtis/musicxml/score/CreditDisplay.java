package org.curtis.musicxml.score;

import org.curtis.musicxml.display.FormattedDisplay;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "credit_display")
@DiscriminatorColumn(name = "credit_display_type")
public abstract class CreditDisplay extends FormattedDisplay {
    @Column(name = "element_id")
    private String elementId;

    public CreditDisplay() {

    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }
}
