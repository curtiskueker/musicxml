package org.curtis.musicxml.barline;

import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.common.PrintStyle;

import java.math.BigDecimal;

public class Ending {
    private String value;
    private String number;
    private Connection type;
    private Boolean printObject;
    private PrintStyle printStyle;
    private BigDecimal endLength;
    private BigDecimal textX;
    private BigDecimal textY;

    public Ending() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Connection getType() {
        return type;
    }

    public void setType(Connection type) {
        this.type = type;
    }

    public Boolean getPrintObject() {
        return printObject;
    }

    public void setPrintObject(Boolean printObject) {
        this.printObject = printObject;
    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }

    public BigDecimal getEndLength() {
        return endLength;
    }

    public void setEndLength(BigDecimal endLength) {
        this.endLength = endLength;
    }

    public BigDecimal getTextX() {
        return textX;
    }

    public void setTextX(BigDecimal textX) {
        this.textX = textX;
    }

    public BigDecimal getTextY() {
        return textY;
    }

    public void setTextY(BigDecimal textY) {
        this.textY = textY;
    }
}
