package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.display.Display;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("string number")
public class StringNumber extends Technical {
    @Column(name = "string_number")
    private Integer stringNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;

    public StringNumber() {

    }

    public Integer getStringNumber() {
        return stringNumber;
    }

    public void setStringNumber(Integer stringNumber) {
        this.stringNumber = stringNumber;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}
