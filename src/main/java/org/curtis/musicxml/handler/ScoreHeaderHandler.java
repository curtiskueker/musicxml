package org.curtis.musicxml.handler;

import org.curtis.musicxml.display.TextFormat;
import org.curtis.musicxml.factory.DirectionFactory;
import org.curtis.musicxml.factory.DisplayFactory;
import org.curtis.musicxml.factory.FactoryUtil;
import org.curtis.musicxml.factory.IdentityFactory;
import org.curtis.musicxml.factory.LayoutFactory;
import org.curtis.musicxml.factory.LinkFactory;
import org.curtis.musicxml.identity.Identification;
import org.curtis.musicxml.layout.Distance;
import org.curtis.musicxml.layout.Glyph;
import org.curtis.musicxml.layout.Layout;
import org.curtis.musicxml.layout.LineWidth;
import org.curtis.musicxml.layout.NoteSize;
import org.curtis.musicxml.layout.NoteSizeType;
import org.curtis.musicxml.layout.OtherAppearance;
import org.curtis.musicxml.link.Bookmark;
import org.curtis.musicxml.link.Link;
import org.curtis.musicxml.score.Credit;
import org.curtis.musicxml.score.CreditBookmark;
import org.curtis.musicxml.score.CreditImage;
import org.curtis.musicxml.score.CreditLink;
import org.curtis.musicxml.score.CreditSymbol;
import org.curtis.musicxml.score.CreditType;
import org.curtis.musicxml.score.CreditWords;
import org.curtis.musicxml.score.Defaults;
import org.curtis.musicxml.score.LyricFont;
import org.curtis.musicxml.score.LyricLanguage;
import org.curtis.musicxml.score.PartListItem;
import org.curtis.musicxml.score.ScoreHeader;
import org.curtis.musicxml.score.Work;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class ScoreHeaderHandler implements ScoreElementHandler {
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
                                defaults.setScalingMillimeters(MathUtil.newBigDecimal(XmlUtil.getChildElementText(defaultsSubelement, "millimeters")));
                                defaults.setScalingTenths(MathUtil.newBigDecimal(XmlUtil.getChildElementText(defaultsSubelement, "tenths")));
                                break;
                            case "appearance":
                                List<Element> appearanceSubelements = XmlUtil.getChildElements(defaultsSubelement);
                                for(Element appearanceSubelement : appearanceSubelements) {
                                    String appearanceSubelementName = appearanceSubelement.getTagName();
                                    switch (appearanceSubelementName) {
                                        case "line-width":
                                            List<LineWidth> lineWidths = defaults.getLineWidths();
                                            LineWidth lineWidth = new LineWidth();
                                            lineWidth.setValue(MathUtil.newBigDecimal(XmlUtil.getElementText(appearanceSubelement)));
                                            lineWidth.setType(appearanceSubelement.getAttribute("type"));
                                            lineWidths.add(lineWidth);
                                            break;
                                        case "note-size":
                                            List<NoteSize> noteSizes = defaults.getNoteSizes();
                                            NoteSize noteSize = new NoteSize();
                                            noteSize.setValue(MathUtil.newBigDecimal(XmlUtil.getElementText(appearanceSubelement)));
                                            String noteSizeType = appearanceSubelement.getAttribute("type");
                                            noteSize.setType(FactoryUtil.enumValue(NoteSizeType.class, noteSizeType));
                                            noteSizes.add(noteSize);
                                            break;
                                        case "distance":
                                            List<Distance> distances = defaults.getDistances();
                                            Distance distance = new Distance();
                                            distance.setValue(MathUtil.newBigDecimal(XmlUtil.getElementText(appearanceSubelement)));
                                            distance.setType(appearanceSubelement.getAttribute("type"));
                                            distances.add(distance);
                                            break;
                                        case "glyph":
                                            List<Glyph> glyphs = defaults.getGlyphs();
                                            Glyph glyph = new Glyph();
                                            glyph.setValue(XmlUtil.getElementText(appearanceSubelement));
                                            glyph.setType(appearanceSubelement.getAttribute("type"));
                                            glyphs.add(glyph);
                                            break;
                                        case "other-appearance":
                                            List<OtherAppearance> otherAppearances = defaults.getOtherAppearances();
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
                    credit.setElementId(subelement.getAttribute("id"));
                    credit.setPage(StringUtil.getInteger(subelement.getAttribute("page")));
                    List<Element> creditSubelements = XmlUtil.getChildElements(subelement);
                    for(Element creditSubelement : creditSubelements) {
                        String creditSubelementName = creditSubelement.getTagName();
                        switch (creditSubelementName) {
                            case "credit-type":
                                CreditType creditType = new CreditType();
                                creditType.setValue(XmlUtil.getElementText(creditSubelement));
                                credit.getCreditTypes().add(creditType);
                                break;
                            case "link":
                                CreditLink creditLink = new CreditLink();
                                Link link = LinkFactory.newLink(creditSubelement);
                                creditLink.setLink(link);
                                credit.getCreditDisplays().add(creditLink);
                                break;
                            case "bookmark":
                                CreditBookmark creditBookmark = new CreditBookmark();
                                Bookmark bookmark = LinkFactory.newBookmark(creditSubelement);
                                creditBookmark.setBookmark(bookmark);
                                credit.getCreditDisplays().add(creditBookmark);
                                break;
                            case "credit-image":
                                CreditImage creditImage = new CreditImage();
                                creditImage.setElementId(creditSubelement.getAttribute("id"));
                                creditImage.setImage(DirectionFactory.newImage(creditSubelement));
                                DisplayFactory.setFormattedDisplay(creditImage, creditSubelement);
                                credit.getCreditDisplays().add(creditImage);
                                break;
                            case "credit-words":
                                CreditWords creditWords = new CreditWords();
                                creditWords.setElementId(creditSubelement.getAttribute("id"));
                                DisplayFactory.setFormattedDisplay(creditWords, creditSubelement);
                                TextFormat creditTextFormat = creditWords.getTextFormat();
                                if (creditTextFormat == null) break;
                                creditWords.setTextFormat(creditTextFormat);
                                credit.getCreditDisplays().add(creditWords);
                                break;
                            case "credit-symbol":
                                CreditSymbol creditSymbol = new CreditSymbol();
                                creditSymbol.setElementId(creditSubelement.getAttribute("id"));
                                DisplayFactory.setFormattedDisplay(creditSymbol, creditSubelement);
                                credit.getCreditDisplays().add(creditSymbol);
                                break;
                        }
                    }
                    if (!credit.getCreditDisplays().isEmpty()) credits.add(credit);
                    break;
                case "part-list":
                    List<PartListItem> partListItems = scoreHeader.getPartListItems();
                    PartListHandler partListHandler = new PartListHandler(partListItems);
                    partListHandler.handle(subelement);
                    break;
            }
        }
    }
}
