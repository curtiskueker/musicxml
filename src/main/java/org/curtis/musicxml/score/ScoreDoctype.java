package org.curtis.musicxml.score;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "score_doctype")
public class ScoreDoctype extends DatabaseItem {
    @Column
    private String name;
    @Column(name = "public_id")
    private String publicId;
    @Column(name = "system_id")
    private String systemId;

    public ScoreDoctype() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }
}
