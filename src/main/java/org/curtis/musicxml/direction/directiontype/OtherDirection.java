package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.common.PrintStyleAlign;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("other direction")
public class OtherDirection extends DirectionType {
    @Column
    private String value;
    @Column(name = "print_object")
    @Type(type="yes_no")
    private Boolean printObject;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "print_style_align_id")
    private PrintStyleAlign printStyleAlign;
    @Column
    private String smufl;

    public OtherDirection() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getPrintObject() {
        return printObject;
    }

    public void setPrintObject(Boolean printObject) {
        this.printObject = printObject;
    }

    public PrintStyleAlign getPrintStyleAlign() {
        return printStyleAlign;
    }

    public void setPrintStyleAlign(PrintStyleAlign printStyleAlign) {
        this.printStyleAlign = printStyleAlign;
    }

    public String getSmufl() {
        return smufl;
    }

    public void setSmufl(String smufl) {
        this.smufl = smufl;
    }
}
