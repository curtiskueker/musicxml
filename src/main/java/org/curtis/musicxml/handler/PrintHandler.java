package org.curtis.musicxml.handler;

import org.curtis.musicxml.direction.MeasureNumberingType;
import org.curtis.musicxml.direction.Print;
import org.curtis.musicxml.factory.DisplayFactory;
import org.curtis.musicxml.factory.FactoryUtil;
import org.curtis.musicxml.factory.LayoutFactory;
import org.curtis.musicxml.factory.ScorePartFactory;
import org.curtis.musicxml.util.TypeUtil;
import org.curtis.musicxml.layout.Layout;
import org.curtis.musicxml.layout.MeasureLayout;
import org.curtis.musicxml.score.MusicData;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

public class PrintHandler extends MusicDataHandler {
    public PrintHandler() {

    }

    public MusicData handle(Element element) {
        Print print = new Print();
        print.setElementId(element.getAttribute("id"));
        Layout layout = LayoutFactory.newLayout(element);

        Element measureLayoutElement = XmlUtil.getChildElement(element, "measure-layout");
        if (measureLayoutElement != null) {
            MeasureLayout measureLayout = new MeasureLayout();
            measureLayout.setMeasureDistance(MathUtil.newBigDecimal(XmlUtil.getChildElementText(measureLayoutElement, "measure-distance")));
            print.setMeasureLayout(measureLayout);
        }
        Element measureNumberingElement = XmlUtil.getChildElement(element, "measure-numbering");
        print.setMeasureNumberingValue(FactoryUtil.enumValue(MeasureNumberingType.class, XmlUtil.getElementText(measureNumberingElement)));
        print.setMeasureNumberingDisplay(DisplayFactory.newDisplay(measureNumberingElement));
        print.setPartNameDisplay(ScorePartFactory.newNameDisplay(XmlUtil.getChildElement(element, "part-name-display")));
        print.setPartAbbreviationDisplay(ScorePartFactory.newNameDisplay(XmlUtil.getChildElement(element, "part-abbreviation-display")));

        print.setStaffSpacing(MathUtil.newBigDecimal(element.getAttribute("staff-spacing")));
        print.setNewSystem(TypeUtil.getYesNo(element.getAttribute("new-system")));
        print.setNewPage(TypeUtil.getYesNo(element.getAttribute("new-page")));
        print.setBlankPage(StringUtil.getInteger(element.getAttribute("blank-page")));
        print.setPageNumber(element.getAttribute("page-number"));

        print.setLayout(layout);

        return print;
    }
}
