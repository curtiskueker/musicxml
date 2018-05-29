package org.curtis.musicxml.builder;

import org.curtis.musicxml.builder.util.BuilderUtil;
import org.curtis.musicxml.common.CssFontSize;
import org.curtis.musicxml.common.DashedFormatting;
import org.curtis.musicxml.common.Font;
import org.curtis.musicxml.common.FontSize;
import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.common.PrintStyleAlign;
import org.curtis.musicxml.common.TextDecoration;

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

    public static Map<String, String> buildDashedFormatting(DashedFormatting dashedFormatting) {
        Map<String, String> attributes = new HashMap<>();
        if (dashedFormatting == null) return attributes;

        attributes.put("dash-length", BuilderUtil.stringValue(dashedFormatting.getDashLength()));
        attributes.put("space-length", BuilderUtil.stringValue(dashedFormatting.getSpaceLength()));

        return attributes;
    }
}
