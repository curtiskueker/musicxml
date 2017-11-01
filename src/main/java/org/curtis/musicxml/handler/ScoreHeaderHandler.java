package org.curtis.musicxml.handler;

import org.curtis.musicxml.identity.TypedText;
import org.curtis.musicxml.identity.encoding.Encoding;
import org.curtis.musicxml.identity.encoding.Software;
import org.curtis.musicxml.score.PartList;
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

    public void handle() {
        Element element = getElement();

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

            Element encodingElement = XmlUtil.getChildElement(identification, "encoding");
            if(encodingElement != null) {
                List<Element> encodingSubelements = XmlUtil.getChildElements(encodingElement);
                List<Encoding> encodings = new ArrayList<>();
                for(Element encodingSubelement : encodingSubelements) {
                    switch (encodingSubelement.getTagName()) {
                        case "software":
                            Software software = new Software();
                            software.setSoftware(XmlUtil.getElementText(encodingSubelement));
                            encodings.add(software);
                            break;
                    }
                }
            }
        }

        // part list: required
        Element partListElement = XmlUtil.getChildElement(element, "part-list");
        PartList partList = scoreHeader.getPartList();
        PartListHandler partListHandler = new PartListHandler(partListElement, partList);
        partListHandler.handle();
    }
}
