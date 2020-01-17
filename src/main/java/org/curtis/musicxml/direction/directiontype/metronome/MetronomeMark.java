package org.curtis.musicxml.direction.directiontype.metronome;

import org.curtis.database.DatabaseItem;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "metronome_mark")
public abstract class MetronomeMark extends DatabaseItem {
}
