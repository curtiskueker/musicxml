package org.curtis.lilypond;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.musicxml.bin.MusicXml2Ly;
import org.curtis.lilypond.util.ScoreDefaults;
import org.curtis.musicxml.common.FormattedText;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.TextFormatting;
import org.curtis.musicxml.identity.Identification;
import org.curtis.musicxml.identity.TypedText;
import org.curtis.musicxml.layout.MarginType;
import org.curtis.musicxml.layout.PageLayout;
import org.curtis.musicxml.layout.PageMargins;
import org.curtis.musicxml.score.Credit;
import org.curtis.musicxml.score.ScoreHeader;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScoreHeaderBuilder extends AbstractBuilder {
    private ScoreHeader scoreHeader;

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
        append(MusicXml2Ly.LILYPOND_VERSION);
        appendLine("\"");
        appendLine("");

        ScoreDefaults.getInstance().setScoreDefaults(scoreHeader.getDefaults());

        if(ScoreDefaults.getInstance().hasScaling()) {
            appendLine("\\paper {");

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

            appendLine("}");
        }

        appendLine("\\header {");

        if(StringUtil.isNotEmpty(scoreHeader.getMovementTitle())) {
            append("title = \"");
            append(scoreHeader.getMovementTitle());
            appendLine("\"");
        }

        Identification identification = scoreHeader.getIdentification();
        if (identification != null) {
            for (TypedText typedText : identification.getCreators()) {
                if(typedText.getType().equals("composer")) {
                    append("composer = \"");
                    append(typedText.getValue());
                    append("\"");
                }
            }
        }

        List<Credit> credits = scoreHeader.getCredits();
        List<FormattedText> creditWordsList = new ArrayList<>();
        for(Credit credit : credits) {
            if(credit.getPage() > 1) {
                continue;
            }

            creditWordsList.addAll(credit.getCreditWordsList());
        }

        if(!creditWordsList.isEmpty()) {
            appendLine("title =");
            appendLine("  \\markup {");
            appendLine("    \\column {");

            for(FormattedText creditWord : creditWordsList) {
                TextFormatting textFormatting = creditWord.getTextFormatting();
                Location justify = textFormatting.getJustify();
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

                BigDecimal fontSize = textFormatting.getPrintStyleAlign().getPrintStyle().getFont().getFontSize().getFontSize();
                if(fontSize != null) {
                    append("\\abs-fontsize #");
                    append(String.valueOf(fontSize.intValue()));
                    append(" ");
                }

                append("\"");
                append(StringUtil.nullToString(creditWord.getValue()));
                appendLine("\" }");
            }

            appendLine("            }");
            appendLine("           }");
        }

        appendLine("}");

        return stringBuilder;
    }
}
