package org.curtis.musicxml.builder;

import org.curtis.musicxml.common.DashedFormatting;
import org.curtis.musicxml.common.LevelDisplay;
import org.curtis.musicxml.common.Printout;
import org.curtis.musicxml.layout.SystemDivider;
import org.curtis.musicxml.display.TextFormat;

import java.util.HashMap;
import java.util.Map;

public class FormattingBuilder extends OutputBuilder {
    private FormattingBuilder() {

    }

    public static Map<String, String> buildSystemDivider(SystemDivider systemDivider) {
        Map<String, String> attributes = new HashMap<>();
        if (systemDivider == null) return attributes;

        attributes.put("print-object", BuilderUtil.yesOrNo(systemDivider.getPrintObject()));
        attributes.putAll(DisplayBuilder.buildDisplay(systemDivider.getDisplay()));

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

    public static Map<String, String> buildTextFormat(TextFormat textFormat) {
        Map<String, String> attributes = new HashMap<>();
        if (textFormat == null) return attributes;

        attributes.put("justify", BuilderUtil.enumValue(textFormat.getJustify()));
        attributes.put("underline", BuilderUtil.stringValue(textFormat.getUnderline()));
        attributes.put("overline", BuilderUtil.stringValue(textFormat.getOverline()));
        attributes.put("line-through", BuilderUtil.stringValue(textFormat.getLineThrough()));
        attributes.put("text-rotation", BuilderUtil.stringValue(textFormat.getTextRotation()));
        attributes.put("letter-spacing", textFormat.getLetterSpacing());
        attributes.put("line-height", textFormat.getLineHeight());
        attributes.put("xml:lang", textFormat.getLang());
        attributes.put("xml:space", textFormat.getSpace());
        attributes.put("text-direction", BuilderUtil.enumValue(textFormat.getTextDirection()));
        attributes.put("enclosure", BuilderUtil.enumValue(textFormat.getEnclosure()));

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
