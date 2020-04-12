package org.curtis.musicxml.builder;

import org.curtis.musicxml.builder.musicdata.LayoutBuilder;
import org.curtis.musicxml.builder.musicdata.MusicDataBuilder;
import org.curtis.musicxml.identity.Identification;
import org.curtis.musicxml.identity.IdentificationType;
import org.curtis.musicxml.identity.Miscellaneous;
import org.curtis.musicxml.identity.encoding.Encoder;
import org.curtis.musicxml.identity.encoding.Encoding;
import org.curtis.musicxml.identity.encoding.EncodingDate;
import org.curtis.musicxml.identity.encoding.EncodingDescription;
import org.curtis.musicxml.identity.encoding.Software;
import org.curtis.musicxml.identity.encoding.Supports;
import org.curtis.musicxml.layout.Appearance;
import org.curtis.musicxml.layout.Distance;
import org.curtis.musicxml.layout.Glyph;
import org.curtis.musicxml.layout.LineWidth;
import org.curtis.musicxml.layout.NoteSize;
import org.curtis.musicxml.layout.OtherAppearance;
import org.curtis.musicxml.link.Bookmark;
import org.curtis.musicxml.link.Link;
import org.curtis.musicxml.link.LinkAttributes;
import org.curtis.musicxml.score.Credit;
import org.curtis.musicxml.score.CreditDisplay;
import org.curtis.musicxml.score.CreditImage;
import org.curtis.musicxml.score.CreditSymbol;
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
import org.curtis.musicxml.score.instrument.VirtualInstrument;
import org.curtis.util.DateUtil;

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
        for (IdentificationType creator : identification.getCreators()) buildIdentificationType(creator);
        for (IdentificationType rights : identification.getRightsList()) buildIdentificationType(rights);
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
                    buildIdentificationType(encoder.getIdentificationType());
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
                    attributes.put("element", BuilderUtil.requiredValue(supports.getElement()));
                    attributes.put("attribute", supports.getAttribute());
                    attributes.put("value", supports.getValue());
                    buildElementWithAttributes("supports", attributes);
                }
            }
            buildEndElement("encoding");
            buildElementWithValue("source", identification.getSource());
            for (IdentificationType relation : identification.getRelations()) buildIdentificationType(relation);
            List<Miscellaneous> miscellaneousList = identification.getMiscellaneousList();
            if (!miscellaneousList.isEmpty()) {
                buildStartElement("miscellaneous");
                for (Miscellaneous miscellaneous : miscellaneousList) buildElementWithValueAndAttribute("miscellaneous-field", miscellaneous.getValue(), "name", BuilderUtil.requiredValue(miscellaneous.getName()));
                buildEndElement("miscellaneous");
            }
        }
        buildElementWithValue("source", identification.getSource());
        buildEndElement("identification");
    }

    private void buildDefaults() {
        Defaults defaults = scoreHeader.getDefaults();

        if (defaults == null) return;
        buildStartElement("defaults");
        if (defaults.hasScaling()) {
            buildStartElement("scaling");
            buildElementWithValue("millimeters", defaults.getScalingMillimeters());
            buildElementWithValue("tenths", defaults.getScalingTenths());
            buildEndElement("scaling");
        }
        append(LayoutBuilder.buildLayout(defaults.getLayout()));
        Appearance appearance = defaults.getAppearance();
        if (appearance != null) {
            buildStartElement("appearance");
            for (LineWidth lineWidth : appearance.getLineWidths()) {
                buildElementWithValueAndAttribute("line-width", lineWidth.getValue(), "type", BuilderUtil.requiredValue(lineWidth.getLineWidthType()));
            }
            for (NoteSize noteSize : appearance.getNoteSizes()) {
                buildElementWithValueAndAttribute("note-size", noteSize.getValue(), "type", BuilderUtil.enumValue(noteSize.getType()));
            }
            for (Distance distance : appearance.getDistances()) {
                buildElementWithValueAndAttribute("distance", distance.getValue(), "type", BuilderUtil.requiredValue(distance.getType()));
            }
            for (Glyph glyph : appearance.getGlyphs()) {
                buildElementWithValueAndAttribute("glyph", glyph.getValue(), "type", BuilderUtil.requiredValue(glyph.getType()));
            }
            for (OtherAppearance otherAppearance : appearance.getOtherAppearances()) buildElementWithValueAndAttribute("other-appearance", otherAppearance.getValue(), "type", BuilderUtil.requiredValue(otherAppearance.getType()));
            buildEndElement("appearance");
        }
        buildElementWithAttributes("music-font", DisplayBuilder.buildFont(defaults.getMusicFont()));
        buildElementWithAttributes("word-font", DisplayBuilder.buildFont(defaults.getWordFont()));
        for (LyricFont lyricFont : defaults.getLyricFonts()) buildLyricFont(lyricFont);
        for (LyricLanguage lyricLanguage : defaults.getLyricLanguages()) buildLyricLanguage(lyricLanguage);
        buildEndElement("defaults");
    }

    private void buildLyricFont(LyricFont lyricFont) {
        if (lyricFont == null) return;

        Map<String, String> attributes = new HashMap<>();
        attributes.put("number", lyricFont.getNumber());
        attributes.put("name", lyricFont.getName());
        attributes.putAll(DisplayBuilder.buildFont(lyricFont.getFont()));
        buildElementWithAttributes("lyric-font", attributes);
    }

    private void buildLyricLanguage(LyricLanguage lyricLanguage) {
        if (lyricLanguage == null) return;

        Map<String, String> attributes = new HashMap<>();
        attributes.put("number", lyricLanguage.getNumber());
        attributes.put("name", lyricLanguage.getName());
        attributes.put("xml:lang", BuilderUtil.requiredValue(lyricLanguage.getLang()));
        buildElementWithAttributes("lyric-language", attributes);
    }

    private void buildCredit(Credit credit) {
        if (credit == null) return;

        if (credit.getCreditDisplays().isEmpty()) return;

        buildOpenElement("credit");
        buildAttribute("id", credit.getElementId());
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
                buildImage("credit-image", creditImage.getImage());
            } else if (creditDisplay instanceof CreditWords) {
                CreditWords creditWords = (CreditWords)creditDisplay;
                buildFormattedDisplayElement("credit-words", creditWords.getElementId(), creditWords.getDisplay(), creditWords.getTextFormat());
            } else if (creditDisplay instanceof CreditSymbol) {
                CreditSymbol creditSymbol = (CreditSymbol)creditDisplay;
                buildFormattedDisplayElement("credit-symbol", creditSymbol.getElementId(), creditSymbol.getDisplay(), creditSymbol.getTextFormat());
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
        buildAttribute("type", BuilderUtil.enumValue(partGroup.getType()));
        buildAttribute("number", partGroup.getNumber());
        buildCloseElement();
        buildGroupName("group-name", partGroup.getGroupName());
        buildNameDisplay("group-name-display", partGroup.getGroupNameDisplay());
        buildGroupName("group-abbreviation", partGroup.getGroupAbbreviation());
        buildNameDisplay("group-abbreviation-display", partGroup.getGroupAbbreviationDisplay());
        GroupSymbol groupSymbol = partGroup.getGroupSymbol();
        if (groupSymbol != null) buildElementWithValueAndAttributes("group-symbol", groupSymbol.getGroupSymbolType(), DisplayBuilder.buildDisplay(groupSymbol.getDisplay()));
        GroupBarline groupBarline = partGroup.getGroupBarline();
        if (groupBarline != null) {
            String groupBarlineValue = BuilderUtil.enumValue(groupBarline.getGroupBarlineType());
            groupBarlineValue = groupBarlineValue.replace("mensurstrich", "Mensurstrich");
            buildElementWithValueAndAttribute("group-barline", groupBarlineValue, "color", groupBarline.getDisplay() == null ? null : groupBarline.getDisplay().getColor());
        }
        if (partGroup.getGroupTime()) buildElement("group-time");
        buildEditorial(partGroup.getEditorial());
        buildEndElement("part-group");
    }

    private void buildGroupName(String elementName, GroupName groupName) {
        if (groupName == null) return;

        Map<String, String> attributes = new HashMap<>(DisplayBuilder.buildDisplay(groupName.getDisplay()));
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
            buildElementWithOptionalValue("instrument-name", scoreInstrument.getInstrumentName());
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
            VirtualInstrument virtualInstrument = scoreInstrument.getVirtualInstrument();
            if (virtualInstrument != null) {
                buildStartElement("virtual-instrument");
                buildElementWithValue("virtual-library", virtualInstrument.getVirtualLibrary());
                buildElementWithValue("virtual-name", virtualInstrument.getVirtualName());
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
        attributes.put("print-object", BuilderUtil.yesOrNo(partName.getPrintObject()));
        attributes.putAll(DisplayBuilder.buildDisplay(partName.getDisplay()));
        attributes.put("justify", BuilderUtil.enumValue(partName.getJustify()));
        buildElementWithValueAndAttributes(elementName, partName.getPartName(), attributes);
    }

    private void buildIdentificationType(IdentificationType identificationType) {
        buildElementWithValueAndAttribute(identificationType.getIdName(), identificationType.getIdValue(), "type", identificationType.getIdType());
    }
}
