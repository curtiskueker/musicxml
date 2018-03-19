package org.curtis.musicxml.note.lyric;

import org.curtis.database.DatabaseItem;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "lyric_item")
@DiscriminatorColumn(name = "lyric_item_type")
public abstract class LyricItem extends DatabaseItem {

}
