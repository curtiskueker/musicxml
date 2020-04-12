package org.curtis.lilypond;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.musicxml.display.Display;
import org.curtis.musicxml.display.Font;
import org.curtis.musicxml.display.Halign;
import org.curtis.musicxml.display.TextFormat;
import org.curtis.musicxml.identity.Identification;
import org.curtis.musicxml.identity.IdentificationType;
import org.curtis.musicxml.layout.MarginType;
import org.curtis.musicxml.layout.PageLayout;
import org.curtis.musicxml.layout.PageMargins;
import org.curtis.musicxml.score.CreditDisplay;
import org.curtis.musicxml.score.CreditWords;
import org.curtis.musicxml.score.Defaults;
import org.curtis.musicxml.score.ScoreHeader;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreHeaderBuilder extends LilypondBuilder {
    private static final String LILYPOND_VERSION = "2.18.2";

    private ScoreHeader scoreHeader;

    private String poet;
    private String composer;
    private String arranger;
    private String copyright = "";

    public ScoreHeaderBuilder(ScoreHeader scoreHeader) {
        this.scoreHeader = scoreHeader;
    }

    public ScoreHeader getScoreHeader() {
        return scoreHeader;
    }

    public void setScoreHeader(ScoreHeader scoreHeader) {
        this.scoreHeader = scoreHeader;
    }

    public StringBuilder build() throws BuildException {
        append("\\version \"");
        append(LILYPOND_VERSION);
        appendLine("\"");
        appendLine();

        // assign values
        Identification identification = scoreHeader.getIdentification();
        if (identification != null) {
            for (IdentificationType creator : identification.getCreators()) {
                switch (creator.getIdType()) {
                    case "lyricist":
                        poet = creator.getIdValue();
                        break;
                    case "composer":
                        composer = creator.getIdValue();
                        break;
                    case "arranger":
                        arranger = creator.getIdValue();
                        break;
                }
            }
            for (IdentificationType rights : identification.getRightsList()) {
                if (StringUtil.isNotEmpty(copyright)) copyright += "\n";
                String rightsType = rights.getIdType();
                if (StringUtil.isNotEmpty(rightsType)) copyright += rightsType + ": ";
                copyright += rights.getIdValue();
            }
        }

        Defaults defaults = scoreHeader.getDefaults();
        if(defaults != null && defaults.hasScaling()) {
            appendStartSection("\\paper {");

            PageLayout pageLayout = defaults.getLayout().getPageLayout();
            BigDecimal pageHeight = pageLayout.getPageHeight();
            BigDecimal pageWidth = pageLayout.getPageWidth();
            if(MathUtil.isPositive(pageHeight)) {
                append("paper-height = ");
                appendLine(defaults.getMillimeters(pageHeight));
            }
            if(MathUtil.isPositive(pageWidth)) {
                append("paper-width = ");
                appendLine(defaults.getMillimeters(pageWidth));
            }

            Map<MarginType, PageMargins> pageMarginsMap = pageLayout.getPageMargins();
            PageMargins alternatePageMargins = pageMarginsMap.get(MarginType.ODD);
            PageMargins fixedPageMargins = pageMarginsMap.get(MarginType.BOTH);
            if(alternatePageMargins != null) {
                append("top-margin = ");
                appendLine(defaults.getMillimeters(alternatePageMargins.getTopMargin()));
                append("bottom-margin = ");
                appendLine(defaults.getMillimeters(alternatePageMargins.getBottomMargin()));
                append("inner-margin = ");
                appendLine(defaults.getMillimeters(alternatePageMargins.getLeftMargin()));
                append("outer-margin = ");
                appendLine(defaults.getMillimeters(alternatePageMargins.getRightMargin()));
            } else if(fixedPageMargins != null) {
                append("top-margin = ");
                appendLine(defaults.getMillimeters(fixedPageMargins.getTopMargin()));
                append("bottom-margin = ");
                appendLine(defaults.getMillimeters(fixedPageMargins.getBottomMargin()));
                append("left-margin = ");
                appendLine(defaults.getMillimeters(fixedPageMargins.getLeftMargin()));
                append("right-margin = ");
                appendLine(defaults.getMillimeters(fixedPageMargins.getRightMargin()));
            }

            appendEndSection("}");
        }

        appendStartSection("\\header {");

        appendKeyValue("title", scoreHeader.getMovementTitle());
        appendKeyValue("poet", poet);
        appendKeyValue("composer", composer);
        appendKeyValue("arranger", arranger);
        appendKeyValue("copyright", copyright);

        List<CreditDisplay> creditDisplays = new ArrayList<>();
        scoreHeader.getCredits().stream()
                .filter(credit -> credit.getPage() == null || credit.getPage().equals(1))
                .collect(Collectors.toList()).forEach(credit -> creditDisplays.addAll(credit.getCreditDisplays()));

        if(!creditDisplays.isEmpty()) {
            append("title = ");
            appendStartSection("\\markup {");
            appendStartSection("\\column {");

            for (CreditDisplay creditDisplay : creditDisplays) {
                if (creditDisplay instanceof CreditWords) {
                    CreditWords creditWords = (CreditWords)creditDisplay;
                    TextFormat creditWordsTextFormat = creditWords.getTextFormat();
                    if (creditWordsTextFormat == null) continue;
                    Halign justify = creditWordsTextFormat.getJustify();
                    if (justify != null) {
                        switch (justify) {
                            case LEFT:
                                append("\\left-align");
                                break;
                            case CENTER:
                                append("\\center-align");
                                break;
                            case RIGHT:
                                append("\\right-align");
                                break;
                        }
                    }

                    append(" { ");

                    Display creditWordsDisplay = creditWords.getDisplay();
                    if (creditWordsDisplay != null) {
                        Font font = creditWordsDisplay.getFont();
                        if (font != null) {
                            BigDecimal numericFontSize = font.getNumericFontSize();
                            if (numericFontSize != null) {
                                append("\\abs-fontsize #");
                                append(MathUtil.truncate(numericFontSize));
                                append(" ");
                            }
                        }
                    }

                    append("\"");
                    append(StringUtil.nullToString(creditWordsTextFormat.getValue()));
                    appendLine("\" }");
                }
            }

            appendEndSection("}");
            appendEndSection("}");
        }

        appendEndSection("}");

        return stringBuilder;
    }

    private void appendKeyValue(String key, String value) {
        if (StringUtil.isEmpty(value)) return;

        append(key);
        append(" = \"");
        append(value);
        append("\"");
        appendLine();
    }
}
