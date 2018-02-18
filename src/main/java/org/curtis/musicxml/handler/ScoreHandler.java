package org.curtis.musicxml.handler;

import org.curtis.musicxml.score.PartItem;
import org.curtis.musicxml.score.Score;
import org.curtis.musicxml.score.ScorePart;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class ScoreHandler extends AbstractHandler {
    private Score score;
    public static Boolean DEBUG;

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
        List<Element> partElements = XmlUtil.getChildElements(element, "part");
        for(PartItem partItem : score.getScoreHeader().getPartList().getPartItems()) {
            if(partItem instanceof ScorePart) {
                ScorePart scorePart = (ScorePart) partItem;
                String partId = scorePart.getId();
                for (Element partElement : partElements) {
                    if (partId.equals(partElement.getAttribute("id"))) {
                        PartHandler partHandler = new PartHandler(score.getParts());
                        partHandler.handle(partElement);
                    }
                }
            }
        }

        String scoreVersion = element.getAttribute("version");
        if (StringUtil.isNotEmpty(scoreVersion)) score.setVersion(scoreVersion);
    }

    public static void displayException(Exception e) {
        if (StringUtil.isEmpty(e.getMessage())) {
            System.err.println("Exception: no message");
        } else {
            System.err.println("Exception: " + e.getMessage());
        }
        if (!DEBUG) return;

        e.printStackTrace();
        System.err.println("");
    }
}
