package org.curtis.musicxml.handler;

import org.curtis.musicxml.builder.ScoreBuilder;
import org.curtis.musicxml.score.Score;
import org.w3c.dom.Element;

public class ScoreHandler extends AbstractHandler {
    public ScoreHandler(Element element) {
        super(element);
    }

    public StringBuilder handle() {
        Score score = new Score();
        StringBuilder scoreStringBuilder = getStringBuilder();

        // score header
        ScoreHeaderHandler scoreHeaderHandler = new ScoreHeaderHandler(getElement(), score.getScoreHeader());
        scoreStringBuilder.append(scoreHeaderHandler.handle());

        // score
        ScoreBuilder scoreBuilder = new ScoreBuilder(score);
        scoreStringBuilder.append(scoreBuilder.build().toString());

        return scoreStringBuilder;
    }
}
