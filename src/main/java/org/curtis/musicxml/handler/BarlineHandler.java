package org.curtis.musicxml.handler;

import org.curtis.musicxml.barline.BarStyle;
import org.curtis.musicxml.barline.BarStyleColor;
import org.curtis.musicxml.barline.Barline;
import org.curtis.musicxml.barline.Repeat;
import org.curtis.musicxml.barline.RepeatDirection;
import org.curtis.musicxml.handler.util.HandlerUtil;
import org.curtis.musicxml.score.MusicData;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class BarlineHandler extends AbstractHandler {
    private List<MusicData> musicDataList;

    public BarlineHandler(Element element, List<MusicData> musicDataList) {
        super(element);
        this.musicDataList = musicDataList;
    }

    public void handle() {
        Barline barline = new Barline();
        barline.setLocation(HandlerUtil.getLocation(getElement().getAttribute("location")));

        List<Element> barlineSubelements = XmlUtil.getChildElements(getElement());
        for(Element barlineSubelement : barlineSubelements) {
            String barlineSubelementName = barlineSubelement.getTagName();
            switch (barlineSubelementName) {
                case "bar-style":
                    BarStyleColor barStyleColor = new BarStyleColor();
                    String barStyle = XmlUtil.getElementText(barlineSubelement);
                    switch (barStyle) {
                        case "light-heavy":
                            barStyleColor.setBarStyle(BarStyle.LIGHT_HEAVY);
                            break;
                    }
                    barline.setBarStyle(barStyleColor);
                    break;
                case "repeat":
                    Repeat repeat = new Repeat();
                    String repeatDirection = barlineSubelement.getAttribute("direction");
                    switch (repeatDirection) {
                        case "backward":
                            repeat.setDirection(RepeatDirection.BACKWARD);
                            break;
                        case "forward":
                            repeat.setDirection(RepeatDirection.FORWARD);
                            break;
                    }
                    barline.setRepeat(repeat);
                    break;
            }
        }

        musicDataList.add(barline);
    }
}
