package org.curtis.musicxml.handler;

import org.curtis.musicxml.factory.AttributesFactory;
import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.factory.IdentityFactory;
import org.curtis.musicxml.factory.PlacementFactory;
import org.curtis.musicxml.factory.ScorePartFactory;
import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.musicxml.score.GroupBarline;
import org.curtis.musicxml.score.GroupBarlineType;
import org.curtis.musicxml.score.GroupSymbol;
import org.curtis.musicxml.score.PartGroup;
import org.curtis.musicxml.score.PartList;
import org.curtis.musicxml.score.ScorePartMidi;
import org.curtis.musicxml.score.ScorePart;
import org.curtis.musicxml.score.ScorePartGroup;
import org.curtis.musicxml.score.instrument.Ensemble;
import org.curtis.musicxml.score.instrument.ScoreInstrument;
import org.curtis.musicxml.score.instrument.Solo;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class PartListHandler extends AbstractHandler {
    private PartList partList;

    public PartListHandler(PartList partList) {
        this.partList = partList;
    }

    public void handle(Element element) {
        List<Element> partListSubelements = XmlUtil.getChildElements(element);
        for(Element partListSubelement : partListSubelements) {
            String elementName = partListSubelement.getTagName();
            switch (elementName) {
                case "part-group":
                    PartGroup partGroup = new PartGroup();
                    partGroup.setType(PlacementUtil.getConnection(partListSubelement.getAttribute("type")));
                    partGroup.setEditorial(FormattingFactory.newEditorial(partListSubelement));
                    partGroup.setNumber(partListSubelement.getAttribute("number"));
                    partGroup.setGroupName(ScorePartFactory.newGroupName(XmlUtil.getChildElement(partListSubelement, "group-name")));
                    partGroup.setGroupNameDisplay(ScorePartFactory.newNameDisplay(XmlUtil.getChildElement(partListSubelement, "group-name-display")));
                    partGroup.setGroupAbbreviation(ScorePartFactory.newGroupName(XmlUtil.getChildElement(partListSubelement, "group-abbreviation")));
                    partGroup.setGroupAbbreviationDisplay(ScorePartFactory.newNameDisplay(XmlUtil.getChildElement(partListSubelement, "group-abbreviation-display")));
                    Element groupSymbolElement = XmlUtil.getChildElement(partListSubelement, "group-symbol");
                    if (groupSymbolElement != null) {
                        GroupSymbol groupSymbol = new GroupSymbol();
                        groupSymbol.setGroupSymbolType(AttributesFactory.newGroupSymbolType(groupSymbolElement));
                        groupSymbol.setPosition(PlacementFactory.newPosition(groupSymbolElement));
                        groupSymbol.setColor(groupSymbolElement.getAttribute("color"));
                        partGroup.setGroupSymbol(groupSymbol);
                    }
                    Element groupBarlineElement = XmlUtil.getChildElement(partListSubelement, "group-barline");
                    if (groupBarlineElement != null) {
                        GroupBarline groupBarline = new GroupBarline();
                        String groupBarlineValue = XmlUtil.getElementText(groupBarlineElement);
                        switch (groupBarlineValue) {
                            case "yes":
                                groupBarline.setGroupBarlineType(GroupBarlineType.YES);
                                break;
                            case "no":
                                groupBarline.setGroupBarlineType(GroupBarlineType.NO);
                                break;
                            case "Mensurstrich":
                                groupBarline.setGroupBarlineType(GroupBarlineType.MENSURSTRICH);
                                break;
                        }
                        groupBarline.setColor(groupBarlineElement.getAttribute("color"));
                        partGroup.setGroupBarline(groupBarline);
                    }
                    partGroup.setGroupTime(XmlUtil.hasChildElement(partListSubelement, "group-time"));
                    partList.getPartItems().add(partGroup);
                    break;
                case "score-part":
                    ScorePart scorePart = new ScorePart();
                    scorePart.setScorePartId(partListSubelement.getAttribute("id"));
                    scorePart.setIdentification(IdentityFactory.newIdentification(XmlUtil.getChildElement(partListSubelement, "identification")));
                    scorePart.setPartName(ScorePartFactory.newPartName(XmlUtil.getChildElement(partListSubelement, "part-name")));
                    scorePart.setPartNameDisplay(ScorePartFactory.newNameDisplay(XmlUtil.getChildElement(partListSubelement, "part-name-display")));
                    scorePart.setPartAbbreviation(ScorePartFactory.newPartName(XmlUtil.getChildElement(partListSubelement, "part-abbreviation")));
                    scorePart.setPartAbbreviationDisplay(ScorePartFactory.newNameDisplay(XmlUtil.getChildElement(partListSubelement, "part-abbreviation-display")));
                    List<Element> groupElements = XmlUtil.getChildElements(partListSubelement, "group");
                    for (Element groupElement : groupElements) {
                        ScorePartGroup scorePartGroup = new ScorePartGroup();
                        scorePartGroup.setGroup(XmlUtil.getElementText(groupElement));
                        scorePart.getGroups().add(scorePartGroup);
                    }
                    List<Element> scoreInstrumentElements = XmlUtil.getChildElements(partListSubelement, "score-instrument");
                    for(Element scoreInstrumentElement : scoreInstrumentElements) {
                        ScoreInstrument scoreInstrument = new ScoreInstrument();
                        scoreInstrument.setScoreInstrumentId(scoreInstrumentElement.getAttribute("id"));
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
                    List<Element> midiDeviceElements = XmlUtil.getChildElements(partListSubelement, "midi-device");
                    for (Element midiDeviceElement : midiDeviceElements) {
                        handleScorePartMidi(scorePart, midiDeviceElement.getAttribute("id"), midiDeviceElement, null);
                    }
                    List<Element> midiInstrumentElements = XmlUtil.getChildElements(partListSubelement, "midi-instrument");
                    for(Element midiInstrumentElement : midiInstrumentElements) {
                        handleScorePartMidi(scorePart, midiInstrumentElement.getAttribute("id"), null, midiInstrumentElement);
                    }
                    partList.getPartItems().add(scorePart);
                    break;
            }
        }
    }

    private void handleScorePartMidi(ScorePart scorePart, String id, Element midiDeviceElement, Element midiInstrumentElement) {
        ScorePartMidi scorePartMidi;
        if (StringUtil.isEmpty(id)) {
            scorePartMidi = new ScorePartMidi();
            scorePart.getScorePartMidis().add(scorePartMidi);
        } else {
            scorePartMidi = scorePart.getScorePartMidis().stream().filter(spm -> spm.getScorePartMidiId().equals(id)).findAny().orElse(null);
            if (scorePartMidi == null) {
                scorePartMidi = new ScorePartMidi();
                scorePartMidi.setScorePartMidiId(id);
                scorePart.getScorePartMidis().add(scorePartMidi);
            }
        }

        if (midiDeviceElement != null) scorePartMidi.setMidiDevice(ScorePartFactory.newMidiDevice(midiDeviceElement));
        else if (midiInstrumentElement != null) scorePartMidi.setMidiInstrument(ScorePartFactory.newMidiInstrument(midiInstrumentElement));
    }
}
