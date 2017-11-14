package org.curtis.musicxml.factory;

import org.curtis.musicxml.common.CssFontSize;
import org.curtis.musicxml.common.Font;
import org.curtis.musicxml.common.FontSize;
import org.curtis.musicxml.common.FontStyle;
import org.curtis.musicxml.common.FontWeight;
import org.curtis.musicxml.common.FormattedText;
import org.curtis.musicxml.common.Position;
import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.common.PrintStyleAlign;
import org.curtis.musicxml.common.TextFormatting;
import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.math.BigDecimal;

public class FormatFactory {
    private FormatFactory() {

    }

    public static FormattedText newFormattedText(Element formattedTextElement) {
        if(formattedTextElement == null) {
            return null;
        }

        FormattedText formattedText = new FormattedText();
        formattedText.setValue(XmlUtil.getElementText(formattedTextElement));

        TextFormatting textFormatting = new TextFormatting();
        textFormatting.setJustify(PlacementUtil.getLocation(formattedTextElement.getAttribute("justify")));

        PrintStyleAlign printStyleAlign = newPrintStyleAlign(formattedTextElement);
        textFormatting.setPrintStyleAlign(printStyleAlign);
        formattedText.setTextFormatting(textFormatting);

        return formattedText;
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
        PrintStyle printStyle = new PrintStyle();

        Position position = newPosition(printStyleElement);
        printStyle.setPosition(position);

        Font font = new Font();
        font.setFontFamily(printStyleElement.getAttribute("font-family"));

        String fontStyle = printStyleElement.getAttribute("font-style");
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
        String fontSizeValue = printStyleElement.getAttribute("font-size");
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

        String fontWeight = printStyleElement.getAttribute("font-weight");
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

        printStyle.setFont(font);

        printStyle.setColor(printStyleElement.getAttribute("color"));

        return printStyle;
    }

    public static Position newPosition(Element positionElement) {
        if(positionElement == null) {
            return null;
        }

        Position position = new Position();

        position.setDefaultX(MathUtil.newBigDecimal(positionElement.getAttribute("default-x")));
        position.setDefaultY(MathUtil.newBigDecimal(positionElement.getAttribute("default-y")));
        position.setRelativeX(MathUtil.newBigDecimal(positionElement.getAttribute("relative-x")));
        position.setRelativeY(MathUtil.newBigDecimal(positionElement.getAttribute("relative-y")));

        return position;
    }
}
