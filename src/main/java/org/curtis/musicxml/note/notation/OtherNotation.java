package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.converter.ConnectionConverter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("other notation")
public class OtherNotation extends Notation {
    @Column
    private String value;
    @Convert(converter = ConnectionConverter.class)
    @Column
    private Connection type;
    @Column(name = "notation_number")
    private Integer number = 1;
    @Column(name = "print_object")
    @Type(type="yes_no")
    private Boolean printObject;
    @Column
    private String smufl;

    public OtherNotation() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Connection getType() {
        return type;
    }

    public void setType(Connection type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getPrintObject() {
        return printObject;
    }

    public void setPrintObject(Boolean printObject) {
        this.printObject = printObject;
    }

    public String getSmufl() {
        return smufl;
    }

    public void setSmufl(String smufl) {
        this.smufl = smufl;
    }
}
