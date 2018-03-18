package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.note.notation.ornament.DelayedInvertedTurn;
import org.curtis.musicxml.note.notation.ornament.DelayedTurn;
import org.curtis.musicxml.note.notation.ornament.InvertedMordent;
import org.curtis.musicxml.note.notation.ornament.InvertedTurn;
import org.curtis.musicxml.note.notation.ornament.Mordent;
import org.curtis.musicxml.note.notation.ornament.Ornament;
import org.curtis.musicxml.note.notation.ornament.Shake;
import org.curtis.musicxml.note.notation.ornament.Tremolo;
import org.curtis.musicxml.note.notation.ornament.TrillMark;
import org.curtis.musicxml.note.notation.ornament.Turn;
import org.curtis.musicxml.note.notation.ornament.VerticalTurn;

public class OrnamentBuilder extends BaseBuilder {
    private Ornament ornament;

    public OrnamentBuilder(Ornament ornament) {
        this.ornament = ornament;
    }

    public StringBuilder build() {
        if (ornament == null) return stringBuilder;

        if (ornament instanceof TrillMark) buildTrillMark((TrillMark)ornament);
        else if (ornament instanceof Turn) buildTurn((Turn)ornament);
        else if (ornament instanceof DelayedTurn) buildDelayedTurn((DelayedTurn)ornament);
        else if (ornament instanceof InvertedTurn) buildInvertedTurn((InvertedTurn)ornament);
        else if (ornament instanceof DelayedInvertedTurn) buildDelayedInvertedTurn((DelayedInvertedTurn)ornament);
        else if (ornament instanceof VerticalTurn) buildVerticalTurn((VerticalTurn)ornament);
        else if (ornament instanceof Shake) buildShake((Shake)ornament);
        else if (ornament instanceof Mordent) buildMordent((Mordent)ornament);
        else if (ornament instanceof InvertedMordent) buildInvertedMordent((InvertedMordent)ornament);
        else if (ornament instanceof Tremolo) buildTremolo((Tremolo)ornament);

        return stringBuilder;
    }

    private void buildTrillMark(TrillMark trillMark) {
        buildElement("trill-mark");
    }

    private void buildTurn(Turn turn) {
        buildElement("turn");
    }

    private void buildDelayedTurn(DelayedTurn delayedTurn) {
        buildElement("delayed-turn");
    }

    private void buildInvertedTurn(InvertedTurn invertedTurn) {
        buildElement("inverted-turn");
    }

    private void buildDelayedInvertedTurn(DelayedInvertedTurn delayedInvertedTurn) {
        buildElement("delayed-inverted-turn");
    }

    private void buildVerticalTurn(VerticalTurn verticalTurn) {
        buildElement("vertical-turn");
    }

    private void buildShake(Shake shake) {
        buildElement("shake");
    }

    private void buildMordent(Mordent mordent) {
        buildElement("mordent");
    }

    private void buildInvertedMordent(InvertedMordent invertedMordent) {
        buildElement("inverted-mordent");
    }

    private void buildTremolo(Tremolo tremolo) {
        buildElementWithValue("tremolo", tremolo.getTremoloMarks());
    }
}
