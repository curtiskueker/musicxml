package org.curtis.musicxml.factory;

import org.curtis.musicxml.identity.IdentificationType;
import org.curtis.musicxml.util.TypeUtil;
import org.curtis.musicxml.identity.Identification;
import org.curtis.musicxml.identity.Miscellaneous;
import org.curtis.musicxml.identity.encoding.Encoder;
import org.curtis.musicxml.identity.encoding.Encoding;
import org.curtis.musicxml.identity.encoding.EncodingDate;
import org.curtis.musicxml.identity.encoding.EncodingDescription;
import org.curtis.musicxml.identity.encoding.Software;
import org.curtis.musicxml.identity.encoding.Supports;
import org.curtis.util.DateUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class IdentityFactory {
    private IdentityFactory() {

    }

    public static Identification newIdentification(Element element) {
        if (element == null) return null;

        Identification identification = new Identification();
        List<Element> identificationSubelements = XmlUtil.getChildElements(element);
        for(Element identificationSubelement : identificationSubelements) {
            String identificationSubelementName = identificationSubelement.getTagName();
            switch (identificationSubelementName) {
                case "creator":
                case "rights":
                case "relation":
                    identification.getIdentificationTypes().add(newIdentificationType(identificationSubelementName, identificationSubelement));
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
                            case "encoder":
                                Encoder encoder = new Encoder();
                                encoder.setIdentificationType(newIdentificationType(encodingSubelement.getTagName(), encodingSubelement));
                                break;
                            case "software":
                                Software software = new Software();
                                software.setSoftware(XmlUtil.getElementText(encodingSubelement));
                                encodings.add(software);
                                break;
                            case "encoding-description":
                                EncodingDescription encodingDescription = new EncodingDescription();
                                encodingDescription.setEncodingDescription(XmlUtil.getElementText(encodingSubelement));
                                encodings.add(encodingDescription);
                                break;
                            case "supports":
                                Supports supports = new Supports();
                                supports.setType(TypeUtil.getYesNo(encodingSubelement.getAttribute("type")));
                                supports.setElement(encodingSubelement.getAttribute("element"));
                                supports.setAttribute(encodingSubelement.getAttribute("attribute"));
                                supports.setValue(encodingSubelement.getAttribute("value"));
                                encodings.add(supports);
                                break;
                        }
                    }
                    break;
                case "source":
                    identification.setSource(XmlUtil.getElementText(identificationSubelement));
                    break;
                case "miscellaneous":
                    List<Element> miscellaneousFieldElements = XmlUtil.getChildElements(identificationSubelement, "miscellaneous-field");
                    for(Element miscellaneousFieldElement: miscellaneousFieldElements) {
                        Miscellaneous miscellaneous = new Miscellaneous();
                        miscellaneous.setValue(XmlUtil.getElementText(miscellaneousFieldElement));
                        miscellaneous.setName(miscellaneousFieldElement.getAttribute("name"));
                        identification.getMiscellaneousList().add(miscellaneous);
                    }
                    break;
            }
        }

        return identification;
    }

    private static IdentificationType newIdentificationType(String identificationName, Element element) {
        IdentificationType identificationType = new IdentificationType();
        identificationType.setIdName(identificationName);
        identificationType.setIdType(element.getAttribute("type"));
        identificationType.setIdValue(XmlUtil.getElementText(element));

        return identificationType;
    }
}
