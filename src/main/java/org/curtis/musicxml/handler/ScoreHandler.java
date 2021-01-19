package org.curtis.musicxml.handler;

import org.curtis.musicxml.score.PartListItem;
import org.curtis.musicxml.score.Score;
import org.curtis.musicxml.score.ScoreDeclaration;
import org.curtis.musicxml.score.ScoreDoctype;
import org.curtis.musicxml.score.ScorePart;
import org.curtis.musicxml.score.ScoreXmlDeclaration;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;

import java.util.List;

public class ScoreHandler {
    private Score score = new Score();

    public ScoreHandler() {
        
    }

    public Score getScore() {
        return score;
    }

    public void handleDeclaration(Document document) {
        ScoreXmlDeclaration scoreXmlDeclaration = null;
        String version = document.getXmlVersion();
        String encoding = document.getXmlEncoding();
        Boolean standalone = document.getXmlStandalone();
        if (StringUtil.isNotEmpty(version) || StringUtil.isNotEmpty(encoding)) {
            scoreXmlDeclaration = new ScoreXmlDeclaration();
            scoreXmlDeclaration.setVersion(version);
            scoreXmlDeclaration.setEncoding(encoding);
            scoreXmlDeclaration.setStandalone(standalone);
        }

        ScoreDoctype scoreDoctype = null;
        DocumentType documentType = document.getDoctype();
        if (documentType != null) {
            String name = documentType.getName();
            String publicId = documentType.getPublicId();
            String systemId = documentType.getSystemId();
            if (StringUtil.isNotEmpty(name) || StringUtil.isNotEmpty(publicId) || StringUtil.isNotEmpty(systemId)) {
                scoreDoctype = new ScoreDoctype();
                scoreDoctype.setName(name);
                scoreDoctype.setPublicId(publicId);
                scoreDoctype.setSystemId(systemId);
            }
        }

        if (scoreXmlDeclaration == null && scoreDoctype == null) return;

        ScoreDeclaration scoreDeclaration = new ScoreDeclaration();
        scoreDeclaration.setScoreXmlDeclaration(scoreXmlDeclaration);
        scoreDeclaration.setScoreDoctype(scoreDoctype);

        score.setScoreDeclaration(scoreDeclaration);
    }

    public void handle(Element element) {
        // handle the score header
        ScoreHeaderHandler scoreHeaderHandler = new ScoreHeaderHandler(score.getScoreHeader());
        scoreHeaderHandler.handle(element);

        // handle the parts
        List<Element> partElements = XmlUtil.getChildElements(element, "part");
        for(PartListItem partListItem : score.getScoreHeader().getPartListItems()) {
            if(partListItem instanceof ScorePart) {
                ScorePart scorePart = (ScorePart) partListItem;
                String partId = scorePart.getScorePartId();
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
}
