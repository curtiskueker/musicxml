package org.curtis.musicxml.factory;

import org.curtis.musicxml.common.DashedFormatting;
import org.curtis.musicxml.common.DisplayText;
import org.curtis.musicxml.display.Editorial;
import org.curtis.musicxml.display.EnclosureShape;
import org.curtis.musicxml.common.Level;
import org.curtis.musicxml.common.LevelDisplay;
import org.curtis.musicxml.common.Printout;
import org.curtis.musicxml.common.SymbolSize;
import org.curtis.musicxml.common.TextDisplay;
import org.curtis.musicxml.display.Display;
import org.curtis.musicxml.display.Footnote;
import org.curtis.musicxml.layout.SystemDivider;
import org.curtis.musicxml.util.TypeUtil;
import org.curtis.util.MathUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.math.BigDecimal;

public class FormattingFactory {
    private FormattingFactory() {

    }

    public static TextDisplay newText(Element element) {
        if (element == null) return null;

        TextDisplay text = null;
        String subelementName = element.getTagName();
        switch (subelementName) {
            case "display-text":
                DisplayText displayText = new DisplayText();
                DisplayFactory.setFormattedDisplay(displayText, element);
                text = displayText;
                break;
            case "accidental-text":
                text = NoteFactory.newAccidentalText(element);
                break;
        }

        return text;
    }

    public static SystemDivider newSystemDivider(Element element) {
        if(element == null) return null;

        Boolean printObject = getPrintObject(element);
        Display display = DisplayFactory.newDisplay(element);

        if (printObject == null && display == null) return null;

        SystemDivider systemDivider = new SystemDivider();
        systemDivider.setPrintObject(printObject);
        systemDivider.setDisplay(display);

        return systemDivider;
    }

    public static SymbolSize newSymbolSize(Element symbolSizeElement) {
        if(symbolSizeElement == null) {
            return null;
        }

        return FactoryUtil.enumValue(SymbolSize.class, symbolSizeElement.getAttribute("size"));
    }

    public static DashedFormatting newDashedFormatting(Element dashedFormattingElement) {
        if (dashedFormattingElement == null) return null;

        BigDecimal dashLength = MathUtil.newBigDecimal(dashedFormattingElement.getAttribute("dash-length"));
        BigDecimal spaceLength = MathUtil.newBigDecimal(dashedFormattingElement.getAttribute("space-length"));

        if (dashLength == null && spaceLength == null) return null;

        DashedFormatting dashedFormatting = new DashedFormatting();
        dashedFormatting.setDashLength(dashLength);
        dashedFormatting.setSpaceLength(spaceLength);

        return dashedFormatting;
    }

    public static Boolean getPrintObject(Element element) {
        if (element == null) return null;

        return TypeUtil.getYesNo(element.getAttribute("print-object"));
    }

    public static Printout newPrintout(Element element) {
        if (element == null) return null;

        Boolean printObject = getPrintObject(element);
        Boolean printDot = TypeUtil.getYesNo(element.getAttribute("print-dot"));
        Boolean printSpacing = TypeUtil.getYesNo(element.getAttribute("print-spacing"));
        Boolean printLyric = TypeUtil.getYesNo(element.getAttribute("print-lyric"));

        if (printObject == null && printDot == null && printSpacing == null && printLyric == null) return null;

        Printout printout = new Printout();
        printout.setPrintObject(printObject);
        printout.setPrintDot(printDot);
        printout.setPrintSpacing(printSpacing);
        printout.setPrintLyric(printLyric);

        return printout;
    }

    public static EnclosureShape newEnclosureShape(Element element) {
        if(element == null) return null;

        return FactoryUtil.enumValue(EnclosureShape.class, element.getAttribute("enclosure"));
    }

    public static Editorial newEditorial(Element element) {
        if (element == null) return null;

        Footnote footnote = DisplayFactory.newFootnote(XmlUtil.getChildElement(element, "footnote"));
        Level level = newLevel(XmlUtil.getChildElement(element, "level"));

        if (footnote == null && level == null) return null;

        Editorial editorial = new Editorial();
        editorial.setFootnote(footnote);
        editorial.setLevel(level);

        return editorial;
    }

    private static Level newLevel(Element element) {
        if (element == null) return null;

        Level level = new Level();
        level.setValue(XmlUtil.getElementText(element));
        level.setReference(TypeUtil.getYesNo(element.getAttribute("reference")));
        level.setLevelDisplay(newLevelDisplay(element));

        return level;
    }

    public static LevelDisplay newLevelDisplay(Element element) {
        if (element == null) return null;

        Boolean parentheses = TypeUtil.getYesNo(element.getAttribute("parentheses"));
        Boolean bracket = TypeUtil.getYesNo(element.getAttribute("bracket"));
        SymbolSize symbolSize = newSymbolSize(element);

        if (parentheses == null && bracket == null && symbolSize == null) return null;

        LevelDisplay levelDisplay = new LevelDisplay();
        levelDisplay.setParentheses(parentheses);
        levelDisplay.setBracket(bracket);
        levelDisplay.setSize(symbolSize);

        return levelDisplay;
    }
}
