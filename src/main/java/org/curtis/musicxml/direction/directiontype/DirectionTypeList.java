package org.curtis.musicxml.direction.directiontype;

import org.curtis.database.DatabaseElement;
import org.curtis.musicxml.direction.Direction;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "direction_type_list")
public class DirectionTypeList extends DatabaseElement {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "direction_type_list_id")
    private List<DirectionType> directionTypes = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "direction_id")
    private Direction direction;

    public DirectionTypeList() {

    }

    public List<DirectionType> getDirectionTypes() {
        return directionTypes;
    }

    public void setDirectionTypes(List<DirectionType> directionTypes) {
        this.directionTypes = directionTypes;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
