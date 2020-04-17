package org.curtis.musicxml.note;

import org.curtis.database.DatabaseItem;


import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "note_type")
@DiscriminatorColumn(name = "note_type_type")
public abstract class NoteType extends DatabaseItem {
}
