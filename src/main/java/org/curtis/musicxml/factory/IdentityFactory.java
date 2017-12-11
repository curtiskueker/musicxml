package org.curtis.musicxml.factory;

import org.curtis.musicxml.handler.util.TypeUtil;
import org.curtis.musicxml.identity.Identification;
import org.curtis.musicxml.identity.Miscellaneous;
import org.curtis.musicxml.identity.MiscellaneousField;
import org.curtis.musicxml.identity.TypedText;
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
        Identification identification = new Identification();

        List<Element> identificationSubelements = XmlUtil.getChildElements(element);
        for(Element identificationSubelement : identificationSubelements) {
            String identificationSubelementName = identificationSubelement.getTagName();
            switch (identificationSubelementName) {
                case "creator":
                    List<TypedText> creators = identification.getCreators();
                    creators.add(newTypedText(identificationSubelement));
                    break;
                case "rights":
                    List<TypedText> rightsList = identification.getRightsList();
                    rightsList.add(newTypedText(identificationSubelement));
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
                                encoder.setEncoder(newTypedText(encodingSubelement));
                                encodings.add(encoder);
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
                case "relation":
                    List<TypedText> relations = identification.getRelations();
                    relations.add(newTypedText(identificationSubelement));
                    break;
                case "miscellaneous":
                    Miscellaneous miscellaneous = new Miscellaneous();
                    List<MiscellaneousField> miscellaneousFields = miscellaneous.getMiscellaneousFields();
                    List<Element> miscellaneousFieldElements = XmlUtil.getChildElements(identificationSubelement, "miscellaneous-field");
                    for(Element miscellaneousFieldElement: miscellaneousFieldElements) {
                        MiscellaneousField miscellaneousField = new MiscellaneousField();
                        miscellaneousField.setValue(XmlUtil.getElementText(miscellaneousFieldElement));
                        miscellaneousField.setName(miscellaneousFieldElement.getAttribute("name"));
                        miscellaneousFields.add(miscellaneousField);
                    }
                    identification.setMiscellaneous(miscellaneous);
                    break;
            }
        }

        return identification;
    }

    public static TypedText newTypedText(Element element) {
        TypedText typedText = new TypedText();
        typedText.setType(element.getAttribute("type"));
        typedText.setValue(XmlUtil.getElementText(element));

        return typedText;
    }
}
