package org.curtis.musicxml.handler;

import org.curtis.musicxml.common.MidiInstrument;
import org.curtis.musicxml.factory.AttributesFactory;
import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.score.GroupName;
import org.curtis.musicxml.score.GroupSymbol;
import org.curtis.musicxml.score.PartGroup;
import org.curtis.musicxml.score.PartList;
import org.curtis.musicxml.score.PartName;
import org.curtis.musicxml.score.ScorePart;
import org.curtis.musicxml.score.instrument.ScoreInstrument;
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

                        GroupName groupName = new GroupName();
                        groupName.setGroupName(XmlUtil.getChildElementText(partListSubelement, "group-name"));
                        currentPartGroup.setGroupName(groupName);

                        GroupName abbreviatedGroupName = new GroupName();
                        abbreviatedGroupName.setGroupName(XmlUtil.getChildElementText(partListSubelement, "group-abbreviation"));
                        currentPartGroup.setGroupAbbreviation(abbreviatedGroupName);

                        GroupSymbol groupSymbol = new GroupSymbol();
                        groupSymbol.setGroupSymbolType(AttributesFactory.newGroupSymbolType(partListSubelement));
                        currentPartGroup.setGroupSymbol(groupSymbol);
                    } else if(type.equals("stop")) {
                        partList.getPartItems().add(currentPartGroup);
                        currentPartGroup = null;
                    }

                    break;
                case "score-part":
                    ScorePart scorePart = new ScorePart();
                    scorePart.setId(partListSubelement.getAttribute("id"));

                    PartName partName = new PartName();
                    partName.setPartName(XmlUtil.getChildElementText(partListSubelement, "part-name"));
                    scorePart.setPartName(partName);

                    PartName partAbbreviation = new PartName();
                    partAbbreviation.setPartName(XmlUtil.getChildElementText(partListSubelement, "part-abbreviation"));
                    scorePart.setPartAbbreviation(partAbbreviation);

                    List<Element> scoreInstrumentElements = XmlUtil.getChildElements(partListSubelement, "score-instrument");
                    List<ScoreInstrument> scoreInstruments = new ArrayList<>();
                    for(Element scoreInstrumentElement : scoreInstrumentElements) {
                        ScoreInstrument scoreInstrument = new ScoreInstrument();
                        scoreInstrument.setId(scoreInstrumentElement.getAttribute("id"));
                        scoreInstrument.setInstrumentName(XmlUtil.getChildElementText(scoreInstrumentElement, "instrument-name"));

                        scoreInstruments.add(scoreInstrument);
                    }
                    scorePart.setScoreInstruments(scoreInstruments);

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
