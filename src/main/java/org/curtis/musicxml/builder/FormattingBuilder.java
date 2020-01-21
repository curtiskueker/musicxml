package org.curtis.musicxml.builder;

import org.curtis.musicxml.common.DashedFormatting;
import org.curtis.musicxml.common.LevelDisplay;
import org.curtis.musicxml.common.Printout;
import org.curtis.musicxml.common.SymbolFormatting;
import org.curtis.musicxml.common.TextDecoration;
import org.curtis.musicxml.common.TextFormatting;
import org.curtis.musicxml.layout.SystemDivider;

import java.util.HashMap;
import java.util.Map;

public class FormattingBuilder extends OutputBuilder {
    private FormattingBuilder() {

    }

    public static Map<String, String> buildTextDecoration(TextDecoration textDecoration) {
        Map<String, String> attributes = new HashMap<>();
        if (textDecoration == null) return attributes;

        attributes.put("underline", BuilderUtil.stringValue(textDecoration.getUnderline()));
        attributes.put("overline", BuilderUtil.stringValue(textDecoration.getOverline()));
        attributes.put("line-through", BuilderUtil.stringValue(textDecoration.getLineThrough()));

        return attributes;
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

    public static Map<String, String> buildTextFormatting(TextFormatting textFormatting) {
        Map<String, String> attributes = new HashMap<>();
        if (textFormatting == null) return attributes;

        attributes.put("justify", BuilderUtil.enumValue(textFormatting.getJustify()));
        attributes.putAll(DisplayBuilder.buildDisplay(textFormatting.getDisplay()));
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

    public static Map<String, String> buildSymbolFormatting(SymbolFormatting symbolFormatting) {
        Map<String, String> attributes = new HashMap<>();
        if (symbolFormatting == null) return attributes;

        attributes.put("justify", BuilderUtil.enumValue(symbolFormatting.getJustify()));
        attributes.putAll(DisplayBuilder.buildDisplay(symbolFormatting.getDisplay()));
        attributes.putAll(buildTextDecoration(symbolFormatting.getTextDecoration()));
        attributes.put("text-rotation", BuilderUtil.stringValue(symbolFormatting.getTextRotation()));
        attributes.put("letter-spacing", symbolFormatting.getLetterSpacing());
        attributes.put("line-height", symbolFormatting.getLineHeight());
        attributes.put("text-direction", BuilderUtil.enumValue(symbolFormatting.getTextDirection()));
        attributes.put("enclosure", BuilderUtil.enumValue(symbolFormatting.getEnclosure()));

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
