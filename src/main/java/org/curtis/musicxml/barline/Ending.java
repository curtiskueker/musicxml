package org.curtis.musicxml.barline;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.common.PrintStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@Table
public class Ending extends DatabaseItem {
    @Column
    private String value;
    @Column
    private String number;
    @Enumerated(EnumType.STRING)
    @Column
    private Connection type;
    @Transient
    private Boolean printObject;
    @Transient
    private PrintStyle printStyle;
    @Transient
    private BigDecimal endLength;
    @Transient
    private BigDecimal textX;
    @Transient
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
