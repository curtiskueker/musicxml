package org.curtis.musicxml.handler;

import org.curtis.musicxml.display.SymbolDirection;
import org.curtis.musicxml.factory.DisplayFactory;
import org.curtis.musicxml.factory.FactoryUtil;
import org.curtis.musicxml.factory.NotationFactory;
import org.curtis.musicxml.factory.PlacementFactory;
import org.curtis.musicxml.note.notation.Articulations;
import org.curtis.musicxml.note.notation.articulation.Accent;
import org.curtis.musicxml.note.notation.articulation.Articulation;
import org.curtis.musicxml.note.notation.articulation.BreathMark;
import org.curtis.musicxml.note.notation.articulation.BreathMarkType;
import org.curtis.musicxml.note.notation.articulation.Caesura;
import org.curtis.musicxml.note.notation.articulation.CaesuraValue;
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
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class ArticulationHandler extends BaseHandler {
    private Articulations articulations;

    public ArticulationHandler() {

    }

    public Articulations getArticulations() {
        return articulations;
    }

    public void setArticulations(Articulations articulations) {
        this.articulations = articulations;
    }

    public void handle(Element element) {
        articulations = new Articulations();
        List<Element> articulationsSubelements = XmlUtil.getChildElements(element);
        for(Element articulationsSubelement : articulationsSubelements) {
            Articulation articulation = null;
            switch (articulationsSubelement.getTagName()) {
                case "accent":
                    Accent accent = new Accent();
                    accent.setDisplay(DisplayFactory.newDisplay(articulationsSubelement));
                    articulation = accent;
                    break;
                case "strong-accent":
                    StrongAccent strongAccent = new StrongAccent();
                    strongAccent.setDisplay(DisplayFactory.newDisplay(articulationsSubelement));
                    strongAccent.setType(FactoryUtil.enumValue(SymbolDirection.class, articulationsSubelement.getAttribute("type")));
                    articulation = strongAccent;
                    break;
                case "staccato":
                    Staccato staccato = new Staccato();
                    staccato.setDisplay(DisplayFactory.newDisplay(articulationsSubelement));
                    articulation = staccato;
                    break;
                case "tenuto":
                    Tenuto tenuto = new Tenuto();
                    tenuto.setDisplay(DisplayFactory.newDisplay(articulationsSubelement));
                    articulation = tenuto;
                    break;
                case "detached-legato":
                    DetachedLegato detachedLegato = new DetachedLegato();
                    detachedLegato.setDisplay(DisplayFactory.newDisplay(articulationsSubelement));
                    articulation = detachedLegato;
                    break;
                case "staccatissimo":
                    Staccatissimo staccatissimo = new Staccatissimo();
                    staccatissimo.setDisplay(DisplayFactory.newDisplay(articulationsSubelement));
                    articulation = staccatissimo;
                    break;
                case "spiccato":
                    Spiccato spiccato = new Spiccato();
                    spiccato.setDisplay(DisplayFactory.newDisplay(articulationsSubelement));
                    articulation = spiccato;
                    break;
                case "scoop":
                    Scoop scoop = new Scoop();
                    scoop.setLine(NotationFactory.newLine(articulationsSubelement));
                    articulation = scoop;
                    break;
                case "plop":
                    Plop plop = new Plop();
                    plop.setLine(NotationFactory.newLine(articulationsSubelement));
                    articulation = plop;
                    break;
                case "doit":
                    Doit doit = new Doit();
                    doit.setLine(NotationFactory.newLine(articulationsSubelement));
                    articulation = doit;
                    break;
                case "falloff":
                    Falloff falloff = new Falloff();
                    falloff.setLine(NotationFactory.newLine(articulationsSubelement));
                    articulation = falloff;
                    break;
                case "breath-mark":
                    BreathMark breathMark = new BreathMark();
                    String breathMarkValue = XmlUtil.getElementText(articulationsSubelement);
                    breathMark.setBreathMarkValue(FactoryUtil.enumValue(BreathMarkType.class, breathMarkValue));
                    breathMark.setDisplay(DisplayFactory.newDisplay(articulationsSubelement));
                    articulation = breathMark;
                    break;
                case "caesura":
                    Caesura caesura = new Caesura();
                    caesura.setCaesuraValue(FactoryUtil.enumValue(CaesuraValue.class, XmlUtil.getElementText(articulationsSubelement)));
                    caesura.setDisplay(DisplayFactory.newDisplay(articulationsSubelement));
                    articulation = caesura;
                    break;
                case "stress":
                    Stress stress = new Stress();
                    stress.setDisplay(DisplayFactory.newDisplay(articulationsSubelement));
                    articulation = stress;
                    break;
                case "unstress":
                    Unstress unstress = new Unstress();
                    unstress.setDisplay(DisplayFactory.newDisplay(articulationsSubelement));
                    articulation = unstress;
                    break;
                case "soft-accent":
                    SoftAccent softAccent = new SoftAccent();
                    softAccent.setDisplay(DisplayFactory.newDisplay(articulationsSubelement));
                    articulation = softAccent;
                    break;
                case "other-articulation":
                    OtherArticulation otherArticulation = new OtherArticulation();
                    otherArticulation.setPlacementText(PlacementFactory.newPlacementText(articulationsSubelement));
                    otherArticulation.setSmufl(articulationsSubelement.getAttribute("smufl"));
                    articulation = otherArticulation;
                    break;
            }
            if (articulation != null) articulations.getArticulationList().add(articulation);
        }
    }
}
