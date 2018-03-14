package org.curtis.musicxml.builder;

import org.curtis.musicxml.score.Part;
import org.curtis.musicxml.score.Score;

public class ScoreBuilder extends BaseBuilder {
    private Score score;

    public ScoreBuilder(Score score) {
        this.score = score;
    }

    public StringBuilder build() {
        append("<score-partwise");

        append(" version=\"");
        append(score.getVersion());
        append("\"");

        appendLine(">");

        ScoreHeaderBuilder scoreHeaderBuilder = new ScoreHeaderBuilder(score.getScoreHeader());
        append(scoreHeaderBuilder.build().toString());

        for (Part part : score.getParts()) {
            PartBuilder partBuilder = new PartBuilder(part);
            append(partBuilder.build().toString());
        }

        appendLine("</score-partwise>");

        return stringBuilder;
    }
}