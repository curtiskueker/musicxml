package org.curtis.musicxml.note.notation;

import org.curtis.database.DatabaseItem;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "notation")
@DiscriminatorColumn(name = "notation_type")
public abstract class Notation extends DatabaseItem {
}
