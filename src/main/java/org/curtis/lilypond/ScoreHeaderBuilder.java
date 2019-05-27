package org.curtis.lilypond;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.lilypond.util.ScoreDefaults;
import org.curtis.musicxml.common.Font;
import org.curtis.musicxml.common.FontSize;
import org.curtis.musicxml.common.FormattedText;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.common.PrintStyleAlign;
import org.curtis.musicxml.common.TextFormatting;
import org.curtis.musicxml.identity.Identification;
import org.curtis.musicxml.identity.TypedText;
import org.curtis.musicxml.layout.MarginType;
import org.curtis.musicxml.layout.PageLayout;
import org.curtis.musicxml.layout.PageMargins;
import org.curtis.musicxml.score.CreditDisplay;
import org.curtis.musicxml.score.CreditWords;
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
    private String copyright;

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
            for (TypedText creator : identification.getCreators()) {
                switch (creator.getType()) {
                    case "lyricist":
                        poet = creator.getValue();
                        break;
                    case "composer":
                        composer = creator.getValue();
                        break;
                    case "arranger":
                        arranger = creator.getValue();
                        break;
                }
            }
        }

        ScoreDefaults.getInstance().setScoreDefaults(scoreHeader.getDefaults());

        if(ScoreDefaults.getInstance().hasScaling()) {
            appendStartSection("\\paper {");

            PageLayout pageLayout = scoreHeader.getDefaults().getLayout().getPageLayout();
            BigDecimal pageHeight = pageLayout.getPageHeight();
            BigDecimal pageWidth = pageLayout.getPageWidth();
            if(MathUtil.isPositive(pageHeight)) {
                append("paper-height = ");
                appendLine(ScoreDefaults.getInstance().getMillimeters(pageHeight).toString());
            }
            if(MathUtil.isPositive(pageWidth)) {
                append("paper-width = ");
                appendLine(ScoreDefaults.getInstance().getMillimeters(pageWidth).toString());
            }

            Map<MarginType, PageMargins> pageMarginsMap = pageLayout.getPageMargins();
            PageMargins alternatePageMargins = pageMarginsMap.get(MarginType.ODD);
            PageMargins fixedPageMargins = pageMarginsMap.get(MarginType.BOTH);
            if(alternatePageMargins != null) {
                append("top-margin = ");
                appendLine(ScoreDefaults.getInstance().getMillimeters(alternatePageMargins.getMargins().getTopMargin()).toString());
                append("bottom-margin = ");
                appendLine(ScoreDefaults.getInstance().getMillimeters(alternatePageMargins.getMargins().getBottomMargin()).toString());
                append("inner-margin = ");
                appendLine(ScoreDefaults.getInstance().getMillimeters(alternatePageMargins.getMargins().getLeftMargin()).toString());
                append("outer-margin = ");
                appendLine(ScoreDefaults.getInstance().getMillimeters(alternatePageMargins.getMargins().getRightMargin()).toString());
            } else if(fixedPageMargins != null) {
                append("top-margin = ");
                appendLine(ScoreDefaults.getInstance().getMillimeters(fixedPageMargins.getMargins().getTopMargin()).toString());
                append("bottom-margin = ");
                appendLine(ScoreDefaults.getInstance().getMillimeters(fixedPageMargins.getMargins().getBottomMargin()).toString());
                append("left-margin = ");
                appendLine(ScoreDefaults.getInstance().getMillimeters(fixedPageMargins.getMargins().getLeftMargin()).toString());
                append("right-margin = ");
                appendLine(ScoreDefaults.getInstance().getMillimeters(fixedPageMargins.getMargins().getRightMargin()).toString());
            }

            appendEndSection("}");
        }

        appendStartSection("\\header {");

        appendKeyValue("title", scoreHeader.getMovementTitle());
        appendKeyValue("poet", poet);
        appendKeyValue("composer", composer);
        appendKeyValue("arranger", arranger);

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
                    FormattedText creditWord = creditWords.getCreditWords();
                    if (creditWord == null) continue;
                    TextFormatting textFormatting = creditWord.getTextFormatting();
                    Location justify = textFormatting == null ? null : textFormatting.getJustify();
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

                    PrintStyleAlign printStyleAlign = textFormatting == null ? null : textFormatting.getPrintStyleAlign();
                    if (printStyleAlign != null) {
                        PrintStyle printStyle = printStyleAlign.getPrintStyle();
                        if (printStyle != null) {
                            Font font = printStyle.getFont();
                            if (font != null) {
                                FontSize fontSize = font.getFontSize();
                                if (fontSize != null) {
                                    BigDecimal fontSizeValue = fontSize.getFontSize();
                                    if(fontSizeValue != null) {
                                        append("\\abs-fontsize #");
                                        append(MathUtil.truncate(fontSizeValue));
                                        append(" ");
                                    }
                                }
                            }
                        }
                    }

                    append("\"");
                    append(StringUtil.nullToString(creditWord.getValue()));
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
