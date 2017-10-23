package org.curtis.musicxml.builder;

import org.curtis.musicxml.score.PartGroup;
import org.curtis.musicxml.score.PartList;
import org.curtis.musicxml.score.Score;
import org.curtis.musicxml.score.ScorePart;

import java.util.List;

public class ScoreBuilder extends AbstractBuilder {
    private Score score;

    public ScoreBuilder(Score score) {
        this.score = score;
    }

    public Score getScore() {
        return score;
    }

    public StringBuilder build() {
        // begin score
        appendLine("\\score {");

        List<PartGroup> partGroups = score.getScoreHeader().getPartList().getPartGroups();
        for (PartGroup partGroup : partGroups) {
            // begin staff group
            appendLine("\\new StaffGroup <<");

            // staff
            // TODO: score parts from the part groups list for now
            for(ScorePart scorePart : partGroup.getScoreParts()) {
                appendLine("\\new Staff");

                // staff identifiers
                appendLine("\\with {");

                append("instrumentName = #\"");
                append(scorePart.getPartName().getPartName());
                appendLine("\"");

                append("shortInstrumentName = #\"");
                append(scorePart.getPartAbbreviation().getPartName());
                appendLine("\"");

                appendLine("}");

                // TODO; temporary output
                appendLine("{c' e' g' e'}");
            }

            // end staff group
            appendLine(">>");
        }

        // end score
        appendLine("}");

        return stringBuilder;
    }
}
