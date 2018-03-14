package org.curtis.musicxml.note;

import org.curtis.database.DatabaseItem;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "notehead_text")
@DiscriminatorColumn(name = "notehead_text_type")
public abstract class NoteheadText extends DatabaseItem {
}
