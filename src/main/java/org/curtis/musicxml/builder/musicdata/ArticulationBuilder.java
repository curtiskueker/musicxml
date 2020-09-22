package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.DisplayBuilder;
import org.curtis.musicxml.builder.BuilderUtil;
import org.curtis.musicxml.builder.FormattingBuilder;
import org.curtis.musicxml.note.Line;
import org.curtis.musicxml.note.notation.articulation.Accent;
import org.curtis.musicxml.note.notation.articulation.Articulation;
import org.curtis.musicxml.note.notation.articulation.BreathMark;
import org.curtis.musicxml.note.notation.articulation.Caesura;
import org.curtis.musicxml.note.notation.articulation.DetachedLegato;
import org.curtis.musicxml.note.notation.articulation.Doit;
import org.curtis.musicxml.note.notation.articulation.Falloff;
import org.curtis.musicxml.note.notation.articulation.LinedArticulation;
import org.curtis.musicxml.note.notation.articulation.OtherArticulation;
import org.curtis.musicxml.note.notation.articulation.Plop;
import org.curtis.musicxml.note.notation.articulation.Scoop;
import org.curtis.musicxml.note.notation.articulation.SoftAccent;
import org.curtis.musicxml.note.notation.articulation.Spiccato;
import org.curtis.musicxml.note.notation.articulation.Staccatissimo;
import org.curtis.musicxml.note.notation.articulation.Staccato;
import org.curtis.musicxml.note.notation.articulation.Stress;
import org.curtis.musicxml.note.notation.articulation.StrongAccent;
import org.curtis.musicxml.note.notation.articulation.Tenuto;
import org.curtis.musicxml.note.notation.articulation.Unstress;

import java.util.HashMap;
import java.util.Map;

public class ArticulationBuilder extends MusicDataBuilder {
    private Articulation articulation;

    public ArticulationBuilder(Articulation articulation) {
        this.articulation = articulation;
    }

    public StringBuilder build() {
        if (articulation == null) return stringBuilder;

        if (articulation instanceof Accent) buildAccent((Accent)articulation);
        else if (articulation instanceof StrongAccent) buildStrongAccent((StrongAccent)articulation);
        else if (articulation instanceof Staccato) buildStaccato((Staccato)articulation);
        else if (articulation instanceof Tenuto) buildTenuto((Tenuto)articulation);
        else if (articulation instanceof DetachedLegato) buildDetachedLegato((DetachedLegato)articulation);
        else if (articulation instanceof Staccatissimo) buildStaccatissimo((Staccatissimo)articulation);
        else if (articulation instanceof Spiccato) buildSpiccato((Spiccato)articulation);
        else if (articulation instanceof Scoop) buildScoop((Scoop)articulation);
        else if (articulation instanceof Plop) buildPlop((Plop)articulation);
        else if (articulation instanceof Doit) buildDoit((Doit)articulation);
        else if (articulation instanceof Falloff) buildFalloff((Falloff)articulation);
        else if (articulation instanceof BreathMark) buildBreathMark((BreathMark)articulation);
        else if (articulation instanceof Caesura) buildCaesura((Caesura)articulation);
        else if (articulation instanceof Stress) buildStress((Stress)articulation);
        else if (articulation instanceof Unstress) buildUnstress((Unstress)articulation);
        else if (articulation instanceof SoftAccent) buildSoftAccent((SoftAccent)articulation);
        else if (articulation instanceof OtherArticulation) buildOtherArticulation((OtherArticulation)articulation);

        return stringBuilder;
    }

    private void buildAccent(Accent accent) {
        buildElementWithOptionalAttributes("accent", DisplayBuilder.buildDisplay(accent.getDisplay()));
    }

    private void buildStrongAccent(StrongAccent strongAccent) {
        Map<String, String> attributes = new HashMap<>(DisplayBuilder.buildDisplay(strongAccent.getDisplay()));
        attributes.put("type", BuilderUtil.enumValue(strongAccent.getType()));
        buildElementWithOptionalAttributes("strong-accent", attributes);
    }

    private void buildStaccato(Staccato staccato) {
        buildElementWithOptionalAttributes("staccato", DisplayBuilder.buildDisplay(staccato.getDisplay()));
    }

    private void buildTenuto(Tenuto tenuto) {
        buildElementWithOptionalAttributes("tenuto", DisplayBuilder.buildDisplay(tenuto.getDisplay()));
    }

    private void buildDetachedLegato(DetachedLegato detachedLegato) {
        buildElementWithOptionalAttributes("detached-legato", DisplayBuilder.buildDisplay(detachedLegato.getDisplay()));
    }

    private void buildStaccatissimo(Staccatissimo staccatissimo) {
        buildElementWithOptionalAttributes("staccatissimo", DisplayBuilder.buildDisplay(staccatissimo.getDisplay()));
    }

    private void buildSpiccato(Spiccato spiccato) {
        buildElementWithOptionalAttributes("spiccato", DisplayBuilder.buildDisplay(spiccato.getDisplay()));
    }

    private void buildScoop(Scoop scoop) {
        buildLinedArticulation("scoop", scoop);
    }

    private void buildPlop(Plop plop) {
        buildLinedArticulation("plop", plop);
    }

    private void buildDoit(Doit doit) {
        buildLinedArticulation("doit", doit);
    }

    private void buildFalloff(Falloff falloff) {
        buildLinedArticulation("falloff", falloff);
    }

    private void buildBreathMark(BreathMark breathMark) {
        buildElementWithValueAndAttributes("breath-mark", breathMark.getValue(), DisplayBuilder.buildDisplay(breathMark.getDisplay()));
    }

    private void buildCaesura(Caesura caesura) {
        buildElementWithValueAndAttributes("caesura", caesura.getValue(), DisplayBuilder.buildDisplay(caesura.getDisplay()));
    }

    private void buildStress(Stress stress) {
        buildElementWithOptionalAttributes("stress", DisplayBuilder.buildDisplay(stress.getDisplay()));
    }

    private void buildUnstress(Unstress unstress) {
        buildElementWithOptionalAttributes("unstress", DisplayBuilder.buildDisplay(unstress.getDisplay()));
    }

    private void buildSoftAccent(SoftAccent softAccent) {
        buildElementWithOptionalAttributes("soft-accent", DisplayBuilder.buildDisplay(softAccent.getDisplay()));
    }

    private void buildOtherArticulation(OtherArticulation otherArticulation) {
        Map<String, String> attributes = new HashMap<>(DisplayBuilder.buildDisplay(otherArticulation.getDisplay()));
        attributes.put("smufl", otherArticulation.getSmufl());
        buildElementWithValueAndAttributes("other-articulation", otherArticulation.getValue(), attributes);
    }

    private void buildLinedArticulation(String elementName, LinedArticulation linedArticulation) {
        Map<String, String> attributes = new HashMap<>();
        Line line = linedArticulation.getLine();
        attributes.put("line-shape", BuilderUtil.enumValue(line.getLineShape()));
        attributes.put("line-type", BuilderUtil.enumValue(line.getLineType()));
        attributes.put("line-length", BuilderUtil.enumValue(line.getLineLength()));
        attributes.putAll(FormattingBuilder.buildDashedFormatting(line.getDashedFormatting()));
        attributes.putAll(DisplayBuilder.buildDisplay(linedArticulation.getDisplay()));
        buildElementWithOptionalAttributes(elementName, attributes);
    }
}
