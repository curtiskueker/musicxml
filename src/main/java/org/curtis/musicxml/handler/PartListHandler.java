package org.curtis.musicxml.handler;

import org.curtis.musicxml.common.MidiInstrument;
import org.curtis.musicxml.factory.AttributesFactory;
import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.factory.IdentityFactory;
import org.curtis.musicxml.factory.PlacementFactory;
import org.curtis.musicxml.factory.ScorePartFactory;
import org.curtis.musicxml.score.GroupBarline;
import org.curtis.musicxml.score.GroupBarlineType;
import org.curtis.musicxml.score.GroupSymbol;
import org.curtis.musicxml.score.PartGroup;
import org.curtis.musicxml.score.PartList;
import org.curtis.musicxml.score.PartName;
import org.curtis.musicxml.score.ScorePart;
import org.curtis.musicxml.score.instrument.Ensemble;
import org.curtis.musicxml.score.instrument.ScoreInstrument;
import org.curtis.musicxml.score.instrument.Solo;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

public class PartListHandler extends AbstractHandler {
    private PartList partList;

    public PartListHandler(PartList partList) {
        this.partList = partList;
    }

    public void handle(Element element) {
        List<Element> partListSubelements = XmlUtil.getChildElements(element);
        PartGroup currentPartGroup = null;
        for(Element partListSubelement : partListSubelements) {
            String elementName = partListSubelement.getTagName();
            switch (elementName) {
                case "part-group":
                    String type = partListSubelement.getAttribute("type");
                    if(type.equals("start")) {
                        currentPartGroup = new PartGroup();
                        currentPartGroup.setEditorial(FormattingFactory.newEditorial(partListSubelement));
                        currentPartGroup.setNumber(partListSubelement.getAttribute("number"));
                        currentPartGroup.setGroupName(ScorePartFactory.newGroupName(XmlUtil.getChildElement(partListSubelement, "group-name")));
                        currentPartGroup.setGroupNameDisplay(ScorePartFactory.newNameDisplay(XmlUtil.getChildElement(partListSubelement, "group-name-display")));
                        currentPartGroup.setGroupAbbreviation(ScorePartFactory.newGroupName(XmlUtil.getChildElement(partListSubelement, "group-abbreviation")));
                        currentPartGroup.setGroupAbbreviationDisplay(ScorePartFactory.newNameDisplay(XmlUtil.getChildElement(partListSubelement, "group-abbreviation-display")));
                        Element groupSymbolElement = XmlUtil.getChildElement(partListSubelement, "group-symbol");
                        if (groupSymbolElement != null) {
                            GroupSymbol groupSymbol = new GroupSymbol();
                            groupSymbol.setGroupSymbolType(AttributesFactory.newGroupSymbolType(groupSymbolElement));
                            groupSymbol.setPosition(PlacementFactory.newPosition(groupSymbolElement));
                            groupSymbol.setColor(groupSymbolElement.getAttribute("color"));
                            currentPartGroup.setGroupSymbol(groupSymbol);
                        }
                        Element groupBarlineElement = XmlUtil.getChildElement(partListSubelement, "group-barline");
                        if (groupBarlineElement != null) {
                            GroupBarline groupBarline = new GroupBarline();
                            String groupBarlineValue = XmlUtil.getElementText(groupBarlineElement);
                            switch (groupBarlineValue) {
                                case "yes":
                                    groupBarline.setGroupBarlineValue(GroupBarlineType.YES);
                                    break;
                                case "no":
                                    groupBarline.setGroupBarlineValue(GroupBarlineType.NO);
                                    break;
                                case "Mensurstrich":
                                    groupBarline.setGroupBarlineValue(GroupBarlineType.MENSURSTRICH);
                                    break;
                            }
                            groupBarline.setColor(groupBarlineElement.getAttribute("color"));
                        }
                        currentPartGroup.setGroupTime(XmlUtil.hasChildElement(partListSubelement, "group-time"));
                    } else if(type.equals("stop")) {
                        partList.getPartItems().add(currentPartGroup);
                        currentPartGroup = null;
                    }

                    break;
                case "score-part":
                    ScorePart scorePart = new ScorePart();
                    scorePart.setId(partListSubelement.getAttribute("id"));
                    scorePart.setIdentification(IdentityFactory.newIdentification(XmlUtil.getChildElement(partListSubelement, "identification")));
                    scorePart.setPartName(ScorePartFactory.newPartName(XmlUtil.getChildElement(partListSubelement, "part-name")));
                    scorePart.setPartNameDisplay(ScorePartFactory.newNameDisplay(XmlUtil.getChildElement(partListSubelement, "part-name-display")));
                    scorePart.setPartAbbreviation(ScorePartFactory.newPartName(XmlUtil.getChildElement(partListSubelement, "part-abbreviation")));
                    scorePart.setPartAbbreviationDisplay(ScorePartFactory.newNameDisplay(XmlUtil.getChildElement(partListSubelement, "part-abbreviation-display")));
                    List<Element> groupElements = XmlUtil.getChildElements(partListSubelement, "group");
                    for(Element groupElement : groupElements) {
                        scorePart.getGroups().add(XmlUtil.getElementText(groupElement));
                    }
                    List<Element> scoreInstrumentElements = XmlUtil.getChildElements(partListSubelement, "score-instrument");
                    for(Element scoreInstrumentElement : scoreInstrumentElements) {
                        ScoreInstrument scoreInstrument = new ScoreInstrument();
                        scoreInstrument.setId(scoreInstrumentElement.getAttribute("id"));
                        scoreInstrument.setInstrumentName(XmlUtil.getChildElementText(scoreInstrumentElement, "instrument-name"));
                        scoreInstrument.setInstrumentAbbreviation(XmlUtil.getChildElementText(scoreInstrumentElement, "instrument-abbreviation"));
                        scoreInstrument.setInstrumentSound(XmlUtil.getChildElementText(scoreInstrumentElement, "instrument-sound"));
                        if(XmlUtil.hasChildElement(scoreInstrumentElement, "solo")) {
                            scoreInstrument.setInstrumentType(new Solo());
                        } else if (XmlUtil.hasChildElement(scoreInstrumentElement, "ensemble")) {
                            Ensemble ensemble = new Ensemble();
                            ensemble.setValue(StringUtil.getInteger(XmlUtil.getChildElementText(scoreInstrumentElement, "ensemble")));
                            scoreInstrument.setInstrumentType(ensemble);
                        }
                        Element virtualInstrumentElement = XmlUtil.getChildElement(partListSubelement, "virtual-instrument");
                        scoreInstrument.setVirtualLibrary(XmlUtil.getChildElementText(virtualInstrumentElement, "virtual-library"));
                        scoreInstrument.setVirtualName(XmlUtil.getChildElementText(virtualInstrumentElement, "virtual-name"));
                        scorePart.getScoreInstruments().add(scoreInstrument);
                    }
                    List<Element> midiInstrumentElements = XmlUtil.getChildElements(partListSubelement, "midi-instrument");
                    List<MidiInstrument> midiInstruments = scorePart.getMidiInstruments();
                    for(Element midiInstrumentElement : midiInstrumentElements) {
                        MidiInstrument midiInstrument = new MidiInstrument();
                        midiInstrument.setId(midiInstrumentElement.getAttribute("id"));
                        midiInstrument.setMidiChannel(StringUtil.getInteger(XmlUtil.getChildElementText(midiInstrumentElement, "midi-channel")));
                        midiInstrument.setMidiProgram(StringUtil.getInteger(XmlUtil.getChildElementText(midiInstrumentElement, "midi-program")));
                        midiInstrument.setVolume(MathUtil.newBigDecimal(XmlUtil.getChildElementText(midiInstrumentElement, "volume")));
                        midiInstrument.setPan(MathUtil.newBigDecimal(XmlUtil.getChildElementText(midiInstrumentElement, "pan")));
                        midiInstruments.add(midiInstrument);
                    }

                    if(currentPartGroup == null) {
                        partList.getPartItems().add(scorePart);
                    } else {
                        currentPartGroup.getScoreParts().add(scorePart);
                    }

                    break;
            }
        }
    }
}
