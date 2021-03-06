package org.curtis.musicxml.handler;

import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.factory.AttributesFactory;
import org.curtis.musicxml.factory.DisplayFactory;
import org.curtis.musicxml.factory.FactoryUtil;
import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.factory.IdentityFactory;
import org.curtis.musicxml.factory.ScorePartFactory;
import org.curtis.musicxml.score.GroupBarline;
import org.curtis.musicxml.score.GroupBarlineType;
import org.curtis.musicxml.score.GroupSymbol;
import org.curtis.musicxml.score.PartGroup;
import org.curtis.musicxml.score.PartListItem;
import org.curtis.musicxml.score.ScorePartMidi;
import org.curtis.musicxml.score.ScorePart;
import org.curtis.musicxml.score.ScorePartGroup;
import org.curtis.musicxml.score.instrument.Ensemble;
import org.curtis.musicxml.score.instrument.ScoreInstrument;
import org.curtis.musicxml.score.instrument.Solo;
import org.curtis.musicxml.score.instrument.VirtualInstrument;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class PartListHandler implements ScoreElementHandler {
    private List<PartListItem> partListItems;

    public PartListHandler(List<PartListItem> partListItems) {
        this.partListItems = partListItems;
    }

    public void handle(Element element) {
        List<Element> partListSubelements = XmlUtil.getChildElements(element);
        for(Element partListSubelement : partListSubelements) {
            String elementName = partListSubelement.getTagName();
            switch (elementName) {
                case "part-group":
                    PartGroup partGroup = new PartGroup();
                    partGroup.setType(FactoryUtil.enumValue(Connection.class, partListSubelement.getAttribute("type")));
                    partGroup.setEditorial(FormattingFactory.newEditorial(partListSubelement));
                    partGroup.setNumber(partListSubelement.getAttribute("number"));
                    partGroup.setGroupName(ScorePartFactory.newGroupName(XmlUtil.getChildElement(partListSubelement, "group-name")));
                    partGroup.setNameDisplay(ScorePartFactory.newNameDisplay(XmlUtil.getChildElement(partListSubelement, "group-name-display")));
                    partGroup.setGroupAbbreviation(ScorePartFactory.newGroupName(XmlUtil.getChildElement(partListSubelement, "group-abbreviation")));
                    partGroup.setAbbreviationDisplay(ScorePartFactory.newNameDisplay(XmlUtil.getChildElement(partListSubelement, "group-abbreviation-display")));
                    Element groupSymbolElement = XmlUtil.getChildElement(partListSubelement, "group-symbol");
                    if (groupSymbolElement != null) {
                        GroupSymbol groupSymbol = new GroupSymbol();
                        groupSymbol.setGroupSymbolType(AttributesFactory.newGroupSymbolType(groupSymbolElement));
                        groupSymbol.setDisplay(DisplayFactory.newDisplay(groupSymbolElement));
                        partGroup.setGroupSymbol(groupSymbol);
                    }
                    Element groupBarlineElement = XmlUtil.getChildElement(partListSubelement, "group-barline");
                    if (groupBarlineElement != null) {
                        GroupBarline groupBarline = new GroupBarline();
                        groupBarline.setGroupBarlineType(FactoryUtil.enumValue(GroupBarlineType.class, XmlUtil.getElementText(groupBarlineElement)));
                        groupBarline.setDisplay(DisplayFactory.newDisplay(partListSubelement));
                        partGroup.setGroupBarline(groupBarline);
                    }
                    partGroup.setGroupTime(XmlUtil.hasChildElement(partListSubelement, "group-time"));
                    partListItems.add(partGroup);
                    break;
                case "score-part":
                    ScorePart scorePart = new ScorePart();
                    scorePart.setScorePartId(partListSubelement.getAttribute("id"));
                    scorePart.setIdentification(IdentityFactory.newIdentification(XmlUtil.getChildElement(partListSubelement, "identification")));
                    scorePart.setPartName(ScorePartFactory.newPartName(XmlUtil.getChildElement(partListSubelement, "part-name")));
                    scorePart.setNameDisplay(ScorePartFactory.newNameDisplay(XmlUtil.getChildElement(partListSubelement, "part-name-display")));
                    scorePart.setPartAbbreviation(ScorePartFactory.newPartName(XmlUtil.getChildElement(partListSubelement, "part-abbreviation")));
                    scorePart.setAbbreviationDisplay(ScorePartFactory.newNameDisplay(XmlUtil.getChildElement(partListSubelement, "part-abbreviation-display")));
                    List<Element> groupElements = XmlUtil.getChildElements(partListSubelement, "group");
                    for (Element groupElement : groupElements) {
                        ScorePartGroup scorePartGroup = new ScorePartGroup();
                        scorePartGroup.setValue(XmlUtil.getElementText(groupElement));
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
                        if (virtualInstrumentElement != null) {
                            VirtualInstrument virtualInstrument = new VirtualInstrument();
                            virtualInstrument.setVirtualLibrary(XmlUtil.getChildElementText(virtualInstrumentElement, "virtual-library"));
                            virtualInstrument.setVirtualName(XmlUtil.getChildElementText(virtualInstrumentElement, "virtual-name"));
                            scoreInstrument.setVirtualInstrument(virtualInstrument);
                        }
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
                    partListItems.add(scorePart);
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
            scorePartMidi = scorePart.getScorePartMidis().stream().filter(spm -> (spm.getScorePartMidiId() != null && spm.getScorePartMidiId().equals(id))).findAny().orElse(null);
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
