package org.curtis.musicxml.common.play;

import org.curtis.database.OrderedItem;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "play_type")
@DiscriminatorColumn(name = "play_type_type")
public abstract class PlayType extends OrderedItem {

}
