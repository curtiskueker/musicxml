package org.curtis.musicxml.direction.directiontype;

import org.curtis.database.DatabaseElement;
import org.curtis.musicxml.display.Display;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "direction_type")
@DiscriminatorColumn(name = "direction_type_type")
public abstract class DirectionType extends DatabaseElement {
    public static List<String> MULTIPLE_DIRECTION_TYPES = Arrays.asList("Dynamics", "Wedge");
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}
