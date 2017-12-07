package org.curtis.musicxml.handler;

import org.curtis.musicxml.score.PartGroup;
import org.curtis.musicxml.score.Score;
import org.curtis.musicxml.score.ScorePart;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

public class ScoreHandler extends AbstractHandler {
    private Score score;

    public ScoreHandler() {
        
    }

    public Score getScore() {
        return score;
    }

    public void handle(Element element) {
        score = new Score();

        // handle the score header
        ScoreHeaderHandler scoreHeaderHandler = new ScoreHeaderHandler(score.getScoreHeader());
        scoreHeaderHandler.handle(element);

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

    }
}
