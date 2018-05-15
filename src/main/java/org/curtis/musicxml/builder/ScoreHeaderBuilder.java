package org.curtis.musicxml.builder;

import org.curtis.musicxml.builder.musicdata.LayoutBuilder;
import org.curtis.musicxml.builder.util.BuilderUtil;
import org.curtis.musicxml.identity.Identification;
import org.curtis.musicxml.identity.Miscellaneous;
import org.curtis.musicxml.identity.MiscellaneousField;
import org.curtis.musicxml.identity.TypedText;
import org.curtis.musicxml.identity.encoding.Encoder;
import org.curtis.musicxml.identity.encoding.Encoding;
import org.curtis.musicxml.identity.encoding.EncodingDate;
import org.curtis.musicxml.identity.encoding.EncodingDescription;
import org.curtis.musicxml.identity.encoding.Software;
import org.curtis.musicxml.identity.encoding.Supports;
import org.curtis.musicxml.layout.Appearance;
import org.curtis.musicxml.layout.Distance;
import org.curtis.musicxml.layout.LineWidth;
import org.curtis.musicxml.layout.NoteSize;
import org.curtis.musicxml.layout.OtherAppearance;
import org.curtis.musicxml.layout.Scaling;
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
import org.curtis.util.DateUtil;

import java.util.HashMap;
import java.util.List;
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
        buildIdentification(scoreHeader.getIdentification());
        buildDefaults();
        for (Credit credit : scoreHeader.getCredits()) {
            buildCredit(credit);
        }
        buildPartList();

        return stringBuilder;
    }

    private void buildIdentification(Identification identification) {
        if (identification == null) return;

        appendLine("<identification>");
        List<Encoding> encodings = identification.getEncodings();
        if (!encodings.isEmpty()) {
            appendLine("<encoding>");
            for (Encoding encoding : encodings) {
                if (encoding instanceof EncodingDate) {
                    EncodingDate encodingDate = (EncodingDate)encoding;
                    buildElementWithValue("encoding-date", DateUtil.formatDate(encodingDate.getEncodingDate()));
                }
                else if (encoding instanceof Encoder) {
                    Encoder encoder = (Encoder)encoding;
                    buildTypedText(encoder.getEncoder(), "encoder");
                }
                if (encoding instanceof Software) {
                    Software software = (Software)encoding;
                    buildElementWithValue("software", software.getSoftware());
                }
                else if (encoding instanceof EncodingDescription) {
                    EncodingDescription encodingDescription = (EncodingDescription)encoding;
                    buildElementWithValue("encoding-description", encodingDescription.getEncodingDescription());
                }
                else if (encoding instanceof Supports) {
                    Supports supports = (Supports)encoding;
                    Map<String, String> attributes = new HashMap<>();
                    // TODO: supports type
                    attributes.put("type", "yes");
                    attributes.put("element", supports.getElement());
                    attributes.put("attribute", supports.getAttribute());
                    attributes.put("value", supports.getValue());
                    buildElementWithAttributes("supports", attributes);
                }
            }
            buildElementWithValue("source", identification.getSource());
            Miscellaneous miscellaneous = identification.getMiscellaneous();
            if (miscellaneous != null) {
                appendLine("<miscellaneous>");
                for (MiscellaneousField miscellaneousField : miscellaneous.getMiscellaneousFields()) buildElementWithValueAndAttribute("miscellaneous-field", miscellaneousField.getValue(), "name", miscellaneousField.getName());
                appendLine("</miscellaneous>");
            }
            appendLine("</encoding>");
        }
        buildElementWithValue("source", identification.getSource());
        appendLine("</identification>");
    }

    private void buildDefaults() {
        Defaults defaults = scoreHeader.getDefaults();

        if (defaults == null) return;
        appendLine("<defaults>");
        Scaling scaling = defaults.getScaling();
        if (scaling != null) {
            appendLine("<scaling>");
            buildElementWithValue("millimeters", scaling.getMillimeters());
            buildElementWithValue("tenths", BuilderUtil.stringValue(scaling.getTenths()));
            appendLine("</scaling>");
        }
        append(LayoutBuilder.buildLayout(defaults.getLayout()));
        Appearance appearance = defaults.getAppearance();
        if (appearance != null) {
            appendLine("<appearance>");
            for (LineWidth lineWidth : appearance.getLineWidths()) {
                buildElementWithValueAndAttribute("line-width", BuilderUtil.stringValue(lineWidth.getValue()), "type", lineWidth.getLineWidthType());
            }
            for (NoteSize noteSize : appearance.getNoteSizes()) {
                // TODO: note size decimal
                buildElementWithValueAndAttribute("note-size", 100, "type", BuilderUtil.enumValue(noteSize.getType()));
            }
            for (Distance distance : appearance.getDistances()) {
                buildElementWithValueAndAttribute("distance", BuilderUtil.stringValue(distance.getValue()), "type", distance.getType());
            }
            for (OtherAppearance otherAppearance : appearance.getOtherAppearances()) buildElementWithValueAndAttribute("other-appearance", otherAppearance.getValue(), "type", otherAppearance.getType());
            appendLine("</appearance>");
        }
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
        buildIdentification(scorePart.getIdentification());
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

    private void buildTypedText(TypedText typedText, String elementName) {
        if (typedText == null) return;

        buildElementWithValueAndAttribute(elementName, typedText.getValue(), "type", typedText.getType());
    }
}
