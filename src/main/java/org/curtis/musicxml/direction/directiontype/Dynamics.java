package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.common.EnclosureShape;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyleAlign;
import org.curtis.musicxml.common.TextDecoration;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("dynamics")
public class Dynamics extends DirectionType {
    @ElementCollection(targetClass = DynamicsType.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "dynamics_type", joinColumns = @JoinColumn(name = "direction_type_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "dynamics_type")
    private List<DynamicsType> types = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "print_style_align_id")
    private PrintStyleAlign printStyleAlign;
    @Enumerated(EnumType.STRING)
    @Column
    private Location placement;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "text_decoration_id")
    private TextDecoration textDecoration;
    @Enumerated(EnumType.STRING)
    @Column
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
