package org.curtis.musicxml.builder;

import org.curtis.musicxml.common.CssFontSize;
import org.curtis.musicxml.common.DashedFormatting;
import org.curtis.musicxml.common.Font;
import org.curtis.musicxml.common.FontSize;
import org.curtis.musicxml.common.LevelDisplay;
import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.common.PrintStyleAlign;
import org.curtis.musicxml.common.Printout;
import org.curtis.musicxml.common.TextDecoration;
import org.curtis.musicxml.common.TextFormatting;
import org.curtis.musicxml.layout.PrintObjectStyleAlign;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class FormattingBuilder extends OutputBuilder {
    private FormattingBuilder() {

    }

    public static Map<String, String> buildFont(Font font) {
        Map<String, String> attributes = new HashMap<>();
        if (font == null) return attributes;

        attributes.put("font-family", font.getFontFamily());
        attributes.put("font-style", BuilderUtil.enumValue(font.getFontStyle()));
        FontSize fontSize = font.getFontSize();
        if (fontSize != null) {
            BigDecimal fontSizeValue = fontSize.getFontSize();
            if (fontSizeValue != null) attributes.put("font-size", BuilderUtil.stringValue(fontSizeValue));
            else {
                CssFontSize cssFontSize = fontSize.getCssFontSize();
                if (cssFontSize != null) attributes.put("font-size", BuilderUtil.enumValue(cssFontSize));
            }
        }
        attributes.put("font-weight", BuilderUtil.enumValue(font.getFontWeight()));

        return attributes;
    }

    public static Map<String, String> buildTextDecoration(TextDecoration textDecoration) {
        Map<String, String> attributes = new HashMap<>();
        if (textDecoration == null) return attributes;

        attributes.put("underline", BuilderUtil.stringValue(textDecoration.getUnderline()));
        attributes.put("overline", BuilderUtil.stringValue(textDecoration.getOverline()));
        attributes.put("line-through", BuilderUtil.stringValue(textDecoration.getLineThrough()));

        return attributes;
    }

    public static Map<String, String> buildPrintStyle(PrintStyle printStyle) {
        Map<String, String> attributes = new HashMap<>();
        if (printStyle == null) return attributes;

        attributes.putAll(PlacementBuilder.buildPosition(printStyle.getPosition()));
        attributes.putAll(buildFont(printStyle.getFont()));
        attributes.put("color", printStyle.getColor());

        return attributes;
    }

    public static Map<String, String> buildPrintStyleAlign(PrintStyleAlign printStyleAlign) {
        Map<String, String> attributes = new HashMap<>();
        if (printStyleAlign == null) return attributes;

        attributes.putAll(buildPrintStyle(printStyleAlign.getPrintStyle()));
        attributes.put("halign", BuilderUtil.enumValue(printStyleAlign.getHalign()));
        attributes.put("valign", BuilderUtil.enumValue(printStyleAlign.getValign()));

        return attributes;
    }

    public static Map<String, String> buildPrintObjectStyleAlign(PrintObjectStyleAlign printObjectStyleAlign) {
        Map<String, String> attributes = new HashMap<>();
        if (printObjectStyleAlign == null) return attributes;

        attributes.put("print-object", BuilderUtil.yesOrNo(printObjectStyleAlign.getPrintObject()));
        attributes.putAll(buildPrintStyleAlign(printObjectStyleAlign.getPrintStyleAlign()));

        return attributes;
    }

    public static Map<String, String> buildDashedFormatting(DashedFormatting dashedFormatting) {
        Map<String, String> attributes = new HashMap<>();
        if (dashedFormatting == null) return attributes;

        attributes.put("dash-length", BuilderUtil.stringValue(dashedFormatting.getDashLength()));
        attributes.put("space-length", BuilderUtil.stringValue(dashedFormatting.getSpaceLength()));

        return attributes;
    }

    public static Map<String, String> buildPrintout(Printout printout) {
        Map<String, String> attributes = new HashMap<>();
        if (printout == null) return attributes;

        attributes.put("print-object", BuilderUtil.yesOrNo(printout.getPrintObject()));
        attributes.put("print-dot", BuilderUtil.yesOrNo(printout.getPrintDot()));
        attributes.put("print-spacing", BuilderUtil.yesOrNo(printout.getPrintSpacing()));
        attributes.put("print-lyric", BuilderUtil.yesOrNo(printout.getPrintLyric()));

        return attributes;
    }

    public static Map<String, String> buildTextFormatting(TextFormatting textFormatting) {
        Map<String, String> attributes = new HashMap<>();
        if (textFormatting == null) return attributes;

        attributes.put("justify", BuilderUtil.enumValue(textFormatting.getJustify()));
        attributes.putAll(buildPrintStyleAlign(textFormatting.getPrintStyleAlign()));
        attributes.putAll(buildTextDecoration(textFormatting.getTextDecoration()));
        attributes.put("text-rotation", BuilderUtil.stringValue(textFormatting.getTextRotation()));
        attributes.put("letter-spacing", textFormatting.getLetterSpacing());
        attributes.put("line-height", textFormatting.getLineHeight());
        attributes.put("xml:lang", textFormatting.getLang());
        attributes.put("xml:space", textFormatting.getSpace());
        attributes.put("text-direction", BuilderUtil.enumValue(textFormatting.getTextDirection()));
        attributes.put("enclosure", BuilderUtil.enumValue(textFormatting.getEnclosure()));

        return attributes;
    }

    public static Map<String, String> buildLevelDisplay(LevelDisplay levelDisplay) {
        Map<String, String> attributes = new HashMap<>();
        if (levelDisplay == null) return attributes;

        attributes.put("parentheses", BuilderUtil.yesOrNo(levelDisplay.getParentheses()));
        attributes.put("bracket", BuilderUtil.yesOrNo(levelDisplay.getBracket()));
        attributes.put("size", BuilderUtil.enumValue(levelDisplay.getSize()));

        return attributes;
    }
}
