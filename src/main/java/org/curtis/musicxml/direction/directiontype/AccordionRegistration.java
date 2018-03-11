package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.common.PrintStyleAlign;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("accordion registration")
public class AccordionRegistration extends DirectionType {
    @Transient
    private Boolean accordionHigh;
    @Transient
    private Integer accordionMiddle;
    @Transient
    private Boolean accordionLow;
    @Transient
    private PrintStyleAlign printStyleAlign;

    public AccordionRegistration() {

    }

    public Boolean getAccordionHigh() {
        return accordionHigh;
    }

    public void setAccordionHigh(Boolean accordionHigh) {
        this.accordionHigh = accordionHigh;
    }

    public Integer getAccordionMiddle() {
        return accordionMiddle;
    }

    public void setAccordionMiddle(Integer accordionMiddle) {
        this.accordionMiddle = accordionMiddle;
    }

    public Boolean getAccordionLow() {
        return accordionLow;
    }

    public void setAccordionLow(Boolean accordionLow) {
        this.accordionLow = accordionLow;
    }

    public PrintStyleAlign getPrintStyleAlign() {
        return printStyleAlign;
    }

    public void setPrintStyleAlign(PrintStyleAlign printStyleAlign) {
        this.printStyleAlign = printStyleAlign;
    }
}
