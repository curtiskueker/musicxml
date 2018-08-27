package org.curtis.musicxml.builder;

import org.curtis.musicxml.builder.musicdata.LayoutBuilder;
import org.curtis.musicxml.builder.musicdata.MusicDataBuilder;
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
import org.curtis.musicxml.link.Bookmark;
import org.curtis.musicxml.link.Link;
import org.curtis.musicxml.link.LinkAttributes;
import org.curtis.musicxml.score.Credit;
import org.curtis.musicxml.score.CreditDisplay;
import org.curtis.musicxml.score.CreditImage;
import org.curtis.musicxml.score.CreditType;
import org.curtis.musicxml.score.CreditWords;
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
import org.curtis.musicxml.score.ScorePartGroup;
import org.curtis.musicxml.score.ScorePartMidi;
import org.curtis.musicxml.score.Work;
import org.curtis.musicxml.score.instrument.Ensemble;
import org.curtis.musicxml.score.instrument.InstrumentType;
import org.curtis.musicxml.score.instrument.ScoreInstrument;
import org.curtis.musicxml.score.instrument.Solo;
import org.curtis.util.DateUtil;
import org.curtis.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreHeaderBuilder extends MusicDataBuilder {
    private ScoreHeader scoreHeader;

    public ScoreHeaderBuilder(ScoreHeader scoreHeader) {
        this.scoreHeader = scoreHeader;
    }

    public StringBuilder build() {
        Work work = scoreHeader.getWork();
        if (work != null) {
            buildStartElement("work");
            buildElementWithValue("work-number", work.getWorkNumber());
            buildElementWithValue("work-title", work.getWorkTitle());
            LinkAttributes opus = work.getOpus();
            if (opus != null) {
                buildOpenElement("opus");
                append(XLinkBuilder.buildLinkAttributes(opus));
                buildCloseEmptyElement();
            }
            buildEndElement("work");
        }
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

        buildStartElement("identification");
        for (TypedText creator : identification.getCreators()) buildTypedText(creator, "creator");
        for (TypedText rights : identification.getRightsList()) buildTypedText(rights, "rights");
        List<Encoding> encodings = identification.getEncodings();
        if (!encodings.isEmpty()) {
            buildStartElement("encoding");
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
                    attributes.put("type", BuilderUtil.yesOrNo(supports.getType()));
                    attributes.put("element", supports.getElement());
                    attributes.put("attribute", supports.getAttribute());
                    attributes.put("value", supports.getValue());
                    buildElementWithAttributes("supports", attributes);
                }
            }
            buildElementWithValue("source", identification.getSource());
            for (TypedText relation : identification.getRelations()) buildTypedText(relation, "relation");
            Miscellaneous miscellaneous = identification.getMiscellaneous();
            if (miscellaneous != null) {
                buildStartElement("miscellaneous");
                for (MiscellaneousField miscellaneousField : miscellaneous.getMiscellaneousFields()) buildElementWithValueAndAttribute("miscellaneous-field", miscellaneousField.getValue(), "name", miscellaneousField.getName());
                buildEndElement("miscellaneous");
            }
            buildEndElement("encoding");
        }
        buildElementWithValue("source", identification.getSource());
        buildEndElement("identification");
    }

    private void buildDefaults() {
        Defaults defaults = scoreHeader.getDefaults();

        if (defaults == null) return;
        buildStartElement("defaults");
        Scaling scaling = defaults.getScaling();
        if (scaling != null) {
            buildStartElement("scaling");
            buildElementWithValue("millimeters", scaling.getMillimeters());
            buildElementWithValue("tenths", scaling.getTenths());
            buildEndElement("scaling");
        }
        append(LayoutBuilder.buildLayout(defaults.getLayout()));
        Appearance appearance = defaults.getAppearance();
        if (appearance != null) {
            buildStartElement("appearance");
            for (LineWidth lineWidth : appearance.getLineWidths()) {
                buildElementWithValueAndAttribute("line-width", lineWidth.getValue(), "type", lineWidth.getLineWidthType());
            }
            for (NoteSize noteSize : appearance.getNoteSizes()) {
                buildElementWithValueAndAttribute("note-size", noteSize.getValue(), "type", noteSize.getType());
            }
            for (Distance distance : appearance.getDistances()) {
                buildElementWithValueAndAttribute("distance", distance.getValue(), "type", distance.getType());
            }
            for (OtherAppearance otherAppearance : appearance.getOtherAppearances()) buildElementWithValueAndAttribute("other-appearance", otherAppearance.getValue(), "type", otherAppearance.getType());
            buildEndElement("appearance");
        }
        buildElementWithAttributes("music-font", FormattingBuilder.buildFont(defaults.getMusicFont()));
        buildElementWithAttributes("word-font", FormattingBuilder.buildFont(defaults.getWordFont()));
        for (LyricFont lyricFont : defaults.getLyricFonts()) buildLyricFont(lyricFont);
        for (LyricLanguage lyricLanguage : defaults.getLyricLanguages()) buildLyricLanguage(lyricLanguage);
        buildEndElement("defaults");
    }

    private void buildLyricFont(LyricFont lyricFont) {
        if (lyricFont == null) return;

        Map<String, String> attributes = new HashMap<>();
        attributes.put("number", lyricFont.getNumber());
        attributes.put("name", lyricFont.getName());
        attributes.putAll(FormattingBuilder.buildFont(lyricFont.getFont()));
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

        buildOpenElement("credit");
        buildAttribute("page", credit.getPage());
        buildCloseElement();
        for (CreditType creditType : credit.getCreditTypes()) {
            buildElementWithValue("credit-type", creditType.getType());
        }
        for (CreditDisplay creditDisplay : credit.getCreditDisplays()) {
            for (Link link : creditDisplay.getLinks()) {
                append(XLinkBuilder.buildLink(link));
            }
            for (Bookmark bookmark : creditDisplay.getBookmarks()) {
                append(XLinkBuilder.buildBookmark(bookmark));
            }
            if (creditDisplay instanceof CreditImage) {
                CreditImage creditImage = (CreditImage)creditDisplay;
                buildImage(creditImage.getImage());
            } else if (creditDisplay instanceof CreditWords) {
                CreditWords creditWords = (CreditWords)creditDisplay;
                buildFormattedText("credit-words", creditWords.getCreditWords());
            }
        }
        buildEndElement("credit");
    }

    private void buildPartList() {
        buildStartElement("part-list");
        PartList partList = scoreHeader.getPartList();
        for (PartItem partItem : partList.getPartItems()) {
            if (partItem instanceof PartGroup) buildPartGroup((PartGroup)partItem);
            else if (partItem instanceof ScorePart) buildScorePart((ScorePart)partItem);
        }
        buildEndElement("part-list");
    }

    private void buildPartGroup(PartGroup partGroup) {
        buildOpenElement("part-group");
        buildAttribute("type", partGroup.getType());
        buildAttribute("number", partGroup.getNumber());
        buildCloseElement();
        buildGroupName("group-name", partGroup.getGroupName());
        buildNameDisplay("group-name-display", partGroup.getGroupNameDisplay());
        buildGroupName("group-abbreviation", partGroup.getGroupAbbreviation());
        buildNameDisplay("group-abbreviation-display", partGroup.getGroupAbbreviationDisplay());
        GroupSymbol groupSymbol = partGroup.getGroupSymbol();
        if (groupSymbol != null) {
            Map<String, String> groupSymbolAttributes = new HashMap<>();
            groupSymbolAttributes.putAll(PlacementBuilder.buildPosition(groupSymbol.getPosition()));
            groupSymbolAttributes.put("color", groupSymbol.getColor());
            buildElementWithValueAndAttributes("group-symbol", groupSymbol.getGroupSymbolType(), groupSymbolAttributes);
        }
        GroupBarline groupBarline = partGroup.getGroupBarline();
        if (groupBarline != null) {
            String groupBarlineValue = BuilderUtil.enumValue(groupBarline.getGroupBarlineType());
            groupBarlineValue = groupBarlineValue.replace("mensurstrich", "Mensurstrich");
            buildElementWithValueAndAttribute("group-barline", groupBarlineValue, "color", groupBarline.getColor());
        }
        if (partGroup.getGroupTime()) buildElement("group-time");
        buildEditorial(partGroup.getEditorial());
        buildEndElement("part-group");
    }

    private void buildGroupName(String elementName, GroupName groupName) {
        if (groupName == null) return;

        Map<String, String> attributes = new HashMap<>();
        attributes.putAll(FormattingBuilder.buildPrintStyle(groupName.getPrintStyle()));
        attributes.put("justify", BuilderUtil.enumValue(groupName.getJustify()));
        buildElementWithValueAndAttributes(elementName, groupName.getGroupName(), attributes);
    }

    private void buildScorePart(ScorePart scorePart) {
        buildOpenElement("score-part");
        buildAttribute("id", scorePart.getScorePartId());
        buildCloseElement();
        buildIdentification(scorePart.getIdentification());
        buildPartName("part-name", scorePart.getPartName());
        buildNameDisplay("part-name-display", scorePart.getPartNameDisplay());
        buildPartName("part-abbreviation", scorePart.getPartAbbreviation());
        buildNameDisplay("part-abbreviation-display", scorePart.getPartAbbreviationDisplay());
        for (ScorePartGroup group : scorePart.getGroups()) {
            buildElementWithValue("group", group.getGroup());
        }
        for (ScoreInstrument scoreInstrument : scorePart.getScoreInstruments()) {
            buildOpenElement("score-instrument");
            buildAttribute("id", scoreInstrument.getScoreInstrumentId());
            buildCloseElement();
            buildElementWithValue("instrument-name", scoreInstrument.getInstrumentName());
            buildElementWithValue("instrument-abbreviation", scoreInstrument.getInstrumentAbbreviation());
            buildElementWithValue("instrument-sound", scoreInstrument.getInstrumentSound());
            InstrumentType instrumentType = scoreInstrument.getInstrumentType();
            if (instrumentType != null) {
                if (instrumentType instanceof Solo) buildElement("solo");
                else if (instrumentType instanceof Ensemble) {
                    Ensemble ensemble = (Ensemble)instrumentType;
                    buildElementWithOptionalValue("ensemble", ensemble.getValue());
                }
            }
            String virtualLibrary = scoreInstrument.getVirtualLibrary();
            String virtualName = scoreInstrument.getVirtualName();
            if (StringUtil.isNotEmpty(virtualLibrary) || StringUtil.isNotEmpty(virtualName)) {
                buildStartElement("virtual-instrument");
                buildElementWithValue("virtual-library", virtualLibrary);
                buildElementWithValue("virtual-name", virtualName);
                buildEndElement("virtual-instrument");
            }
            buildEndElement("score-instrument");
        }
        for (ScorePartMidi scorePartMidi : scorePart.getScorePartMidis()) {
            buildMidiDevice(scorePartMidi.getMidiDevice());
            buildMidiInstrument(scorePartMidi.getMidiInstrument());
        }
        buildEndElement("score-part");
    }

    private void buildPartName(String elementName, PartName partName) {
        if (partName == null) return;

        Map<String, String> attributes = new HashMap<>();
        attributes.put("print-object", BuilderUtil.yesOrNo(partName.getPartNamePrintObject()));
        attributes.putAll(FormattingBuilder.buildPrintStyle(partName.getPartNamePrintStyle()));
        attributes.put("justify", BuilderUtil.enumValue(partName.getPartNameJustify()));
        buildElementWithValueAndAttributes(elementName, partName.getPartName(), attributes);
    }

    private void buildTypedText(TypedText typedText, String elementName) {
        if (typedText == null) return;

        buildElementWithValueAndAttribute(elementName, typedText.getValue(), "type", typedText.getType());
    }
}
