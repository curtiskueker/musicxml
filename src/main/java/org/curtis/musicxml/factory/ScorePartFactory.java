package org.curtis.musicxml.factory;

import org.curtis.musicxml.common.MidiDevice;
import org.curtis.musicxml.common.MidiInstrument;
import org.curtis.musicxml.common.NameDisplay;
import org.curtis.musicxml.common.Text;
import org.curtis.musicxml.common.play.Ipa;
import org.curtis.musicxml.common.play.Mute;
import org.curtis.musicxml.common.play.MuteType;
import org.curtis.musicxml.common.play.OtherPlay;
import org.curtis.musicxml.common.play.Play;
import org.curtis.musicxml.common.play.PlayType;
import org.curtis.musicxml.common.play.SemiPitchedType;
import org.curtis.musicxml.common.play.SemiPitched;
import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.musicxml.score.GroupName;
import org.curtis.musicxml.score.PartName;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class ScorePartFactory {
    private ScorePartFactory() {

    }

    public static GroupName newGroupName(Element element) {
        if (element == null) return null;

        GroupName groupName = new GroupName();
        groupName.setGroupName(XmlUtil.getElementText(element));
        groupName.setPrintStyle(FormattingFactory.newPrintStyle(element));
        groupName.setJustify(PlacementUtil.getLocation(element.getAttribute("justify")));

        return groupName;
    }

    public static NameDisplay newNameDisplay(Element element) {
        if (element == null) return null;

        NameDisplay nameDisplay = new NameDisplay();
        for(Element subelement : XmlUtil.getChildElements(element)) {
            Text text = FormattingFactory.newText(subelement);
            if (text != null) {
                nameDisplay.getTextList().add(text);
                text.setNameDisplay(nameDisplay);
            }
        }
        nameDisplay.setPrintObject(FormattingFactory.getPrintObject(element));

        return nameDisplay;
    }

    public static PartName newPartName(Element element) {
        if (element == null) return null;

        PartName partName = new PartName();
        partName.setPartName(XmlUtil.getElementText(element));
        partName.setPartNamePrintStyle(FormattingFactory.newPrintStyle(element));
        partName.setPartNamePrintObject(FormattingFactory.getPrintObject(element));
        partName.setPartNameJustify(PlacementUtil.getLocation(element.getAttribute("justify")));

        return partName;
    }

    public static MidiDevice newMidiDevice(Element element) {
        if (element == null) return null;

        MidiDevice midiDevice = new MidiDevice();
        midiDevice.setMidiDeviceId(element.getAttribute("id"));
        midiDevice.setValue(XmlUtil.getElementText(element));
        midiDevice.setPort(StringUtil.getInteger(element.getAttribute("port")));

        return midiDevice;
    }

    public static MidiInstrument newMidiInstrument(Element element) {
        if (element == null) return null;

        MidiInstrument midiInstrument = new MidiInstrument();
        midiInstrument.setMidiInstrumentId(element.getAttribute("id"));
        midiInstrument.setMidiChannel(StringUtil.getInteger(XmlUtil.getChildElementText(element, "midi-channel")));
        midiInstrument.setMidiName(XmlUtil.getChildElementText(element, "midi-name"));
        midiInstrument.setMidiBank(StringUtil.getInteger(XmlUtil.getChildElementText(element, "midi-bank")));
        midiInstrument.setMidiProgram(StringUtil.getInteger(XmlUtil.getChildElementText(element, "midi-program")));
        midiInstrument.setMidiUnpitched(StringUtil.getInteger(XmlUtil.getChildElementText(element, "midi-unpitched")));
        midiInstrument.setVolume(MathUtil.newBigDecimal(XmlUtil.getChildElementText(element, "volume")));
        midiInstrument.setPan(MathUtil.newBigDecimal(XmlUtil.getChildElementText(element, "pan")));
        midiInstrument.setVolume(MathUtil.newBigDecimal(XmlUtil.getChildElementText(element, "volume")));

        return midiInstrument;
    }

    public static Play newPlay(Element element) {
        if (element ==  null) return null;

        Play play = new Play();
        play.setPlayId(element.getAttribute("id"));
        List<Element> playSubelements = XmlUtil.getChildElements(element);
        for (Element playSubelement : playSubelements) {
            PlayType playType = null;
            switch (playSubelement.getTagName()) {
                case "ipa":
                    Ipa ipa = new Ipa();
                    ipa.setValue(XmlUtil.getElementText(playSubelement));
                    playType = ipa;
                    break;
                case "mute":
                    Mute mute = new Mute();
                    mute.setMuteType((MuteType)FactoryUtil.enumValue(MuteType.class, XmlUtil.getElementText(playSubelement)));
                    playType = mute;
                    break;
                case "semi-pitched":
                    SemiPitched semiPitched = new SemiPitched();
                    semiPitched.setSemiPitchedType((SemiPitchedType)FactoryUtil.enumValue(SemiPitchedType.class, XmlUtil.getElementText(playSubelement)));
                    playType = semiPitched;
                    break;
                case "other-play":
                    OtherPlay otherPlay = new OtherPlay();
                    otherPlay.setValue(XmlUtil.getElementText(playSubelement));
                    otherPlay.setType(playSubelement.getAttribute("type"));
                    playType = otherPlay;
                    break;
            }
            if (playType != null) play.getPlayTypes().add(playType);
        }

        return play;
    }
}
