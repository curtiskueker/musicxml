package org.curtis.musicxml.handler;

import org.curtis.musicxml.score.PartGroup;
import org.curtis.musicxml.score.PartItem;
import org.curtis.musicxml.score.Score;
import org.curtis.musicxml.score.ScorePart;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class ScoreHandler extends AbstractHandler {
    private Score score;
    private List<Element> partElements;

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
        partElements = XmlUtil.getChildElements(element, "part");
        for(PartItem partItem : score.getScoreHeader().getPartList().getPartItems()) {
            if(partItem instanceof PartGroup) {
                PartGroup partGroup = (PartGroup)partItem;
                for(ScorePart scorePart : partGroup.getScoreParts()) {
                    handleScorePart(scorePart);
                }
            } else if(partItem instanceof ScorePart) {
                ScorePart scorePart = (ScorePart)partItem;
                handleScorePart(scorePart);
            }
        }
    }

    private void handleScorePart(ScorePart scorePart) {
        String partId = scorePart.getId();
        for(Element partElement : partElements) {
            if(partId.equals(partElement.getAttribute("id"))) {
                PartHandler partHandler = new PartHandler(score.getParts());
                partHandler.handle(partElement);
                return;
            }
        }
    }
}
