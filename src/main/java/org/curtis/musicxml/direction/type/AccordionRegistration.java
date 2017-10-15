package org.curtis.musicxml.direction.type;

import org.curtis.musicxml.common.PrintStyleAlign;

public class AccordionRegistration {
    // TODO: accordion high
    private Integer accordionMiddle;
    // TODO: accordion low
    private PrintStyleAlign printStyleAlign;

    public AccordionRegistration() {

    }

    public Integer getAccordionMiddle() {
        return accordionMiddle;
    }

    public void setAccordionMiddle(Integer accordionMiddle) {
        this.accordionMiddle = accordionMiddle;
    }

    public PrintStyleAlign getPrintStyleAlign() {
        return printStyleAlign;
    }

    public void setPrintStyleAlign(PrintStyleAlign printStyleAlign) {
        this.printStyleAlign = printStyleAlign;
    }
}
