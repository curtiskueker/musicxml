package org.curtis.musicxml.handler;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.factory.FactoryUtil;
import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.factory.NotationFactory;
import org.curtis.musicxml.factory.PlacementFactory;
import org.curtis.musicxml.note.PrintPlacement;
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
                    PrintPlacement accentPlacement = PlacementFactory.newPlacement(articulationsSubelement);
                    accent.setPrintPlacement(accentPlacement);
                    articulation = accent;
                    break;
                case "strong-accent":
                    StrongAccent strongAccent = new StrongAccent();
                    PrintPlacement strongAccentPlacement = PlacementFactory.newPlacement(articulationsSubelement);
                    strongAccent.setPrintPlacement(strongAccentPlacement);
                    strongAccent.setType((Location)FactoryUtil.enumValue(Location.class, articulationsSubelement.getAttribute("type")));
                    articulation = strongAccent;
                    break;
                case "staccato":
                    Staccato staccato = new Staccato();
                    PrintPlacement staccatoPlacement = PlacementFactory.newPlacement(articulationsSubelement);
                    staccato.setPrintPlacement(staccatoPlacement);
                    articulation = staccato;
                    break;
                case "tenuto":
                    Tenuto tenuto = new Tenuto();
                    PrintPlacement tenutoPlacement = PlacementFactory.newPlacement(articulationsSubelement);
                    tenuto.setPrintPlacement(tenutoPlacement);
                    articulation = tenuto;
                    break;
                case "detached-legato":
                    DetachedLegato detachedLegato = new DetachedLegato();
                    PrintPlacement detachedLegatoPlacement = PlacementFactory.newPlacement(articulationsSubelement);
                    detachedLegato.setPrintPlacement(detachedLegatoPlacement);
                    articulation = detachedLegato;
                    break;
                case "staccatissimo":
                    Staccatissimo staccatissimo = new Staccatissimo();
                    PrintPlacement staccatissimoPlacement = PlacementFactory.newPlacement(articulationsSubelement);
                    staccatissimo.setPrintPlacement(staccatissimoPlacement);
                    articulation = staccatissimo;
                    break;
                case "spiccato":
                    Spiccato spiccato = new Spiccato();
                    PrintPlacement spiccatoPlacement = PlacementFactory.newPlacement(articulationsSubelement);
                    spiccato.setPrintPlacement(spiccatoPlacement);
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
                    breathMark.setBreathMarkValue((BreathMarkType) FactoryUtil.enumValueWithEmptyValue(BreathMarkType.class, breathMarkValue));
                    breathMark.setPrintStyle(FormattingFactory.newPrintStyle(articulationsSubelement));
                    breathMark.setPlacement(PlacementFactory.newPlacementLocation(articulationsSubelement));
                    articulation = breathMark;
                    break;
                case "caesura":
                    Caesura caesura = new Caesura();
                    caesura.setCaesuraValue((CaesuraValue)FactoryUtil.enumValue(CaesuraValue.class, XmlUtil.getElementText(articulationsSubelement)));
                    caesura.setPrintPlacement(PlacementFactory.newPlacement(articulationsSubelement));
                    articulation = caesura;
                    break;
                case "stress":
                    Stress stress = new Stress();
                    stress.setPrintPlacement(PlacementFactory.newPlacement(articulationsSubelement));
                    articulation = stress;
                    break;
                case "unstress":
                    Unstress unstress = new Unstress();
                    unstress.setPrintPlacement(PlacementFactory.newPlacement(articulationsSubelement));
                    articulation = unstress;
                    break;
                case "soft-accent":
                    SoftAccent softAccent = new SoftAccent();
                    softAccent.setPrintPlacement(PlacementFactory.newPlacement(articulationsSubelement));
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
