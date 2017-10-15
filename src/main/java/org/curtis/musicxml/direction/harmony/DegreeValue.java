package org.curtis.musicxml.direction.harmony;

import org.curtis.musicxml.common.PrintStyle;

public class DegreeValue {
    private Integer value;
    private String symbol;
    private String text;
    private PrintStyle printStyle;

    public DegreeValue() {

    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }
}
