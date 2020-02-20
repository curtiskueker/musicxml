package org.curtis.musicxml.note.notation.ornament;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.display.Display;
import org.curtis.musicxml.note.notation.Ornaments;

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
@Table(name = "ornament")
@DiscriminatorColumn(name = "ornament_type")
public abstract class Ornament extends DatabaseItem {
    @ManyToOne
    @JoinColumn(name = "ornaments_id")
    private Ornaments ornaments;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;

    public Ornaments getOrnaments() {
        return ornaments;
    }

    public void setOrnaments(Ornaments ornaments) {
        this.ornaments = ornaments;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}
