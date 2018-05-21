package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.builder.util.BuilderUtil;
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
import org.curtis.musicxml.note.notation.articulation.Spiccato;
import org.curtis.musicxml.note.notation.articulation.Staccatissimo;
import org.curtis.musicxml.note.notation.articulation.Staccato;
import org.curtis.musicxml.note.notation.articulation.Stress;
import org.curtis.musicxml.note.notation.articulation.StrongAccent;
import org.curtis.musicxml.note.notation.articulation.Tenuto;
import org.curtis.musicxml.note.notation.articulation.Unstress;
import org.curtis.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

public class ArticulationBuilder extends BaseBuilder {
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
        else if (articulation instanceof OtherArticulation) buildOtherArticulation((OtherArticulation)articulation);

        return stringBuilder;
    }

    private void buildAccent(Accent accent) {
        buildPlacement("accent", accent.getPlacement());
    }

    private void buildStrongAccent(StrongAccent strongAccent) {
        buildPlacementWithAttribute("strong-accent", strongAccent.getPlacement(), "type",BuilderUtil.enumValue(strongAccent.getType()));
    }

    private void buildStaccato(Staccato staccato) {
        buildPlacement("staccato", staccato.getPlacement());
    }

    private void buildTenuto(Tenuto tenuto) {
        buildPlacement("tenuto", tenuto.getPlacement());
    }

    private void buildDetachedLegato(DetachedLegato detachedLegato) {
        buildPlacement("detached-legato", detachedLegato.getPlacement());
    }

    private void buildStaccatissimo(Staccatissimo staccatissimo) {
        buildPlacement("staccatissimo", staccatissimo.getPlacement());
    }

    private void buildSpiccato(Spiccato spiccato) {
        buildPlacement("spiccato", spiccato.getPlacement());
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
        String elementName = "breath-mark";
        String breathMarkValue = BuilderUtil.enumValue(breathMark.getBreathMarkValue());
        Map<String, String> attributes = new HashMap<>();
        attributes.put("placement", BuilderUtil.enumValue(breathMark.getPlacement()));
        if (StringUtil.isEmpty(breathMarkValue)) buildElementWithAttributes(elementName, attributes);
        else buildElementWithValueAndAttributes(elementName, breathMarkValue, attributes);
    }

    private void buildCaesura(Caesura caesura) {
        buildPlacement("caesura", caesura.getPlacement());
    }

    private void buildStress(Stress stress) {
        buildPlacement("stress", stress.getPlacement());
    }

    private void buildUnstress(Unstress unstress) {
        buildPlacement("unstress", unstress.getPlacement());
    }

    private void buildOtherArticulation(OtherArticulation otherArticulation) {
        buildPlacementText("other-articulation", otherArticulation.getPlacementText());
    }
}
