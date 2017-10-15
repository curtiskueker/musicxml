package org.curtis.musicxml.direction;

import org.curtis.musicxml.common.PrintStyleAlign;

public class MeasureNumbering {
    private String measureNumberingValue;
    private PrintStyleAlign printStyleAlign;

    public MeasureNumbering() {

    }

    public String getMeasureNumberingValue() {
        return measureNumberingValue;
    }

    public void setMeasureNumberingValue(String measureNumberingValue) {
        this.measureNumberingValue = measureNumberingValue;
    }

    public PrintStyleAlign getPrintStyleAlign() {
        return printStyleAlign;
    }

    public void setPrintStyleAlign(PrintStyleAlign printStyleAlign) {
        this.printStyleAlign = printStyleAlign;
    }
}
