package org.curtis.musicxml.factory;

import org.curtis.musicxml.common.CssFontSize;
import org.curtis.musicxml.common.DashedFormatting;
import org.curtis.musicxml.common.DisplayText;
import org.curtis.musicxml.common.Editorial;
import org.curtis.musicxml.common.EditorialVoice;
import org.curtis.musicxml.common.EnclosureShape;
import org.curtis.musicxml.common.Font;
import org.curtis.musicxml.common.FontSize;
import org.curtis.musicxml.common.FontStyle;
import org.curtis.musicxml.common.FontWeight;
import org.curtis.musicxml.common.FormattedText;
import org.curtis.musicxml.common.Level;
import org.curtis.musicxml.common.LevelDisplay;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.Position;
import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.common.PrintStyleAlign;
import org.curtis.musicxml.common.Printout;
import org.curtis.musicxml.common.StyleText;
import org.curtis.musicxml.common.SymbolSize;
import org.curtis.musicxml.common.Text;
import org.curtis.musicxml.common.TextDecoration;
import org.curtis.musicxml.common.TextFormatting;
import org.curtis.musicxml.handler.util.TypeUtil;
import org.curtis.musicxml.layout.PrintObjectStyleAlign;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.math.BigDecimal;

public class FormattingFactory {
    private FormattingFactory() {

    }

    public static FormattedText newFormattedText(Element formattedTextElement) {
        if(formattedTextElement == null) {
            return null;
        }

        String value = XmlUtil.getElementText(formattedTextElement);
        TextFormatting textFormatting = newTextFormatting(formattedTextElement);

        if (StringUtil.isEmpty(value) && textFormatting == null) return null;

        FormattedText formattedText = new FormattedText();
        formattedText.setValue(value);
        formattedText.setTextFormatting(textFormatting);

        return formattedText;
    }

    public static TextFormatting newTextFormatting(Element element) {
        if (element == null) return null;

        Location justify = (Location)FactoryUtil.enumValue(Location.class, element.getAttribute("justify"));
        PrintStyleAlign printStyleAlign = newPrintStyleAlign(element);
        TextDecoration textDecoration = LyricFactory.newTextDecoration(element);
        BigDecimal rotation = MathUtil.newBigDecimal(element.getAttribute("rotation"));
        String letterSpacing = element.getAttribute("letter-spacing");
        String lineHeight = element.getAttribute("line-height");
        String lang = element.getAttribute("xml:lang");
        String space = element.getAttribute("xml:space");
        Location dir = (Location)FactoryUtil.enumValue(Location.class, element.getAttribute("dir"));
        EnclosureShape enclosureShape = newEnclosureShape(element);

        if (justify == null && printStyleAlign == null && textDecoration == null && rotation == null && StringUtil.isEmpty(letterSpacing)
                && StringUtil.isEmpty(lineHeight) && StringUtil.isEmpty(lang) && StringUtil.isEmpty(space) && dir == null && enclosureShape == null) return null;

        TextFormatting textFormatting = new TextFormatting();
        textFormatting.setJustify(justify);
        textFormatting.setPrintStyleAlign(printStyleAlign);
        textFormatting.setTextDecoration(textDecoration);
        textFormatting.setTextRotation(rotation);
        textFormatting.setLetterSpacing(letterSpacing);
        textFormatting.setLineHeight(lineHeight);
        textFormatting.setLang(lang);
        textFormatting.setSpace(space);
        textFormatting.setTextDirection(dir);
        textFormatting.setEnclosure(enclosureShape);

        return textFormatting;
    }

    public static StyleText newStyleText(Element element) {
        if (element == null) return null;

        StyleText styleText = new StyleText();
        styleText.setValue(XmlUtil.getElementText(element));
        styleText.setPrintStyle(newPrintStyle(element));

        return styleText;
    }

    public static Text newText(Element element) {
        if (element == null) return null;

        Text text = null;
        String subelementName = element.getTagName();
        switch (subelementName) {
            case "display-text":
                DisplayText displayText = new DisplayText();
                displayText.setDisplayText(FormattingFactory.newFormattedText(element));
                text = displayText;
                break;
            case "accidental-text":
                text = NoteFactory.newAccidentalText(element);
                break;
        }

        return text;
    }

    public static PrintStyleAlign newPrintStyleAlign(Element element) {
        if(element == null) {
            return null;
        }

        Location halign = (Location)FactoryUtil.enumValue(Location.class, element.getAttribute("halign"));
        Location valign = (Location)FactoryUtil.enumValue(Location.class, element.getAttribute("valign"));
        PrintStyle printStyle = newPrintStyle(element);

        if (halign == null && valign == null && printStyle == null) return null;

        PrintStyleAlign printStyleAlign = new PrintStyleAlign();
        printStyleAlign.setHalign(halign);
        printStyleAlign.setValign(valign);
        printStyleAlign.setPrintStyle(printStyle);

        return printStyleAlign;
    }

    public static PrintStyle newPrintStyle(Element printStyleElement) {
        if(printStyleElement == null) {
            return null;
        }

        Position position = PlacementFactory.newPosition(printStyleElement);
        Font font = newFont(printStyleElement);
        String color = printStyleElement.getAttribute("color");

        if (position == null && font == null && StringUtil.isEmpty(color)) return null;

        PrintStyle printStyle = new PrintStyle();
        printStyle.setPosition(position);
        printStyle.setFont(font);
        printStyle.setColor(color);

        return printStyle;
    }

    public static PrintObjectStyleAlign newPrintObjectStyleAlign(Element element) {
        if(element == null) return null;

        Boolean printObject = getPrintObject(element);
        PrintStyleAlign printStyleAlign = newPrintStyleAlign(element);

        if (printObject == null && printStyleAlign == null) return null;

        PrintObjectStyleAlign printObjectStyleAlign = new PrintObjectStyleAlign();
        printObjectStyleAlign.setPrintObject(printObject);
        printObjectStyleAlign.setPrintStyleAlign(printStyleAlign);

        return printObjectStyleAlign;
    }

    public static Font newFont(Element fontElement) {
        if(fontElement == null) {
            return null;
        }

        String fontFamily = fontElement.getAttribute("font-family");
        String fontStyle = fontElement.getAttribute("font-style");
        String fontSizeValue = fontElement.getAttribute("font-size");
        String fontWeight = fontElement.getAttribute("font-weight");

        if (StringUtil.isEmpty(fontFamily) && StringUtil.isEmpty(fontStyle) && StringUtil.isEmpty(fontSizeValue) && StringUtil.isEmpty(fontWeight)) return null;

        Font font = new Font();
        font.setFontFamily(fontFamily);
        font.setFontStyle((FontStyle)FactoryUtil.enumValue(FontStyle.class, fontStyle));

        if (StringUtil.isNotEmpty(fontSizeValue)) {
            FontSize fontSize = new FontSize();
            CssFontSize cssFontSize = (CssFontSize)FactoryUtil.enumValue(CssFontSize.class, fontSizeValue);

            if (cssFontSize == null) {
                BigDecimal fontSizeNumber = MathUtil.newBigDecimal(fontSizeValue);
                fontSize.setFontSize(fontSizeNumber);
            } else fontSize.setCssFontSize(cssFontSize);

            font.setFontSize(fontSize);
        }

        font.setFontWeight((FontWeight)FactoryUtil.enumValue(FontWeight.class, fontWeight));

        return font;
    }

    public static SymbolSize newSymbolSize(Element symbolSizeElement) {
        if(symbolSizeElement == null) {
            return null;
        }

        return (SymbolSize)FactoryUtil.enumValue(SymbolSize.class, symbolSizeElement.getAttribute("size"));
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

        return (EnclosureShape)FactoryUtil.enumValue(EnclosureShape.class, element.getAttribute("enclosure"));
    }

    public static Editorial newEditorial(Element element) {
        if (element == null) return null;

        FormattedText footnote = newFormattedText(XmlUtil.getChildElement(element, "footnote"));
        Level level = newLevel(XmlUtil.getChildElement(element, "level"));

        if (footnote == null && level == null) return null;

        Editorial editorial = new Editorial();
        editorial.setFootnote(footnote);
        editorial.setLevel(level);

        return editorial;
    }

    public static EditorialVoice newEditorialVoice(Element element) {
        if (element == null) return null;

        FormattedText footnote = newFormattedText(XmlUtil.getChildElement(element, "footnote"));
        Level level = newLevel(XmlUtil.getChildElement(element, "level"));
        String voice = XmlUtil.getChildElementText(element, "voice");

        if (footnote == null && level == null && StringUtil.isEmpty(voice)) return null;

        EditorialVoice editorialVoice = new EditorialVoice();
        editorialVoice.setFootnote(footnote);
        editorialVoice.setLevel(level);
        editorialVoice.setVoice(voice);

        return editorialVoice;
    }

    public static Level newLevel(Element element) {
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
