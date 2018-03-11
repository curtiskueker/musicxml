package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.EnclosureShape;
import org.curtis.musicxml.common.PrintStyleAlign;
import org.curtis.musicxml.common.TextDecoration;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("dynamics")
public class Dynamics extends DirectionType {
    @Transient
    private List<DynamicsType> types = new ArrayList<>();
    @Transient
    private PrintStyleAlign printStyleAlign;
    @Transient
    private Location placement;
    @Transient
    private TextDecoration textDecoration;
    @Transient
    private EnclosureShape enclosure;

    public Dynamics() {

    }

    public List<DynamicsType> getTypes() {
        return types;
    }

    public void setTypes(List<DynamicsType> types) {
        this.types = types;
    }

    public PrintStyleAlign getPrintStyleAlign() {
        return printStyleAlign;
    }

    public void setPrintStyleAlign(PrintStyleAlign printStyleAlign) {
        this.printStyleAlign = printStyleAlign;
    }

    public Location getPlacement() {
        return placement;
    }

    public void setPlacement(Location placement) {
        this.placement = placement;
    }

    public TextDecoration getTextDecoration() {
        return textDecoration;
    }

    public void setTextDecoration(TextDecoration textDecoration) {
        this.textDecoration = textDecoration;
    }

    public EnclosureShape getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(EnclosureShape enclosure) {
        this.enclosure = enclosure;
    }
}
