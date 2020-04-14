package org.curtis.musicxml.handler;

import org.curtis.musicxml.barline.BarStyle;
import org.curtis.musicxml.barline.Barline;
import org.curtis.musicxml.barline.BarlineLocation;
import org.curtis.musicxml.barline.BarlineEnding;
import org.curtis.musicxml.barline.BarlineEndingType;
import org.curtis.musicxml.barline.BarlineRepeat;
import org.curtis.musicxml.barline.BarlineRepeatDirection;
import org.curtis.musicxml.barline.Winged;
import org.curtis.musicxml.factory.DisplayFactory;
import org.curtis.musicxml.factory.FactoryUtil;
import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.factory.NotationFactory;
import org.curtis.musicxml.factory.OrnamentFactory;
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
        barline.setElementId(element.getAttribute("id"));
        barline.setEditorial(FormattingFactory.newEditorial(element));
        barline.setBarlineLocation(FactoryUtil.enumValue(BarlineLocation.class, element.getAttribute("location")));
        barline.setSegno(element.getAttribute("segno"));
        barline.setCoda(element.getAttribute("coda"));
        barline.setDivisions(MathUtil.newBigDecimal(element.getAttribute("divisions")));

        List<Element> barlineSubelements = XmlUtil.getChildElements(element);
        for(Element barlineSubelement : barlineSubelements) {
            String barlineSubelementName = barlineSubelement.getTagName();
            switch (barlineSubelementName) {
                case "bar-style":
                    barline.setBarStyle(FactoryUtil.enumValue(BarStyle.class, XmlUtil.getElementText(barlineSubelement)));
                    barline.setDisplay(DisplayFactory.newDisplay(barlineSubelement));
                    break;
                case "wavy-line":
                    barline.setWavyLine(OrnamentFactory.newWavyLine(barlineSubelement));
                    break;
                case "segno":
                    barline.setSegnoPrint(NotationFactory.newSegno(barlineSubelement));
                    break;
                case "coda":
                    barline.setCodaPrint(NotationFactory.newCoda(barlineSubelement));
                    break;
                case "fermata":
                    List<Fermata> fermataList = barline.getFermataList();
                    Fermata fermata = NotationFactory.newFermata(barlineSubelement);
                    fermataList.add(fermata);
                    fermata.setBarline(barline);
                    break;
                case "ending":
                    BarlineEnding ending = new BarlineEnding();
                    ending.setValue(XmlUtil.getElementText(barlineSubelement));
                    ending.setNumber(barlineSubelement.getAttribute("number"));
                    ending.setType(FactoryUtil.enumValue(BarlineEndingType.class, barlineSubelement.getAttribute("type")));
                    ending.setPrintObject(FormattingFactory.getPrintObject(barlineSubelement));
                    ending.setDisplay(DisplayFactory.newDisplay(barlineSubelement));
                    ending.setEndLength(MathUtil.newBigDecimal(barlineSubelement.getAttribute("end-length")));
                    ending.setTextX(MathUtil.newBigDecimal(barlineSubelement.getAttribute("text-x")));
                    ending.setTextY(MathUtil.newBigDecimal(barlineSubelement.getAttribute("text-y")));
                    barline.setEnding(ending);
                    break;
                case "repeat":
                    BarlineRepeat repeat = new BarlineRepeat();
                    repeat.setDirection(FactoryUtil.enumValue(BarlineRepeatDirection.class, barlineSubelement.getAttribute("direction")));
                    repeat.setTimes(StringUtil.getInteger(barlineSubelement.getAttribute("times")));
                    repeat.setWinged(FactoryUtil.enumValue(Winged.class, barlineSubelement.getAttribute("winged")));
                    barline.setRepeat(repeat);
                    break;
            }
        }

        return barline;
    }
}
