package org.curtis.musicxml.score;

import org.curtis.database.OrderedItem;
import org.curtis.musicxml.display.NameDisplay;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "part_list_item")
@DiscriminatorColumn(name = "part_list_item_type")
public abstract class PartListItem extends OrderedItem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "name_display_id")
    private NameDisplay nameDisplay;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "abbreviation_display_id")
    private NameDisplay abbreviationDisplay;

    public NameDisplay getNameDisplay() {
        return nameDisplay;
    }

    public void setNameDisplay(NameDisplay nameDisplay) {
        this.nameDisplay = nameDisplay;
    }

    public NameDisplay getAbbreviationDisplay() {
        return abbreviationDisplay;
    }

    public void setAbbreviationDisplay(NameDisplay abbreviationDisplay) {
        this.abbreviationDisplay = abbreviationDisplay;
    }
}
