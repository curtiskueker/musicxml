package org.curtis.musicxml.direction.type;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.EnclosureShape;
import org.curtis.musicxml.common.PrintStyleAlign;
import org.curtis.musicxml.common.TextDecoration;

import java.util.ArrayList;
import java.util.List;

public class Dynamics extends DirectionType {
    private List<DynamicsType> types = new ArrayList<>();
    private PrintStyleAlign printStyleAlign;
    private Location placement;
    private TextDecoration textDecoration;
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
