package org.curtis.musicxml.score.instrument;

import org.curtis.database.DatabaseItem;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "instrument_type")
@DiscriminatorColumn(name = "instrument_type")
public abstract class InstrumentType extends DatabaseItem {

}
