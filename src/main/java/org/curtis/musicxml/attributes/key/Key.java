package org.curtis.musicxml.attributes.key;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.display.Display;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "key_signature")
@DiscriminatorColumn(name = "key_type")
public abstract class Key extends DatabaseItem {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "key_id", nullable = false)
    private List<KeyOctave> keyOctaves = new ArrayList<>();
    @Column(name = "key_number")
    private Integer number;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;
    @Column(name = "print_object")
    @Type(type="yes_no")
    private Boolean printObject;

    public List<KeyOctave> getKeyOctaves() {
        return keyOctaves;
    }

    public void setKeyOctaves(List<KeyOctave> keyOctaves) {
        this.keyOctaves = keyOctaves;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public Boolean getPrintObject() {
        return printObject;
    }

    public void setPrintObject(Boolean printObject) {
        this.printObject = printObject;
    }
}
