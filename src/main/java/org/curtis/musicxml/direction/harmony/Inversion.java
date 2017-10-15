package org.curtis.musicxml.direction.harmony;

import org.curtis.musicxml.common.PrintStyle;

public class Inversion {
    private Integer value;
    private PrintStyle printStyle;

    public Inversion() {

    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }
}
