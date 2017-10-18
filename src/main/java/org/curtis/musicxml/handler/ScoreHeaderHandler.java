package org.curtis.musicxml.handler;

import org.curtis.musicxml.builder.ScoreHeaderBuilder;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

public class ScoreHeaderHandler extends AbstractHandler {
    public ScoreHeaderHandler(Element element) {
        super(element);
    }

    public StringBuilder handle() {
        StringBuilder stringBuilder = new StringBuilder();

        Element element = getElement();

        ScoreHeaderBuilder scoreHeaderBuilder = new ScoreHeaderBuilder();

        scoreHeaderBuilder.setMovementTitle(XmlUtil.getChildElementText(element, "movement-title"));
        Element identification = XmlUtil.getChildElement(element, "identification");
        if (identification != null) {
            Element creator = XmlUtil.getChildElement(identification, "creator");
            if(creator != null) {
                String creatorType = creator.getAttribute("type");
                String creatorValue = XmlUtil.getElementText(creator);
                switch (creatorType) {
                    case "composer":
                        scoreHeaderBuilder.setComposer(creatorValue);
                        break;
                }
            }
        }

        stringBuilder.append(scoreHeaderBuilder.build().toString());

        return stringBuilder;
    }
}
