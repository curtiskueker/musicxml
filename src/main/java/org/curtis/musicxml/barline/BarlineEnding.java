package org.curtis.musicxml.barline;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.converter.BarlineEndingTypeConverter;
import org.curtis.musicxml.display.Display;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "barline_ending")
public class BarlineEnding extends DatabaseItem {
    @Column
    private String value;
    @Column(name = "ending_number")
    private String number;
    @Convert(converter = BarlineEndingTypeConverter.class)
    @Column
    private BarlineEndingType type;
    @Column(name = "print_object")
    @Type(type="yes_no")
    private Boolean printObject;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;
    @Column(name = "end_length", precision = 12, scale = 4)
    private BigDecimal endLength;
    @Column(name = "text_x", precision = 12, scale = 4)
    private BigDecimal textX;
    @Column(name = "text_y", precision = 12, scale = 4)
    private BigDecimal textY;

    public BarlineEnding() {

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

    public BarlineEndingType getType() {
        return type;
    }

    public void setType(BarlineEndingType type) {
        this.type = type;
    }

    public Boolean getPrintObject() {
        return printObject;
    }

    public void setPrintObject(Boolean printObject) {
        this.printObject = printObject;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
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
