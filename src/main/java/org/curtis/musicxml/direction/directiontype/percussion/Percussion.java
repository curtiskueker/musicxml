package org.curtis.musicxml.direction.directiontype.percussion;

import org.curtis.musicxml.converter.EnclosureShapeConverter;
import org.curtis.musicxml.display.EnclosureShape;
import org.curtis.musicxml.direction.directiontype.DirectionType;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Percussion extends DirectionType {
    @Convert(converter = EnclosureShapeConverter.class)
    @Column
    private EnclosureShape enclosure;

    public EnclosureShape getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(EnclosureShape enclosure) {
        this.enclosure = enclosure;
    }
}
