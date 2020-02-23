package org.curtis.musicxml.note.notation;

import org.curtis.database.DatabaseElement;
import org.curtis.musicxml.display.Display;
import org.curtis.musicxml.note.Notations;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "notation")
@DiscriminatorColumn(name = "notation_type")
public abstract class Notation extends DatabaseElement {
    @ManyToOne
    @JoinColumn(name = "notations_id")
    private Notations notations;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;

    public Notations getNotations() {
        return notations;
    }

    public void setNotations(Notations notations) {
        this.notations = notations;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}
