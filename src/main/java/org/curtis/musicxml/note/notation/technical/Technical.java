package org.curtis.musicxml.note.notation.technical;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.display.Display;
import org.curtis.musicxml.note.notation.Technicals;

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
@Table(name = "technical")
@DiscriminatorColumn(name = "technical_type")
public abstract class Technical extends DatabaseItem {
    @ManyToOne
    @JoinColumn(name = "technicals_id")
    private Technicals technicals;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;

    public Technicals getTechnicals() {
        return technicals;
    }

    public void setTechnicals(Technicals technicals) {
        this.technicals = technicals;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}
