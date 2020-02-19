package org.curtis.musicxml.note.notation.technical;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("string number")
public class StringNumber extends Technical {
    @Column(name = "string_number")
    private Integer stringNumber;

    public StringNumber() {

    }

    public Integer getStringNumber() {
        return stringNumber;
    }

    public void setStringNumber(Integer stringNumber) {
        this.stringNumber = stringNumber;
    }
}
