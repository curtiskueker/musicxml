package org.curtis.musicxml.builder;

import org.curtis.musicxml.score.Score;

public class ScoreBuilder extends AbstractBuilder {
    private Score score;

    public ScoreBuilder(Score score) {
        this.score = score;
    }

    public Score getScore() {
        return score;
    }

    public StringBuilder build() {
        StringBuilder stringBuilder = new StringBuilder();

        // begin score
        stringBuilder.append("\\score {\n");

        // temporary output
        stringBuilder.append("{c' e' g' e'}\n");

        // end score
        stringBuilder.append("}\n");

        return stringBuilder;
    }
}
