package org.curtis.musicxml.identity.encoding;

import org.curtis.database.OrderedItem;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "encoding")
@DiscriminatorColumn(name = "encoding_type")
public abstract class Encoding extends OrderedItem {

}
