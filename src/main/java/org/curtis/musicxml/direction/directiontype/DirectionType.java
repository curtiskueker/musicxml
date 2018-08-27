package org.curtis.musicxml.direction.directiontype;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.direction.Direction;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "direction_type")
@DiscriminatorColumn(name = "direction_type")
public abstract class DirectionType extends DatabaseItem {
    public static List<String> MULTIPLE_DIRECTION_TYPES = Arrays.asList("Dynamics", "Wedge");
    @ManyToOne
    @JoinColumn(name = "direction_id")
    private Direction direction;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
