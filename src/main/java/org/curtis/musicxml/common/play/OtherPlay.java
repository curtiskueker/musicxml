package org.curtis.musicxml.common.play;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("other play")
public class OtherPlay extends PlayType {
    @Column
    private String value;
    @Column
    private String type;

    public OtherPlay() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
