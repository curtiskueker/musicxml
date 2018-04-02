package org.curtis.musicxml.builder;

import org.curtis.musicxml.builder.util.BuilderUtil;
import org.curtis.musicxml.link.LinkAttributes;
import org.curtis.musicxml.score.Credit;
import org.curtis.musicxml.score.Defaults;
import org.curtis.musicxml.score.GroupBarline;
import org.curtis.musicxml.score.GroupName;
import org.curtis.musicxml.score.GroupSymbol;
import org.curtis.musicxml.score.LyricFont;
import org.curtis.musicxml.score.LyricLanguage;
import org.curtis.musicxml.score.PartGroup;
import org.curtis.musicxml.score.PartItem;
import org.curtis.musicxml.score.PartList;
import org.curtis.musicxml.score.PartName;
import org.curtis.musicxml.score.ScoreHeader;
import org.curtis.musicxml.score.ScorePart;

import java.util.HashMap;
import java.util.Map;

public class ScoreHeaderBuilder extends BaseBuilder {
    private ScoreHeader scoreHeader;

    public ScoreHeaderBuilder(ScoreHeader scoreHeader) {
        this.scoreHeader = scoreHeader;
    }

    public StringBuilder build() {
        appendLine("<work>");
        buildElementWithValue("work-number", scoreHeader.getWorkNumber());
        buildElementWithValue("work-title", scoreHeader.getWorkTitle());
        LinkAttributes opus = scoreHeader.getOpus();
        if (opus != null) {
            append("<opus");
            XLinkBuilder xLinkBuilder = new XLinkBuilder();
            append(xLinkBuilder.buildLinkAttributes(opus));
            appendLine("/>");
        }
        appendLine("</work>");
        buildElementWithValue("movement-number", scoreHeader.getMovementNumber());
        buildElementWithValue("movement-title", scoreHeader.getMovementTitle());
        buildDefaults();
        for (Credit credit : scoreHeader.getCredits()) {
            buildCredit(credit);
        }
        buildPartList();

        return stringBuilder;
    }

    private void buildDefaults() {
        Defaults defaults = scoreHeader.getDefaults();

        if (defaults == null) return;
        appendLine("<defaults>");
        FormattingBuilder formattingBuilder = new FormattingBuilder();
        append(formattingBuilder.buildFont("music-font", defaults.getMusicFont()));
        append(formattingBuilder.buildFont("word-font", defaults.getWordFont()));
        for (LyricFont lyricFont : defaults.getLyricFonts()) buildLyricFont(lyricFont);
        for (LyricLanguage lyricLanguage : defaults.getLyricLanguages()) buildLyricLanguage(lyricLanguage);
        appendLine("</defaults>");
    }

    private void buildLyricFont(LyricFont lyricFont) {
        if (lyricFont == null) return;

        Map<String, String> attributes = new HashMap<>();
        attributes.put("number", lyricFont.getNumber());
        attributes.put("name", lyricFont.getName());
        buildElementWithAttributes("lyric-font", attributes);
    }

    private void buildLyricLanguage(LyricLanguage lyricLanguage) {
        if (lyricLanguage == null) return;

        Map<String, String> attributes = new HashMap<>();
        attributes.put("number", lyricLanguage.getNumber());
        attributes.put("name", lyricLanguage.getName());
        attributes.put("xml:lang", lyricLanguage.getLang());
        buildElementWithAttributes("lyric-language", attributes);
    }

    private void buildCredit(Credit credit) {
        if (credit == null) return;

        append("<credit");
        buildAttribute("page", credit.getPage());
        appendLine(">");
        for (String creditType : credit.getCreditTypes()) {
            buildElementWithValue("credit-type", creditType);
        }
        // TODO: credit-words
        appendLine("<credit-words>Text</credit-words>");
        appendLine("</credit>");
    }

    private void buildPartList() {
        appendLine("<part-list>");
        PartList partList = scoreHeader.getPartList();
        for (PartItem partItem : partList.getPartItems()) {
            if (partItem instanceof PartGroup) buildPartGroup((PartGroup)partItem);
            else if (partItem instanceof ScorePart) buildSscorePart((ScorePart)partItem);
        }
        appendLine("</part-list>");
    }

    private void buildPartGroup(PartGroup partGroup) {
        append("<part-group");
        // TODO: part-group type
        buildAttribute("type", "start");
        buildAttribute("number", partGroup.getNumber());
        appendLine(">");
        buildGroupName("group-name", partGroup.getGroupName());
        buildGroupName("group-abbreviation", partGroup.getGroupAbbreviation());
        GroupSymbol groupSymbol = partGroup.getGroupSymbol();
        if (groupSymbol != null) {
            buildElementWithValue("group-symbol", BuilderUtil.enumValue(groupSymbol.getGroupSymbolType()));
        }
        GroupBarline groupBarline = partGroup.getGroupBarline();
        if (groupBarline != null) {
            String groupBarlineValue = BuilderUtil.enumValue(groupBarline.getGroupBarlineType());
            groupBarlineValue = groupBarlineValue.replace("mensurstrich", "Mensurstrich");
            buildElementWithValue("group-barline", groupBarlineValue);
        }
        appendLine("</part-group>");
    }

    private void buildGroupName(String elementName, GroupName groupName) {
        if (groupName == null) return;

        buildElementWithValue(elementName, groupName.getGroupName());
    }

    private void buildSscorePart(ScorePart scorePart) {
        append("<score-part");
        buildAttribute("id", scorePart.getScorePartId());
        appendLine(">");
        buildPartName("part-name", scorePart.getPartName());
        buildPartName("part-abbreviation", scorePart.getPartAbbreviation());
        for (String group : scorePart.getGroups()) {
            buildElementWithValue("group", group);
        }
        appendLine("</score-part>");
    }

    private void buildPartName(String elementName, PartName partName) {
        if (partName == null) return;

        buildElementWithValue(elementName, partName.getPartName());
    }
}
