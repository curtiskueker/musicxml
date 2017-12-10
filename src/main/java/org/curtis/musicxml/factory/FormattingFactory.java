package org.curtis.musicxml.factory;

import org.curtis.musicxml.common.CssFontSize;
import org.curtis.musicxml.common.DashedFormatting;
import org.curtis.musicxml.common.Font;
import org.curtis.musicxml.common.FontSize;
import org.curtis.musicxml.common.FontStyle;
import org.curtis.musicxml.common.FontWeight;
import org.curtis.musicxml.common.FormattedText;
import org.curtis.musicxml.common.Position;
import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.common.PrintStyleAlign;
import org.curtis.musicxml.common.Printout;
import org.curtis.musicxml.common.SymbolSize;
import org.curtis.musicxml.common.TextFormatting;
import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.musicxml.handler.util.TypeUtil;
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

    public static TextFormatting newTextFormatting(Element textFormattingElement) {
        TextFormatting textFormatting = new TextFormatting();
        textFormatting.setJustify(PlacementUtil.getLocation(textFormattingElement.getAttribute("justify")));

        return textFormatting;
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

    public static Printout newPrintout(Element element) {
        Printout printout = new Printout();
        printout.setPrintObject(TypeUtil.getYesNoDefaultYes(element.getAttribute("print-object")));
        printout.setPrintDot(TypeUtil.getYesNoDefaultYes(element.getAttribute("print-dot")));
        printout.setPrintSpacing(TypeUtil.getYesNoDefaultYes(element.getAttribute("print-spacing")));
        printout.setPrintLyric(TypeUtil.getYesNoDefaultYes(element.getAttribute("print-lyric")));

        return printout;
    }
}
