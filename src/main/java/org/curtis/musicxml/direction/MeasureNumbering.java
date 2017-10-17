package org.curtis.musicxml.direction;

import org.curtis.musicxml.common.PrintStyleAlign;

public class MeasureNumbering {
    private MeasureNumberingType measureNumberingValue;
    private PrintStyleAlign printStyleAlign;

    public MeasureNumbering() {

    }

    public MeasureNumberingType getMeasureNumberingValue() {
        return measureNumberingValue;
    }

    public void setMeasureNumberingValue(MeasureNumberingType measureNumberingValue) {
        this.measureNumberingValue = measureNumberingValue;
    }

    public PrintStyleAlign getPrintStyleAlign() {
        return printStyleAlign;
    }

    public void setPrintStyleAlign(PrintStyleAlign printStyleAlign) {
        this.printStyleAlign = printStyleAlign;
    }
}
