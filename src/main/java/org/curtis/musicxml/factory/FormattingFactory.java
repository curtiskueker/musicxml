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
import org.curtis.musicxml.common.Position;
import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.common.PrintStyleAlign;
import org.curtis.musicxml.common.Printout;
import org.curtis.musicxml.common.StyleText;
import org.curtis.musicxml.common.SymbolSize;
import org.curtis.musicxml.common.Text;
import org.curtis.musicxml.common.TextFormatting;
import org.curtis.musicxml.handler.util.PlacementUtil;
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

        FormattedText formattedText = new FormattedText();
        formattedText.setValue(XmlUtil.getElementText(formattedTextElement));

        TextFormatting textFormatting = newTextFormatting(formattedTextElement);

        PrintStyleAlign printStyleAlign = newPrintStyleAlign(formattedTextElement);
        textFormatting.setPrintStyleAlign(printStyleAlign);
        formattedText.setTextFormatting(textFormatting);

        return formattedText;
    }

    public static TextFormatting newTextFormatting(Element element) {
        TextFormatting textFormatting = new TextFormatting();
        textFormatting.setJustify(PlacementUtil.getLocation(element.getAttribute("justify")));
        textFormatting.setPrintStyleAlign(newPrintStyleAlign(element));
        textFormatting.setTextDecoration(LyricFactory.newTextDecoration(element));
        textFormatting.setTextRotation(MathUtil.newBigDecimal(element.getAttribute("rotation")));
        textFormatting.setLetterSpacing(element.getAttribute("letter-spacing"));
        textFormatting.setLineHeight(element.getAttribute("line-height"));
        textFormatting.setLang(element.getAttribute("xml:lang"));
        textFormatting.setSpace(element.getAttribute("xml:space"));
        textFormatting.setTextDirection(PlacementUtil.getLocation(element.getAttribute("dir")));
        textFormatting.setEnclosure(newEnclosureShape(element));

        return textFormatting;
    }

    public static StyleText newStyleText(Element element) {
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

    public static PrintStyleAlign newPrintStyleAlign(Element printStyleAlignElement) {
        if(printStyleAlignElement == null) {
            return null;
        }

        PrintStyleAlign printStyleAlign = new PrintStyleAlign();
        printStyleAlign.setHalign(PlacementUtil.getLocation(printStyleAlignElement.getAttribute("halign")));
        printStyleAlign.setValign(PlacementUtil.getLocation(printStyleAlignElement.getAttribute("valign")));

        PrintStyle printStyle = newPrintStyle(printStyleAlignElement);
        printStyleAlign.setPrintStyle(printStyle);

        return printStyleAlign;
    }

    public static PrintStyle newPrintStyle(Element printStyleElement) {
        if(printStyleElement == null) {
            return null;
        }

        PrintStyle printStyle = new PrintStyle();

        Position position = PlacementFactory.newPosition(printStyleElement);
        printStyle.setPosition(position);

        Font font = newFont(printStyleElement);
        printStyle.setFont(font);

        printStyle.setColor(printStyleElement.getAttribute("color"));

        return printStyle;
    }

    public static PrintObjectStyleAlign newPrintObjectStyleAlign(Element element) {
        if(element == null) return null;

        PrintObjectStyleAlign printObjectStyleAlign = new PrintObjectStyleAlign();
        printObjectStyleAlign.setPrintObject(getPrintObject(element));
        printObjectStyleAlign.setPrintStyleAlign(newPrintStyleAlign(element));

        return printObjectStyleAlign;
    }

    public static Font newFont(Element fontElement) {
        if(fontElement == null) {
            return null;
        }

        Font font = new Font();
        font.setFontFamily(fontElement.getAttribute("font-family"));

        String fontStyle = fontElement.getAttribute("font-style");
        if(StringUtil.isNotEmpty(fontStyle)) {
            switch (fontStyle) {
                case "normal":
                    font.setFontStyle(FontStyle.NORMAL);
                    break;
                case "italic":
                    font.setFontStyle(FontStyle.ITALIC);
                    break;
            }
        }

        FontSize fontSize = new FontSize();
        String fontSizeValue = fontElement.getAttribute("font-size");
        if (StringUtil.isNotEmpty(fontSizeValue)) {
            switch (fontSizeValue) {
                case "xx-small":
                    fontSize.setCssFontSize(CssFontSize.XX_SMALL);
                    break;
                case "x-small":
                    fontSize.setCssFontSize(CssFontSize.X_SMALL);
                    break;
                case "small":
                    fontSize.setCssFontSize(CssFontSize.SMALL);
                    break;
                case "medium":
                    fontSize.setCssFontSize(CssFontSize.MEDIUM);
                    break;
                case "large":
                    fontSize.setCssFontSize(CssFontSize.LARGE);
                    break;
                case "x-large":
                    fontSize.setCssFontSize(CssFontSize.X_LARGE);
                    break;
                case "xx-large":
                    fontSize.setCssFontSize(CssFontSize.XX_LARGE);
                    break;
                default:
                    BigDecimal fontSizeNumber = MathUtil.newBigDecimal(fontSizeValue);
                    fontSize.setFontSize(fontSizeNumber);
            }
        }
        font.setFontSize(fontSize);

        String fontWeight = fontElement.getAttribute("font-weight");
        if (StringUtil.isNotEmpty(fontWeight)) {
            switch (fontWeight) {
                case "normal":
                    font.setFontWeight(FontWeight.NORMAL);
                    break;
                case "bold":
                    font.setFontWeight(FontWeight.BOLD);
                    break;
            }
        }

        return font;
    }

    public static SymbolSize newSymbolSize(Element symbolSizeElement) {
        if(symbolSizeElement == null) {
            return null;
        }

        String size = symbolSizeElement.getAttribute("size");
        if(StringUtil.isEmpty(size)) {
            return null;
        }

        switch (size) {
            case "full":
                return SymbolSize.FULL;
            case "cue":
                return SymbolSize.CUE;
            case "large":
                return SymbolSize.LARGE;
            default:
                return null;
        }
    }

    public static DashedFormatting newDashedFormatting(Element dashedFormattingElement) {
        DashedFormatting dashedFormatting = new DashedFormatting();
        dashedFormatting.setDashLength(MathUtil.newBigDecimal(dashedFormattingElement.getAttribute("dash-length")));
        dashedFormatting.setSpaceLength(MathUtil.newBigDecimal(dashedFormattingElement.getAttribute("space-length")));

        return dashedFormatting;
    }

    public static Boolean getPrintObject(Element element) {
        if (element == null) return null;

        return TypeUtil.getYesNo(element.getAttribute("print-object"));
    }

    public static Printout newPrintout(Element element) {
        Printout printout = new Printout();
        printout.setPrintObject(getPrintObject(element));
        printout.setPrintDot(TypeUtil.getYesNo(element.getAttribute("print-dot")));
        printout.setPrintSpacing(TypeUtil.getYesNo(element.getAttribute("print-spacing")));
        printout.setPrintLyric(TypeUtil.getYesNo(element.getAttribute("print-lyric")));

        return printout;
    }

    public static EnclosureShape newEnclosureShape(Element element) {
        if(element == null) return null;

        String enclosure = element.getAttribute("enclosure");
        if(StringUtil.isEmpty(enclosure)) return null;

        switch (enclosure) {
            case "rectangle":
                return EnclosureShape.RECTANGLE;
            case "square":
                return EnclosureShape.SQUARE;
            case "oval":
                return EnclosureShape.OVAL;
            case "circle":
                return EnclosureShape.CIRCLE;
            case "bracket":
                return EnclosureShape.BRACKET;
            case "triangle":
                return EnclosureShape.TRIANGLE;
            case "diamond":
                return EnclosureShape.DIAMOND;
            case "none":
                return EnclosureShape.NONE;
            default:
                return null;
        }
    }

    public static Editorial newEditorial(Element element) {
        if (element == null) return null;

        Editorial editorial = new Editorial();
        editorial.setFootnote(newFormattedText(XmlUtil.getChildElement(element, "footnote")));
        editorial.setLevel(newLevel(XmlUtil.getChildElement(element, "level")));

        return editorial;
    }

    public static EditorialVoice newEditorialVoice(Element element) {
        if (element == null) return null;

        EditorialVoice editorialVoice = new EditorialVoice();
        editorialVoice.setFootnote(newFormattedText(XmlUtil.getChildElement(element, "footnote")));
        editorialVoice.setLevel(newLevel(XmlUtil.getChildElement(element, "level")));
        editorialVoice.setVoice(XmlUtil.getChildElementText(element, "voice"));

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

        LevelDisplay levelDisplay = new LevelDisplay();
        levelDisplay.setParentheses(TypeUtil.getYesNo(element.getAttribute("parentheses")));
        levelDisplay.setBracket(TypeUtil.getYesNo(element.getAttribute("bracket")));
        levelDisplay.setSize(FormattingFactory.newSymbolSize(element));

        return levelDisplay;
    }
}
