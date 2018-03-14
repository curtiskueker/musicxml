package org.curtis.musicxml.note;

import org.curtis.database.DatabaseItem;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "full_note_type")
@DiscriminatorColumn(name = "full_note_type_name")
public abstract class FullNoteType extends DatabaseItem {

}
