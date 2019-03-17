package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.common.PrintStyleAlign;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("pedal")
public class Pedal extends DirectionType {
    @Enumerated(EnumType.STRING)
    @Column(name = "direction_type")
    private Connection type;
    @Column
    @Type(type="yes_no")
    private Boolean line;
    @Column
    @Type(type="yes_no")
    private Boolean sign;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "print_style_align_id")
    private PrintStyleAlign printStyleAlign;

    public Pedal() {

    }

    public Connection getType() {
        return type;
    }

    public void setType(Connection type) {
        this.type = type;
    }

    public Boolean getLine() {
        return line;
    }

    public void setLine(Boolean line) {
        this.line = line;
    }

    public Boolean getSign() {
        return sign;
    }

    public void setSign(Boolean sign) {
        this.sign = sign;
    }

    public PrintStyleAlign getPrintStyleAlign() {
        return printStyleAlign;
    }

    public void setPrintStyleAlign(PrintStyleAlign printStyleAlign) {
        this.printStyleAlign = printStyleAlign;
    }
}
