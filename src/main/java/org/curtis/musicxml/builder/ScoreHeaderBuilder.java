package org.curtis.musicxml.builder;

import org.curtis.musicxml.identity.Identification;
import org.curtis.musicxml.identity.TypedText;
import org.curtis.musicxml.score.ScoreHeader;
import org.curtis.util.StringUtil;

public class ScoreHeaderBuilder extends AbstractBuilder {
    private ScoreHeader scoreHeader;

    public ScoreHeaderBuilder(ScoreHeader scoreHeader) {
        this.scoreHeader = scoreHeader;
    }

    public ScoreHeader getScoreHeader() {
        return scoreHeader;
    }

    public void setScoreHeader(ScoreHeader scoreHeader) {
        this.scoreHeader = scoreHeader;
    }

    public StringBuilder build() {
        appendLine("\\header {");

        if(StringUtil.isNotEmpty(scoreHeader.getMovementTitle())) {
            append("title = \"");
            append(scoreHeader.getMovementTitle());
            appendLine("\"");
        }

        Identification identification = scoreHeader.getIdentification();
        for (TypedText typedText : identification.getCreators()) {
            if(typedText.getType().equals("composer")) {
                append("composer = \"");
                append(typedText.getValue());
                append("\"");
            }
        }

        appendLine("}");

        return stringBuilder;
    }
}
