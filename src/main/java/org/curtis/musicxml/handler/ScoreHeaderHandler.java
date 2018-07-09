package org.curtis.musicxml.handler;

import org.curtis.musicxml.common.FormattedText;
import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.factory.IdentityFactory;
import org.curtis.musicxml.factory.LayoutFactory;
import org.curtis.musicxml.factory.LinkFactory;
import org.curtis.musicxml.identity.Identification;
import org.curtis.musicxml.layout.Appearance;
import org.curtis.musicxml.layout.Distance;
import org.curtis.musicxml.layout.Layout;
import org.curtis.musicxml.layout.LineWidth;
import org.curtis.musicxml.layout.NoteSize;
import org.curtis.musicxml.layout.NoteSizeType;
import org.curtis.musicxml.layout.OtherAppearance;
import org.curtis.musicxml.layout.Scaling;
import org.curtis.musicxml.link.Bookmark;
import org.curtis.musicxml.link.Link;
import org.curtis.musicxml.score.Credit;
import org.curtis.musicxml.score.CreditType;
import org.curtis.musicxml.score.Defaults;
import org.curtis.musicxml.score.LyricFont;
import org.curtis.musicxml.score.LyricLanguage;
import org.curtis.musicxml.score.PartList;
import org.curtis.musicxml.score.ScoreHeader;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class ScoreHeaderHandler extends AbstractHandler {
    private ScoreHeader scoreHeader;

    public ScoreHeaderHandler(ScoreHeader scoreHeader) {
        this.scoreHeader = scoreHeader;
    }

    public void handle(Element element) {
        scoreHeader.setMovementTitle(XmlUtil.getChildElementText(element, "movement-title"));

        List<Element> subelements = XmlUtil.getChildElements(element);
        for (Element subelement : subelements) {
            String subelementName = subelement.getTagName();
            switch (subelementName) {
                case "work":
                    scoreHeader.setWorkNumber(XmlUtil.getChildElementText(subelement, "work-number"));
                    scoreHeader.setWorkTitle(XmlUtil.getChildElementText(subelement, "work-title"));
                    scoreHeader.setOpus(LinkFactory.newLinkAttributes(XmlUtil.getChildElement(subelement, "opus")));
                    break;
                case "movement-number":
                    scoreHeader.setMovementNumber(XmlUtil.getChildElementText(subelement, "movement-number"));
                    break;
                case "movement-title":
                    scoreHeader.setMovementTitle(XmlUtil.getChildElementText(subelement, "movement-title"));
                    break;
                case "identification":
                    Identification identification = IdentityFactory.newIdentification(subelement);
                    scoreHeader.setIdentification(identification);
                    break;
                case "defaults":
                    Defaults defaults = new Defaults();

                    Layout layout = LayoutFactory.newLayout(subelement);
                    defaults.setLayout(layout);

                    List<Element> defaultsSubelements = XmlUtil.getChildElements(subelement);
                    for(Element defaultsSubelement : defaultsSubelements) {
                        String defaultsSubelementName = defaultsSubelement.getTagName();
                        switch (defaultsSubelementName) {
                            case "scaling":
                                Scaling scaling = new Scaling();
                                scaling.setMillimeters(MathUtil.newBigDecimal(XmlUtil.getChildElementText(defaultsSubelement, "millimeters")));
                                scaling.setTenths(MathUtil.newBigDecimal(XmlUtil.getChildElementText(defaultsSubelement, "tenths")));
                                defaults.setScaling(scaling);
                                break;
                            case "appearance":
                                Appearance appearance = new Appearance();
                                defaults.setAppearance(appearance);
                                List<Element> appearanceSubelements = XmlUtil.getChildElements(defaultsSubelement);
                                for(Element appearanceSubelement : appearanceSubelements) {
                                    String appearanceSubelementName = appearanceSubelement.getTagName();
                                    switch (appearanceSubelementName) {
                                        case "line-width":
                                            List<LineWidth> lineWidths = appearance.getLineWidths();
                                            LineWidth lineWidth = new LineWidth();
                                            lineWidth.setValue(MathUtil.newBigDecimal(XmlUtil.getElementText(appearanceSubelement)));
                                            lineWidth.setLineWidthType(appearanceSubelement.getAttribute("type"));
                                            lineWidths.add(lineWidth);
                                            break;
                                        case "note-size":
                                            List<NoteSize> noteSizes = appearance.getNoteSizes();
                                            NoteSize noteSize = new NoteSize();
                                            noteSize.setValue(MathUtil.newBigDecimal(XmlUtil.getElementText(appearanceSubelement)));
                                            String noteSizeType = appearanceSubelement.getAttribute("type");
                                            switch (noteSizeType) {
                                                case "cue":
                                                    noteSize.setType(NoteSizeType.CUE);
                                                    break;
                                                case "grace":
                                                    noteSize.setType(NoteSizeType.GRACE);
                                                    break;
                                                case "large":
                                                    noteSize.setType(NoteSizeType.LARGE);
                                                    break;
                                            }
                                            noteSizes.add(noteSize);
                                            break;
                                        case "distance":
                                            List<Distance> distances = appearance.getDistances();
                                            Distance distance = new Distance();
                                            distance.setValue(MathUtil.newBigDecimal(XmlUtil.getElementText(appearanceSubelement)));
                                            distance.setType(appearanceSubelement.getAttribute("type"));
                                            distances.add(distance);
                                            break;
                                        case "other-appearance":
                                            List<OtherAppearance> otherAppearances = appearance.getOtherAppearances();
                                            OtherAppearance otherAppearance = new OtherAppearance();
                                            otherAppearance.setValue(XmlUtil.getElementText(appearanceSubelement));
                                            otherAppearance.setType(appearanceSubelement.getAttribute("type"));
                                            otherAppearances.add(otherAppearance);
                                            break;
                                    }
                                }
                                break;
                            case "music-font":
                                defaults.setMusicFont(FormattingFactory.newFont(defaultsSubelement));
                                break;
                            case "word-font":
                                defaults.setWordFont(FormattingFactory.newFont(defaultsSubelement));
                                break;
                            case "lyric-font":
                                List<LyricFont> lyricFonts = defaults.getLyricFonts();
                                LyricFont lyricFont = new LyricFont();
                                lyricFont.setNumber(defaultsSubelement.getAttribute("number"));
                                lyricFont.setName(defaultsSubelement.getAttribute("name"));
                                lyricFont.setFont(FormattingFactory.newFont(defaultsSubelement));
                                lyricFonts.add(lyricFont);
                                break;
                            case "lyric-language":
                                List<LyricLanguage> lyricLanguages = defaults.getLyricLanguages();
                                LyricLanguage lyricLanguage = new LyricLanguage();
                                lyricLanguage.setNumber(defaultsSubelement.getAttribute("number"));
                                lyricLanguage.setName(defaultsSubelement.getAttribute("name"));
                                lyricLanguage.setLang(defaultsSubelement.getAttribute("lang"));
                                lyricLanguages.add(lyricLanguage);
                        }
                    }
                    scoreHeader.setDefaults(defaults);
                    break;
                case "credit":
                    List<Credit> credits = scoreHeader.getCredits();
                    Credit credit = new Credit();
                    credit.setPage(StringUtil.getInteger(subelement.getAttribute("page")));
                    List<Element> creditSubelements = XmlUtil.getChildElements(subelement);
                    boolean creditWordsAdded = false;
                    for(Element creditSubelement : creditSubelements) {
                        String creditSubelementName = creditSubelement.getTagName();
                        switch (creditSubelementName) {
                            case "credit-type":
                                CreditType creditType = new CreditType();
                                creditType.setType(XmlUtil.getElementText(creditSubelement));
                                credit.getCreditTypes().add(creditType);
                                break;
                            case "link":
                                Link link = LinkFactory.newLink(creditSubelement);
                                if (creditWordsAdded) credit.getLinks().add(link);
                                else credit.getCreditWordsLinks().add(link);
                                break;
                            case "bookmark":
                                Bookmark bookmark = LinkFactory.newBookmark(creditSubelement);
                                if (creditWordsAdded) credit.getBookmarks().add(bookmark);
                                else credit.getCreditWordsBookmarks().add(bookmark);
                                break;
                            case "credit-image":
                                break;
                            case "credit-words":
                                creditWordsAdded = true;
                                List<FormattedText> creditWordsList = credit.getCreditWordsList();
                                creditWordsList.add(FormattingFactory.newFormattedText(creditSubelement));
                                break;
                        }
                    }
                    credits.add(credit);
                    break;
                case "part-list":
                    PartList partList = scoreHeader.getPartList();
                    PartListHandler partListHandler = new PartListHandler(partList);
                    partListHandler.handle(subelement);
                    break;
            }
        }
    }
}
