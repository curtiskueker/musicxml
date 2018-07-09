package org.curtis.musicxml.builder;

import org.curtis.musicxml.builder.musicdata.MusicDataBuilder;
import org.curtis.musicxml.score.Part;
import org.curtis.musicxml.score.Score;

public class ScoreBuilder extends MusicDataBuilder {
    private Score score;

    public ScoreBuilder(Score score) {
        this.score = score;
    }

    public StringBuilder build() {
        buildOpenElement("score-partwise");

        append(" version=\"");
        append(score.getVersion());
        append("\"");

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
}
