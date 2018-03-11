package org.curtis.musicxml.direction.directiontype;

import org.curtis.database.DatabaseItem;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "direction_type")
@DiscriminatorColumn(name = "direction_type")
public abstract class DirectionType extends DatabaseItem {
    public static List<String> MULTIPLE_DIRECTION_TYPES = Arrays.asList("Dynamics", "Wedge");
}
