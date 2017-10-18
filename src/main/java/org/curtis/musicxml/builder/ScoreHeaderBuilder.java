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
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\\header {\n");

        if(StringUtil.isNotEmpty(scoreHeader.getMovementTitle())) {
            stringBuilder.append("title = \"");
            stringBuilder.append(scoreHeader.getMovementTitle());
            stringBuilder.append("\"\n");
        }

        Identification identification = scoreHeader.getIdentification();
        for (TypedText typedText : identification.getCreators()) {
            if(typedText.getType().equals("composer")) {
                stringBuilder.append("composer = \"");
                stringBuilder.append(typedText.getValue());
                stringBuilder.append("\"\n");
            }
        }

        stringBuilder.append("}\n");

        return stringBuilder;
    }
}
