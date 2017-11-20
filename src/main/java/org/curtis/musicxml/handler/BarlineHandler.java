package org.curtis.musicxml.handler;

import org.curtis.musicxml.barline.BarStyle;
import org.curtis.musicxml.barline.BarStyleColor;
import org.curtis.musicxml.barline.Barline;
import org.curtis.musicxml.barline.Repeat;
import org.curtis.musicxml.barline.RepeatDirection;
import org.curtis.musicxml.barline.Winged;
import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.musicxml.score.MusicData;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class BarlineHandler extends MusicDataHandler {
    public BarlineHandler() {

    }

    public MusicData handle(Element element) {
        Barline barline = new Barline();
        barline.setLocation(PlacementUtil.getLocation(element.getAttribute("location")));

        List<Element> barlineSubelements = XmlUtil.getChildElements(element);
        for(Element barlineSubelement : barlineSubelements) {
            String barlineSubelementName = barlineSubelement.getTagName();
            switch (barlineSubelementName) {
                case "bar-style":
                    BarStyleColor barStyleColor = new BarStyleColor();
                    String barStyle = XmlUtil.getElementText(barlineSubelement);
                    switch (barStyle) {
                        case "regular":
                            barStyleColor.setBarStyle(BarStyle.REGULAR);
                            break;
                        case "dotted":
                            barStyleColor.setBarStyle(BarStyle.DOTTED);
                            break;
                        case "dashed":
                            barStyleColor.setBarStyle(BarStyle.DASHED);
                            break;
                        case "heavy":
                            barStyleColor.setBarStyle(BarStyle.HEAVY);
                            break;
                        case "light-light":
                            barStyleColor.setBarStyle(BarStyle.LIGHT_LIGHT);
                            break;
                        case "light-heavy":
                            barStyleColor.setBarStyle(BarStyle.LIGHT_HEAVY);
                            break;
                        case "heavy-light":
                            barStyleColor.setBarStyle(BarStyle.HEAVY_LIGHT);
                            break;
                        case "heavy-heavy":
                            barStyleColor.setBarStyle(BarStyle.HEAVY_HEAVY);
                            break;
                        case "tick":
                            barStyleColor.setBarStyle(BarStyle.TICK);
                            break;
                        case "short":
                            barStyleColor.setBarStyle(BarStyle.SHORT);
                            break;
                        case "none":
                            barStyleColor.setBarStyle(BarStyle.NONE);
                            break;
                    }
                    barStyleColor.setColor(barlineSubelement.getAttribute("color"));
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
                    repeat.setTimes(StringUtil.getInteger(barlineSubelement.getAttribute("times")));
                    String winged = barlineSubelement.getAttribute("winged");
                    if(StringUtil.isNotEmpty(winged)) {
                        switch (winged) {
                            case "none":
                                repeat.setWinged(Winged.NONE);
                                break;
                            case "straignt":
                                repeat.setWinged(Winged.STRAIGHT);
                                break;
                            case "curved":
                                repeat.setWinged(Winged.CURVED);
                                break;
                            case "double-straight":
                                repeat.setWinged(Winged.DOUBLE_STRAIGHT);
                                break;
                            case "double-curved":
                                repeat.setWinged(Winged.DOUBLE_CURVED);
                                break;
                        }
                    }
                    barline.setRepeat(repeat);
                    break;
            }
        }

        return barline;
    }
}
