package org.curtis.musicxml.builder;

import org.curtis.musicxml.bin.MusicXml2Ly;
import org.curtis.musicxml.common.FormattedText;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.TextFormatting;
import org.curtis.musicxml.identity.Identification;
import org.curtis.musicxml.identity.TypedText;
import org.curtis.musicxml.score.Credit;
import org.curtis.musicxml.score.ScoreHeader;
import org.curtis.util.StringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    public StringBuilder build() {
        append("\\version \"");
        append(MusicXml2Ly.LILYPOND_VERSION);
        appendLine("\"");
        appendLine("");

        appendLine("\\header {");

        if(StringUtil.isNotEmpty(scoreHeader.getMovementTitle())) {
            append("title = \"");
            append(scoreHeader.getMovementTitle());
            appendLine("\"");
        }

        Identification identification = scoreHeader.getIdentification();
        for (TypedText typedText : identification.getCreators()) {
            if(typedText.getType().equals("composer")) {
                append("composer = \"");
                append(typedText.getValue());
                append("\"");
            }
        }

        // title = \markup { \column { \italic "value" \bold "Another value" } }
        List<Credit> credits = scoreHeader.getCredits();
        List<FormattedText> creditWordsList = new ArrayList<>();
        for(Credit credit : credits) {
            creditWordsList.addAll(credit.getCreditWordsList());
        }

        if(!creditWordsList.isEmpty()) {
            appendLine("title =");
            appendLine("  \\markup {");
            appendLine("    \\column {");

            for(FormattedText creditWord : creditWordsList) {
                TextFormatting textFormatting = creditWord.getTextFormatting();
                Location justify = textFormatting.getJustify();
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
