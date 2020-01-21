package org.curtis.musicxml.handler;

import org.curtis.musicxml.factory.DirectionFactory;
import org.curtis.musicxml.factory.DisplayFactory;
import org.curtis.musicxml.factory.FactoryUtil;
import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.factory.IdentityFactory;
import org.curtis.musicxml.factory.LayoutFactory;
import org.curtis.musicxml.factory.LinkFactory;
import org.curtis.musicxml.identity.Identification;
import org.curtis.musicxml.layout.Appearance;
import org.curtis.musicxml.layout.Distance;
import org.curtis.musicxml.layout.Glyph;
import org.curtis.musicxml.layout.Layout;
import org.curtis.musicxml.layout.LineWidth;
import org.curtis.musicxml.layout.NoteSize;
import org.curtis.musicxml.layout.NoteSizeType;
import org.curtis.musicxml.layout.OtherAppearance;
import org.curtis.musicxml.layout.Scaling;
import org.curtis.musicxml.link.Bookmark;
import org.curtis.musicxml.link.Link;
import org.curtis.musicxml.score.Credit;
import org.curtis.musicxml.score.CreditImage;
import org.curtis.musicxml.score.CreditSymbol;
import org.curtis.musicxml.score.CreditType;
import org.curtis.musicxml.score.CreditWords;
import org.curtis.musicxml.score.Defaults;
import org.curtis.musicxml.score.LyricFont;
import org.curtis.musicxml.score.LyricLanguage;
import org.curtis.musicxml.score.PartList;
import org.curtis.musicxml.score.ScoreHeader;
import org.curtis.musicxml.score.Work;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

public class ScoreHeaderHandler extends BaseHandler {
    private ScoreHeader scoreHeader;

    public ScoreHeaderHandler(ScoreHeader scoreHeader) {
        this.scoreHeader = scoreHeader;
    }

    public void handle(Element element) {
        List<Element> subelements = XmlUtil.getChildElements(element);
        for (Element subelement : subelements) {
            String subelementName = subelement.getTagName();
            switch (subelementName) {
                case "work":
                    Work work = new Work();
                    work.setWorkNumber(XmlUtil.getChildElementText(subelement, "work-number"));
                    work.setWorkTitle(XmlUtil.getChildElementText(subelement, "work-title"));
                    work.setOpus(LinkFactory.newLinkAttributes(XmlUtil.getChildElement(subelement, "opus")));
                    scoreHeader.setWork(work);
                    break;
                case "movement-number":
                    scoreHeader.setMovementNumber(XmlUtil.getElementText(subelement));
                    break;
                case "movement-title":
                    scoreHeader.setMovementTitle(XmlUtil.getElementText(subelement));
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
                                            noteSize.setType(FactoryUtil.enumValue(NoteSizeType.class, noteSizeType));
                                            noteSizes.add(noteSize);
                                            break;
                                        case "distance":
                                            List<Distance> distances = appearance.getDistances();
                                            Distance distance = new Distance();
                                            distance.setValue(MathUtil.newBigDecimal(XmlUtil.getElementText(appearanceSubelement)));
                                            distance.setType(appearanceSubelement.getAttribute("type"));
                                            distances.add(distance);
                                            break;
                                        case "glyph":
                                            List<Glyph> glyphs = appearance.getGlyphs();
                                            Glyph glyph = new Glyph();
                                            glyph.setValue(XmlUtil.getElementText(appearanceSubelement));
                                            glyph.setType(appearanceSubelement.getAttribute("type"));
                                            glyphs.add(glyph);
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
                                defaults.setMusicFont(DisplayFactory.newFont(defaultsSubelement));
                                break;
                            case "word-font":
                                defaults.setWordFont(DisplayFactory.newFont(defaultsSubelement));
                                break;
                            case "lyric-font":
                                List<LyricFont> lyricFonts = defaults.getLyricFonts();
                                LyricFont lyricFont = new LyricFont();
                                lyricFont.setNumber(defaultsSubelement.getAttribute("number"));
                                lyricFont.setName(defaultsSubelement.getAttribute("name"));
                                lyricFont.setFont(DisplayFactory.newFont(defaultsSubelement));
                                lyricFonts.add(lyricFont);
                                break;
                            case "lyric-language":
                                List<LyricLanguage> lyricLanguages = defaults.getLyricLanguages();
                                LyricLanguage lyricLanguage = new LyricLanguage();
                                lyricLanguage.setNumber(defaultsSubelement.getAttribute("number"));
                                lyricLanguage.setName(defaultsSubelement.getAttribute("name"));
                                lyricLanguage.setLang(defaultsSubelement.getAttribute("xml:lang"));
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
                    List<Link> currentLinks = new ArrayList<>();
                    List<Bookmark> currentBookmarks = new ArrayList<>();
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
                                currentLinks.add(link);
                                break;
                            case "bookmark":
                                Bookmark bookmark = LinkFactory.newBookmark(creditSubelement);
                                currentBookmarks.add(bookmark);
                                break;
                            case "credit-image":
                                CreditImage creditImage = new CreditImage();
                                creditImage.setImage(DirectionFactory.newImage(creditSubelement));
                                for (Link imageLink : currentLinks) {
                                    imageLink.setCreditDisplay(creditImage);
                                    creditImage.getLinks().add(imageLink);
                                }
                                currentLinks.clear();
                                for (Bookmark imageBookmark : currentBookmarks) {
                                    imageBookmark.setCreditDisplay(creditImage);
                                    creditImage.getBookmarks().add(imageBookmark);
                                }
                                currentBookmarks.clear();
                                credit.getCreditDisplays().add(creditImage);
                                break;
                            case "credit-words":
                                CreditWords creditWords = new CreditWords();
                                creditWords.setCreditWords(FormattingFactory.newFormattedText(creditSubelement));
                                for (Link creditWordsLink : currentLinks) {
                                    creditWordsLink.setCreditDisplay(creditWords);
                                    creditWords.getLinks().add(creditWordsLink);
                                }
                                currentLinks.clear();
                                for (Bookmark creditWordsBookmark : currentBookmarks) {
                                    creditWordsBookmark.setCreditDisplay(creditWords);
                                    creditWords.getBookmarks().add(creditWordsBookmark);
                                }
                                currentBookmarks.clear();
                                if (creditWords.getCreditWords() != null) credit.getCreditDisplays().add(creditWords);
                                break;
                            case "credit-symbol":
                                CreditSymbol creditSymbol = new CreditSymbol();
                                creditSymbol.setSmufl(XmlUtil.getElementText(creditSubelement));
                                creditSymbol.setSymbolFormatting(FormattingFactory.newSymbolFormatting(creditSubelement));
                                for (Link creditWordsLink : currentLinks) {
                                    creditWordsLink.setCreditDisplay(creditSymbol);
                                    creditSymbol.getLinks().add(creditWordsLink);
                                }
                                currentLinks.clear();
                                for (Bookmark creditWordsBookmark : currentBookmarks) {
                                    creditWordsBookmark.setCreditDisplay(creditSymbol);
                                    creditSymbol.getBookmarks().add(creditWordsBookmark);
                                }
                                currentBookmarks.clear();
                                credit.getCreditDisplays().add(creditSymbol);
                                break;
                        }
                    }
                    if (!credit.getCreditDisplays().isEmpty()) credits.add(credit);
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
