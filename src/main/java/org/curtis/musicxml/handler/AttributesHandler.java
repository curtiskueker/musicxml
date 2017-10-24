package org.curtis.musicxml.handler;

import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.attributes.Clef;
import org.curtis.musicxml.attributes.ClefSign;
import org.curtis.musicxml.attributes.Time;
import org.curtis.musicxml.attributes.TimeSignature;
import org.curtis.musicxml.attributes.key.Key;
import org.curtis.musicxml.attributes.key.TraditionalKey;
import org.curtis.musicxml.score.MusicData;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.math.BigDecimal;
import java.util.List;

public class AttributesHandler extends AbstractHandler {
    private List<MusicData> musicDataList;

    public AttributesHandler(Element element, List<MusicData> musicDataList) {
        super(element);
        this.musicDataList = musicDataList;
    }

    public StringBuilder handle() {
        Attributes attributes = new Attributes();

        List<Element> attributesSubelements = XmlUtil.getChildElements(getElement());
        for(Element attributeSubelement : attributesSubelements) {
            switch (attributeSubelement.getTagName()) {
                case "divisions":
                    attributes.setDivisions(new BigDecimal(XmlUtil.getElementText(attributeSubelement)));
                    break;
                case "key":
                    Element keyElement = XmlUtil.getChildElement(attributeSubelement, "key");
                    List<Key> keys = attributes.getKeys();
                    Key key = new Key();
                    TraditionalKey traditionalKey = key.getTraditionalKey();
                    traditionalKey.setFifths(Integer.parseInt(XmlUtil.getChildElementText(keyElement, "fifths")));
                    traditionalKey.setMode(XmlUtil.getChildElementText(keyElement, "mode"));
                    keys.add(key);
                    attributes.setKeys(keys);
                    break;
                case "time":
                    Element timeElement = XmlUtil.getChildElement(attributeSubelement, "time");
                    List<Time> timeList = attributes.getTimeList();
                    Time time = new Time();
                    List<TimeSignature> timeSignatures = time.getTimeSignatures();
                    TimeSignature timeSignature = new TimeSignature();
                    timeSignature.setBeats(XmlUtil.getChildElementText(timeElement, "beats"));
                    timeSignature.setBeatType(XmlUtil.getChildElementText(timeElement, "beat-type"));
                    timeSignatures.add(timeSignature);
                    timeList.add(time);
                    attributes.setTimeList(timeList);
                    break;
                case "clef":
                    Element clefElement = XmlUtil.getChildElement(attributeSubelement, "clef");
                    Clef clef = new Clef();
                    String sign = XmlUtil.getChildElementText(clefElement, "sign");
                    ClefSign clefSign;
                    switch (sign) {
                        case "G":
                            clefSign = ClefSign.G;
                            break;
                        case "F":
                            clefSign = ClefSign.F;
                            break;
                        case "C":
                            clefSign = ClefSign.C;
                            break;
                        case "percussion":
                            clefSign = ClefSign.PERCUSSION;
                            break;
                        case "TAB":
                            clefSign = ClefSign.TAB;
                            break;
                        case "jianpu":
                            clefSign = ClefSign.JIANPU;
                            break;
                        case "none":
                            clefSign = ClefSign.NONE;
                            break;
                        default:
                            clefSign = null;
                    }
                    clef.setSign(clefSign);
                    clef.setLine(Integer.parseInt(XmlUtil.getChildElementText(clefElement, "line")));
                    attributes.setClef(clef);

                    break;
            }
        }

        musicDataList.add(attributes);

        return stringBuilder;
    }
}
