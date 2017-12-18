package org.curtis.lilypond;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.MusicData;
import org.curtis.musicxml.score.Part;
import org.curtis.musicxml.score.PartGroup;
import org.curtis.musicxml.score.PartItem;
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

    public StringBuilder build() throws BuildException {
        // score header
        ScoreHeaderBuilder scoreHeaderBuilder = new ScoreHeaderBuilder(score.getScoreHeader());
        append(scoreHeaderBuilder.build().toString());

        // begin score
        appendLine("\\score {");

        List<PartItem> partItems = score.getScoreHeader().getPartList().getPartItems();
        if(partItems.isEmpty()) {
            throw new BuildException("Score header part list not found");
        }

        boolean scorePartFirst = partItems.get(0) instanceof ScorePart;

        if(scorePartFirst) {
            appendLine("<<");
        }

        for (PartItem partItem : partItems) {
            if(partItem instanceof PartGroup) {
                PartGroup partGroup = (PartGroup)partItem;

                appendLine("\\new StaffGroup <<");

                for(ScorePart scorePart : partGroup.getScoreParts()) {
                    buildPart(scorePart);
                }

                appendLine(">>");
            } else if(partItem instanceof ScorePart) {
                ScorePart scorePart = (ScorePart)partItem;
                appendLine("<<");
                buildPart(scorePart);
                appendLine(">>");
            }
        }

        if(scorePartFirst) {
            appendLine(">>");
        }

        // end score
        appendLine("}");

        return stringBuilder;
    }

    private void buildPart(ScorePart scorePart) throws BuildException {
        String partId = scorePart.getId();

        for(Part part : score.getParts()) {
            if(partId.equals(part.getId())) {
                // test for multi-staff part
                List<Measure> measures = part.getMeasures();
                if(measures.isEmpty()) {
                    throw new BuildException("Part " + part.getId() + " has no measures");
                }

                Measure measure = measures.get(0);
                for(MusicData musicData : measure.getMusicDataList()) {
                    if(musicData instanceof Attributes) {
                        Attributes attributes = (Attributes)musicData;
                        Integer staves = attributes.getStaves();
                        if(staves > 1) {
                            buildGrandStaffPart(scorePart, part);
                            return;
                        }
                    }
                }

                buildSingleStaffPart(scorePart, part);
                return;
            }
        }
    }

    private void buildSingleStaffPart(ScorePart scorePart, Part part) throws BuildException {
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

        PartBuilder partBuilder = new PartBuilder(part);
        append(partBuilder.build().toString());
    }

    private void buildGrandStaffPart(ScorePart scorePart, Part part) throws BuildException {
        appendLine("\\new GrandStaff <<");

        append("\\set GrandStaff.instrumentName = #\"");
        append(scorePart.getPartName().getPartName());
        appendLine("\"");

        append("\\set GrandStaff.shortInstrumentName = #\"");
        append(scorePart.getPartAbbreviation().getPartName());
        appendLine("\"");

        PartBuilder partBuilder = new PartBuilder(part);
        append(partBuilder.build().toString());

        appendLine(">>");
    }
}
