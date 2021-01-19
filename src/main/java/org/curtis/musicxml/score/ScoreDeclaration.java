package org.curtis.musicxml.score;

import org.curtis.database.DatabaseItem;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "score_declaration")
public class ScoreDeclaration extends DatabaseItem {
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "score_xml_declaration_id")
    private ScoreXmlDeclaration scoreXmlDeclaration;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "score_doctype_id")
    private ScoreDoctype scoreDoctype;

    public ScoreDeclaration() {

    }

    public ScoreXmlDeclaration getScoreXmlDeclaration() {
        return scoreXmlDeclaration;
    }

    public void setScoreXmlDeclaration(ScoreXmlDeclaration scoreXmlDeclaration) {
        this.scoreXmlDeclaration = scoreXmlDeclaration;
    }

    public ScoreDoctype getScoreDoctype() {
        return scoreDoctype;
    }

    public void setScoreDoctype(ScoreDoctype scoreDoctype) {
        this.scoreDoctype = scoreDoctype;
    }
}
