package org.curtis.musicxml.direction.directiontype.metronome;

import org.curtis.database.OrderedItem;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "metronome_mark")
@DiscriminatorColumn(name = "metronome_mark_type")
public abstract class MetronomeMark extends OrderedItem {
}
