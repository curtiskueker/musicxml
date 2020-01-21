package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.display.Display;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("accordion registration")
public class AccordionRegistration extends DirectionType {
    @Column(name = "accordion_high")
    @Type(type="yes_no")
    private Boolean accordionHigh = false;
    @Column(name = "accordion_middle")
    private Integer accordionMiddle;
    @Column(name = "accordion_low")
    @Type(type="yes_no")
    private Boolean accordionLow;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;

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

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}
