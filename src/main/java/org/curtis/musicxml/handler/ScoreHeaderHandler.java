package org.curtis.musicxml.handler;

import org.curtis.musicxml.builder.ScoreHeaderBuilder;
import org.curtis.musicxml.identity.TypedText;
import org.curtis.musicxml.score.ScoreHeader;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

public class ScoreHeaderHandler extends AbstractHandler {
    private ScoreHeader scoreHeader;

    public ScoreHeaderHandler(Element element, ScoreHeader scoreHeader) {
        super(element);
        this.scoreHeader = scoreHeader;
    }

    public StringBuilder handle() {
        StringBuilder stringBuilder = new StringBuilder();

        Element element = getElement();

        ScoreHeaderBuilder scoreHeaderBuilder = new ScoreHeaderBuilder(scoreHeader);

        scoreHeader.setMovementTitle(XmlUtil.getChildElementText(element, "movement-title"));

        Element identification = XmlUtil.getChildElement(element, "identification");
        if (identification != null) {
            List<Element> creators = XmlUtil.getChildElements(identification, "creator");
            List<TypedText> typedTextList = new ArrayList<>();
            for(Element creator : creators) {
                String creatorType = creator.getAttribute("type");
                String creatorValue = XmlUtil.getElementText(creator);

                TypedText typedText = new TypedText();
                typedText.setType(creatorType);
                typedText.setValue(creatorValue);

                typedTextList.add(typedText);
            }
            scoreHeader.getIdentification().setCreators(typedTextList);
        }

        stringBuilder.append(scoreHeaderBuilder.build().toString());

        return stringBuilder;
    }
}
