package org.curtis.musicxml.common;

import org.curtis.database.DatabaseItem;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "text_display")
@DiscriminatorColumn(name = "text_type")
public abstract class TextDisplay extends DatabaseItem {
}
