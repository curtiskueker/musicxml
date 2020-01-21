package org.curtis.musicxml.direction.directiontype.percussion;

import org.curtis.musicxml.common.EnclosureShape;
import org.curtis.musicxml.direction.directiontype.DirectionType;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Percussion extends DirectionType {
    @Enumerated(EnumType.STRING)
    @Column
    private EnclosureShape enclosure;

    public EnclosureShape getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(EnclosureShape enclosure) {
        this.enclosure = enclosure;
    }
}
