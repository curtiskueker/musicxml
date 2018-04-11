package org.curtis.musicxml.layout;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.PrintStyleAlign;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "print_object_style_align")
public class PrintObjectStyleAlign extends DatabaseItem {
    @Transient
    private Boolean printObject;
    @Transient
    private PrintStyleAlign printStyleAlign;

    public PrintObjectStyleAlign() {

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
}
