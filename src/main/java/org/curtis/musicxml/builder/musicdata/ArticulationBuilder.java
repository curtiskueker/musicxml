package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.DisplayBuilder;
import org.curtis.musicxml.builder.BuilderUtil;
import org.curtis.musicxml.note.notation.articulation.Accent;
import org.curtis.musicxml.note.notation.articulation.Articulation;
import org.curtis.musicxml.note.notation.articulation.BreathMark;
import org.curtis.musicxml.note.notation.articulation.Caesura;
import org.curtis.musicxml.note.notation.articulation.DetachedLegato;
import org.curtis.musicxml.note.notation.articulation.Doit;
import org.curtis.musicxml.note.notation.articulation.Falloff;
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
        buildElementWithAttributes("accent", DisplayBuilder.buildDisplay(accent.getDisplay()));
    }

    private void buildStrongAccent(StrongAccent strongAccent) {
        Map<String, String> attributes = new HashMap<>(DisplayBuilder.buildDisplay(strongAccent.getDisplay()));
        attributes.put("type", BuilderUtil.enumValue(strongAccent.getType()));
        buildElementWithAttributes("strong-accent", attributes);
    }

    private void buildStaccato(Staccato staccato) {
        buildElementWithAttributes("staccato", DisplayBuilder.buildDisplay(staccato.getDisplay()));
    }

    private void buildTenuto(Tenuto tenuto) {
        buildElementWithAttributes("tenuto", DisplayBuilder.buildDisplay(tenuto.getDisplay()));
    }

    private void buildDetachedLegato(DetachedLegato detachedLegato) {
        buildElementWithAttributes("detached-legato", DisplayBuilder.buildDisplay(detachedLegato.getDisplay()));
    }

    private void buildStaccatissimo(Staccatissimo staccatissimo) {
        buildElementWithAttributes("staccatissimo", DisplayBuilder.buildDisplay(staccatissimo.getDisplay()));
    }

    private void buildSpiccato(Spiccato spiccato) {
        buildElementWithAttributes("spiccato", DisplayBuilder.buildDisplay(spiccato.getDisplay()));
    }

    private void buildScoop(Scoop scoop) {
        buildLine("scoop", scoop.getLine());
    }

    private void buildPlop(Plop plop) {
        buildLine("plop", plop.getLine());
    }

    private void buildDoit(Doit doit) {
        buildLine("doit", doit.getLine());
    }

    private void buildFalloff(Falloff falloff) {
        buildLine("falloff", falloff.getLine());
    }

    private void buildBreathMark(BreathMark breathMark) {
        buildElementWithValueAndAttributes("breath-mark", breathMark.getBreathMarkValue(), DisplayBuilder.buildDisplay(breathMark.getDisplay()));
    }

    private void buildCaesura(Caesura caesura) {
        buildElementWithAttributes("caesura", DisplayBuilder.buildDisplay(caesura.getDisplay()));
    }

    private void buildStress(Stress stress) {
        buildElementWithAttributes("stress", DisplayBuilder.buildDisplay(stress.getDisplay()));
    }

    private void buildUnstress(Unstress unstress) {
        buildElementWithAttributes("unstress", DisplayBuilder.buildDisplay(unstress.getDisplay()));
    }

    private void buildSoftAccent(SoftAccent softAccent) {
        buildElementWithAttributes("soft-accent", DisplayBuilder.buildDisplay(softAccent.getDisplay()));
    }

    private void buildOtherArticulation(OtherArticulation otherArticulation) {
        buildOtherPlacementText("other-articulation", otherArticulation.getPlacementText(), otherArticulation.getSmufl());
    }
}
