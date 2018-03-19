package org.curtis.musicxml.note.notation.technical;

import org.curtis.database.DatabaseItem;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "technical")
@DiscriminatorColumn(name = "technical_type")
public abstract class Technical extends DatabaseItem {

}
