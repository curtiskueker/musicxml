package org.curtis.musicxml.note.notation;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.note.Notations;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "notation")
@DiscriminatorColumn(name = "notation_type")
public abstract class Notation extends DatabaseItem {
    @ManyToOne
    @JoinColumn(name = "notations_id")
    private Notations notations;

    public Notations getNotations() {
        return notations;
    }

    public void setNotations(Notations notations) {
        this.notations = notations;
    }
}
