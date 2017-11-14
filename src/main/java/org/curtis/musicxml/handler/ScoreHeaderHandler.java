package org.curtis.musicxml.handler;

import org.curtis.musicxml.common.FormattedText;
import org.curtis.musicxml.factory.FormatFactory;
import org.curtis.musicxml.identity.Identification;
import org.curtis.musicxml.identity.TypedText;
import org.curtis.musicxml.identity.encoding.Encoding;
import org.curtis.musicxml.identity.encoding.EncodingDate;
import org.curtis.musicxml.identity.encoding.Software;
import org.curtis.musicxml.layout.Layout;
import org.curtis.musicxml.layout.PageLayout;
import org.curtis.musicxml.layout.Scaling;
import org.curtis.musicxml.score.Credit;
import org.curtis.musicxml.score.Defaults;
import org.curtis.musicxml.score.PartList;
import org.curtis.musicxml.score.ScoreHeader;
import org.curtis.util.DateUtil;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

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

        List<Element> subelements = XmlUtil.getChildElements(element);
        for (Element subelement : subelements) {
            String subelementName = subelement.getTagName();
            switch (subelementName) {
                case "identification":
                    Identification identification = scoreHeader.getIdentification();

                    List<Element> identificationSubelements = XmlUtil.getChildElements(subelement);
                    for(Element identificationSubelement : identificationSubelements) {
                        String identificationSubelementName = identificationSubelement.getTagName();
                        switch (identificationSubelementName) {
                            case "creator":
                                List<TypedText> creators = identification.getCreators();
                                String creatorType = identificationSubelement.getAttribute("type");
                                String creatorValue = XmlUtil.getElementText(identificationSubelement);

                                TypedText typedText = new TypedText();
                                typedText.setType(creatorType);
                                typedText.setValue(creatorValue);

                                creators.add(typedText);
                                break;
                            case "encoding":
                                List<Element> encodingSubelements = XmlUtil.getChildElements(identificationSubelement);
                                List<Encoding> encodings = identification.getEncodings();
                                for(Element encodingSubelement : encodingSubelements) {
                                    switch (encodingSubelement.getTagName()) {
                                        case "encoding-date":
                                            EncodingDate encodingDate = new EncodingDate();
                                            encodingDate.setEncodingDate(DateUtil.parseDate(XmlUtil.getElementText(encodingSubelement)));
                                            encodings.add(encodingDate);
                                            break;
                                        case "software":
                                            Software software = new Software();
                                            software.setSoftware(XmlUtil.getElementText(encodingSubelement));
                                            encodings.add(software);
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case "defaults":
                    Defaults defaults = new Defaults();
                    List<Element> defaultsSubelements = XmlUtil.getChildElements(subelement);
                    for(Element defaultsSubelement : defaultsSubelements) {
                        String defaultsSubelementName = defaultsSubelement.getTagName();
                        switch (defaultsSubelementName) {
                            case "scaling":
                                Scaling scaling = new Scaling();
                                scaling.setMillimeters(MathUtil.newBigDecimal(XmlUtil.getChildElementText(defaultsSubelement, "millimeters")));
                                scaling.setTenths(MathUtil.newBigDecimal(XmlUtil.getChildElementText(defaultsSubelement, "tenths")));
                                defaults.setScaling(scaling);
                                break;
                            case "page-layout":
                                Layout layout = new Layout();
                                PageLayout pageLayout = new PageLayout();
                                pageLayout.setPageHeight(MathUtil.newBigDecimal(XmlUtil.getChildElementText(defaultsSubelement, "page-height")));
                                pageLayout.setPageWidth(MathUtil.newBigDecimal(XmlUtil.getChildElementText(defaultsSubelement, "page-width")));
                                layout.setPageLayout(pageLayout);
                                break;
                        }
                    }
                    scoreHeader.setDefaults(defaults);
                    break;
                case "credit":
                    List<Credit> credits = scoreHeader.getCredits();
                    Credit credit = new Credit();
                    String page = subelement.getAttribute("page");
                    if(StringUtil.isNotEmpty(page)) {
                        credit.setPage(Integer.parseInt(page));
                    }

                    List<FormattedText> creditWordsList = credit.getCreditWordsList();
                    List<Element> creditWordsElements = XmlUtil.getChildElements(subelement, "credit-words");
                    for(Element creditWordsElement : creditWordsElements) {
                        FormattedText creditWords = FormatFactory.newFormattedText(creditWordsElement);
                        creditWordsList.add(creditWords);
                    }
                    credits.add(credit);
                    break;
            }
        }

        // part list: required
        // processed last
        Element partListElement = XmlUtil.getChildElement(element, "part-list");
        PartList partList = scoreHeader.getPartList();
        PartListHandler partListHandler = new PartListHandler(partListElement, partList);
        partListHandler.handle();
    }
}
