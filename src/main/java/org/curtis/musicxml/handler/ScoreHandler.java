package org.curtis.musicxml.handler;

import org.curtis.musicxml.builder.ScoreBuilder;
import org.curtis.musicxml.builder.ScoreHeaderBuilder;
import org.curtis.musicxml.score.PartGroup;
import org.curtis.musicxml.score.Score;
import org.curtis.musicxml.score.ScorePart;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

public class ScoreHandler extends AbstractHandler {
    public ScoreHandler(Element element) {
        super(element);
    }
    private StringBuilder results = new StringBuilder();

    public void handle() {
        Score score = new Score();


        // score header
        ScoreHeaderHandler scoreHeaderHandler = new ScoreHeaderHandler(getElement(), score.getScoreHeader());
        scoreHeaderHandler.handle();

        ScoreHeaderBuilder scoreHeaderBuilder = new ScoreHeaderBuilder(score.getScoreHeader());
        results.append(scoreHeaderBuilder.build().toString());

        // TODO: uses part list from part group for now
        for(PartGroup partGroup : score.getScoreHeader().getPartList().getPartGroups()) {
            for(ScorePart scorePart : partGroup.getScoreParts()) {
                String partId = scorePart.getId();
                for(Element partElement : XmlUtil.getChildElements(getElement(), "part")) {
                    if(partId.equals(partElement.getAttribute("id"))) {
                        PartHandler partHandler = new PartHandler(partElement, score.getParts());
                        partHandler.handle();

                        break;
                    }
                }
            }
        }

        // score
        ScoreBuilder scoreBuilder = new ScoreBuilder(score);
        results.append(scoreBuilder.build().toString());
    }

    public StringBuilder getResults() {
        return results;
    }
}
