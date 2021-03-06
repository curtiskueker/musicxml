package org.curtis.musicxml.score;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.XmlComment;
import org.curtis.util.StringUtil;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "score", uniqueConstraints = @UniqueConstraint(columnNames = "score_name"))
public class Score extends DatabaseItem {
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "score_declaration_id")
    private ScoreDeclaration scoreDeclaration;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "score_header_id")
    private ScoreHeader scoreHeader = new ScoreHeader();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "score_id", nullable = false)
    @OrderBy("ordering")
    private List<Part> parts = new ArrayList<>();
    @Column
    private String version;
    @Column(name = "score_name")
    private String scoreName;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "score_id", nullable = false)
    private List<XmlComment> xmlComments = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    @Column(name = "score_type")
    private ScoreType scoreType;

    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    public Score() {

    }

    public ScoreDeclaration getScoreDeclaration() {
        return scoreDeclaration;
    }

    public void setScoreDeclaration(ScoreDeclaration scoreDeclaration) {
        this.scoreDeclaration = scoreDeclaration;
    }

    public ScoreHeader getScoreHeader() {
        return scoreHeader;
    }

    public void setScoreHeader(ScoreHeader scoreHeader) {
        this.scoreHeader = scoreHeader;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getScoreName() {
        return scoreName;
    }

    public void setScoreName(String scoreName) {
        this.scoreName = scoreName;
    }

    public List<XmlComment> getXmlComments() {
        return xmlComments;
    }

    public void setXmlComments(List<XmlComment> xmlComments) {
        this.xmlComments = xmlComments;
    }

    public ScoreType getScoreType() {
        return scoreType;
    }

    public void setScoreType(ScoreType scoreType) {
        this.scoreType = scoreType;
    }

    public Charset getEncoding() {
        if (scoreDeclaration == null) return DEFAULT_CHARSET;

        ScoreXmlDeclaration scoreXmlDeclaration = scoreDeclaration.getScoreXmlDeclaration();
        if (scoreXmlDeclaration == null) return DEFAULT_CHARSET;

        String encoding = scoreXmlDeclaration.getEncoding();
        if (StringUtil.isEmpty(encoding)) return DEFAULT_CHARSET;

        return Charset.forName(encoding);
    }
}
