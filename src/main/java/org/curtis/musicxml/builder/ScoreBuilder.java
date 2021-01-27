package org.curtis.musicxml.builder;

import org.curtis.musicxml.builder.musicdata.MusicDataBuilder;
import org.curtis.musicxml.score.Part;
import org.curtis.musicxml.score.Score;
import org.curtis.musicxml.score.ScoreDeclaration;
import org.curtis.musicxml.score.ScoreDoctype;
import org.curtis.musicxml.score.ScoreXmlDeclaration;
import org.curtis.util.StringUtil;

public class ScoreBuilder extends MusicDataBuilder {
    private Score score;

    public ScoreBuilder(Score score) {
        this.score = score;
    }

    public StringBuilder build() {
        buildDocumentDeclaration();

        buildOpenElement("score-partwise");
        buildAttribute("version", score.getVersion());
        buildCloseElement();

        ScoreHeaderBuilder scoreHeaderBuilder = new ScoreHeaderBuilder(score.getScoreHeader());
        append(scoreHeaderBuilder.build().toString());

        for (Part part : score.getParts()) {
            PartBuilder partBuilder = new PartBuilder(part);
            append(partBuilder.build().toString());
        }

        buildEndElement("score-partwise");

        return stringBuilder;
    }

    private void buildDocumentDeclaration() {
        ScoreDeclaration scoreDeclaration = score.getScoreDeclaration();
        if (scoreDeclaration == null) return;

        ScoreXmlDeclaration scoreXmlDeclaration = scoreDeclaration.getScoreXmlDeclaration();
        if (scoreXmlDeclaration != null) {
            append("<?xml ");

            String version = scoreXmlDeclaration.getVersion();
            if (StringUtil.isNotEmpty(version)) {
                append("version=\"");
                append(version);
                append("\" ");
            }

            String encoding = scoreXmlDeclaration.getEncoding();
            if (StringUtil.isNotEmpty(encoding)) {
                append("encoding=\"");
                append(encoding);
                append("\" ");
            }

            append("standalone=\"");
            append(BuilderUtil.yesOrNo(scoreXmlDeclaration.getStandalone()));
            append("\"?>");
            append("\n");
        }

        ScoreDoctype scoreDoctype = scoreDeclaration.getScoreDoctype();
        if (scoreDoctype != null) {
            append("<!DOCTYPE score-partwise PUBLIC \"");
            append(scoreDoctype.getPublicId());
            append("\" \"");
            append(scoreDoctype.getSystemId());
            append("\">");
            append("\n");
        }
    }
}
