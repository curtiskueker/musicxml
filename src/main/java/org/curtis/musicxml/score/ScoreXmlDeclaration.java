package org.curtis.musicxml.score;

import org.curtis.database.DatabaseItem;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "score_xml_declaration")
public class ScoreXmlDeclaration extends DatabaseItem {
    @Column
    private String version;
    @Column
    private String encoding;
    @Column
    @Type(type="yes_no")
    private Boolean standalone;

    public ScoreXmlDeclaration() {

    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public Boolean getStandalone() {
        return standalone;
    }

    public void setStandalone(Boolean standalone) {
        this.standalone = standalone;
    }
}
