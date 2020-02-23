package org.curtis.musicxml.score;

import org.curtis.database.OrderedElement;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "part_item")
@DiscriminatorColumn(name = "part_item_type")
public abstract class PartItem extends OrderedElement {

}
