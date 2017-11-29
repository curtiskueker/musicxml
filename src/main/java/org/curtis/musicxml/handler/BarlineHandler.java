package org.curtis.musicxml.handler;

import org.curtis.musicxml.barline.BarStyle;
import org.curtis.musicxml.barline.BarStyleColor;
import org.curtis.musicxml.barline.Barline;
import org.curtis.musicxml.barline.Ending;
import org.curtis.musicxml.barline.Repeat;
import org.curtis.musicxml.barline.RepeatDirection;
import org.curtis.musicxml.barline.Winged;
import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.factory.NotationFactory;
import org.curtis.musicxml.factory.OrnamentFactory;
import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.musicxml.handler.util.TypeUtil;
import org.curtis.musicxml.note.notation.Fermata;
import org.curtis.musicxml.score.MusicData;
import org.curtis.util.MathUtil;
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
        barline.setSegno(element.getAttribute("segno"));
        barline.setCoda(element.getAttribute("coda"));
        barline.setDivisions(MathUtil.newBigDecimal(element.getAttribute("divisions")));

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
                case "wavy-line":
                    barline.setWavyLine(OrnamentFactory.newWavyLine(barlineSubelement));
                    break;
                case "segno":
                    barline.setSegnoPrint(FormattingFactory.newPrintStyleAlign(barlineSubelement));
                    break;
                case "coda":
                    barline.setCodaPrint(FormattingFactory.newPrintStyleAlign(barlineSubelement));
                    break;
                case "fermata":
                    List<Fermata> fermataList = barline.getFermataList();
                    fermataList.add(NotationFactory.newFermata(barlineSubelement));
                    break;
                case "ending":
                    Ending ending = new Ending();
                    ending.setValue(XmlUtil.getElementText(barlineSubelement));
                    // TODO: comma-separated ending numbers
                    ending.setNumber(barlineSubelement.getAttribute("number"));
                    ending.setType(PlacementUtil.getConnection(barlineSubelement.getAttribute("type")));
                    ending.setPrintObject(TypeUtil.getYesNo(barlineSubelement.getAttribute("print-object")));
                    ending.setPrintStyle(FormattingFactory.newPrintStyle(barlineSubelement));
                    ending.setEndLength(MathUtil.newBigDecimal(barlineSubelement.getAttribute("end-length")));
                    ending.setTextX(MathUtil.newBigDecimal(barlineSubelement.getAttribute("text-x")));
                    ending.setTextY(MathUtil.newBigDecimal(barlineSubelement.getAttribute("text-y")));
                    barline.setEnding(ending);
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
