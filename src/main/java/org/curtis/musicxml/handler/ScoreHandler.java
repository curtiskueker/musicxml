package org.curtis.musicxml.handler;

import org.curtis.lilypond.builder.ScoreBuilder;
import org.curtis.lilypond.builder.ScoreHeaderBuilder;
import org.curtis.musicxml.score.PartGroup;
import org.curtis.musicxml.score.Score;
import org.curtis.musicxml.score.ScorePart;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

public class ScoreHandler extends AbstractHandler {
    public ScoreHandler() {
        
    }
    
    private StringBuilder results = new StringBuilder();

    public void handle(Element element) {
        Score score = new Score();

        // handle and build score header
        ScoreHeaderHandler scoreHeaderHandler = new ScoreHeaderHandler(score.getScoreHeader());
        scoreHeaderHandler.handle(element);

        ScoreHeaderBuilder scoreHeaderBuilder = new ScoreHeaderBuilder(score.getScoreHeader());
        results.append(scoreHeaderBuilder.build().toString());

        // handle the parts
        for(PartGroup partGroup : score.getScoreHeader().getPartList().getPartGroups()) {
            for(ScorePart scorePart : partGroup.getScoreParts()) {
                String partId = scorePart.getId();
                for(Element partElement : XmlUtil.getChildElements(element, "part")) {
                    if(partId.equals(partElement.getAttribute("id"))) {
                        PartHandler partHandler = new PartHandler(score.getParts());
                        partHandler.handle(partElement);

                        break;
                    }
                }
            }
        }

        // build the score
        ScoreBuilder scoreBuilder = new ScoreBuilder(score);
        results.append(scoreBuilder.build().toString());
    }

    public StringBuilder getResults() {
        return results;
    }
}
