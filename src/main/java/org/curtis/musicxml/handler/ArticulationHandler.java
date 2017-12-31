package org.curtis.musicxml.handler;

import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.factory.NotationFactory;
import org.curtis.musicxml.factory.PlacementFactory;
import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.musicxml.note.Placement;
import org.curtis.musicxml.note.notation.Notation;
import org.curtis.musicxml.note.notation.articulation.Accent;
import org.curtis.musicxml.note.notation.articulation.BreathMark;
import org.curtis.musicxml.note.notation.articulation.BreathMarkType;
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
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class ArticulationHandler extends AbstractHandler {
    private List<Notation> notationList;

    public ArticulationHandler(List<Notation> notationList) {
        this.notationList = notationList;
    }

    public void handle(Element element) {
        List<Element> articulationsSubelements = XmlUtil.getChildElements(element);
        for(Element articulationsSubelement : articulationsSubelements) {
            switch (articulationsSubelement.getTagName()) {
                case "accent":
                    Accent accent = new Accent();
                    Placement accentPlacement = PlacementFactory.newPlacement(articulationsSubelement);
                    accent.setPlacement(accentPlacement);
                    notationList.add(accent);
                    break;
                case "strong-accent":
                    StrongAccent strongAccent = new StrongAccent();
                    Placement strongAccentPlacement = PlacementFactory.newPlacement(articulationsSubelement);
                    strongAccent.setPlacement(strongAccentPlacement);
                    strongAccent.setType(PlacementUtil.getLocation(articulationsSubelement.getAttribute("type")));
                    notationList.add(strongAccent);
                    break;
                case "staccato":
                    Staccato staccato = new Staccato();
                    Placement staccatoPlacement = PlacementFactory.newPlacement(articulationsSubelement);
                    staccato.setPlacement(staccatoPlacement);
                    notationList.add(staccato);
                    break;
                case "tenuto":
                    Tenuto tenuto = new Tenuto();
                    Placement tenutoPlacement = PlacementFactory.newPlacement(articulationsSubelement);
                    tenuto.setPlacement(tenutoPlacement);
                    notationList.add(tenuto);
                    break;
                case "detached-legato":
                    DetachedLegato detachedLegato = new DetachedLegato();
                    Placement detachedLegatoPlacement = PlacementFactory.newPlacement(articulationsSubelement);
                    detachedLegato.setPlacement(detachedLegatoPlacement);
                    notationList.add(detachedLegato);
                    break;
                case "staccatissimo":
                    Staccatissimo staccatissimo = new Staccatissimo();
                    Placement staccatissimoPlacement = PlacementFactory.newPlacement(articulationsSubelement);
                    staccatissimo.setPlacement(staccatissimoPlacement);
                    notationList.add(staccatissimo);
                    break;
                case "spiccato":
                    Spiccato spiccato = new Spiccato();
                    Placement spiccatoPlacement = PlacementFactory.newPlacement(articulationsSubelement);
                    spiccato.setPlacement(spiccatoPlacement);
                    notationList.add(spiccato);
                    break;
                case "scoop":
                    Scoop scoop = new Scoop();
                    scoop.setLine(NotationFactory.newLine(articulationsSubelement));
                    notationList.add(scoop);
                    break;
                case "plop":
                    Plop plop = new Plop();
                    plop.setLine(NotationFactory.newLine(articulationsSubelement));
                    notationList.add(plop);
                    break;
                case "doit":
                    Doit doit = new Doit();
                    doit.setLine(NotationFactory.newLine(articulationsSubelement));
                    notationList.add(doit);
                    break;
                case "falloff":
                    Falloff falloff = new Falloff();
                    falloff.setLine(NotationFactory.newLine(articulationsSubelement));
                    notationList.add(falloff);
                    break;
                case "breath-mark":
                    BreathMark breathMark = new BreathMark();
                    String breathMarkValue = XmlUtil.getElementText(articulationsSubelement);
                    if (StringUtil.isNotEmpty(breathMarkValue)) {
                        switch (breathMarkValue) {
                            case "comma":
                                breathMark.setBreathMarkValue(BreathMarkType.COMMA);
                                break;
                            case "tick":
                                breathMark.setBreathMarkValue(BreathMarkType.TICK);
                                break;
                        }
                    }
                    breathMark.setPrintStyle(FormattingFactory.newPrintStyle(articulationsSubelement));
                    breathMark.setPlacement(PlacementUtil.getLocation(articulationsSubelement.getAttribute("placement")));
                    notationList.add(breathMark);
                    break;
                case "caesura":
                    Caesura caesura = new Caesura();
                    Placement caesuraPlacement = PlacementFactory.newPlacement(articulationsSubelement);
                    caesura.setPlacement(caesuraPlacement);
                    notationList.add(caesura);
                    break;
                case "stress":
                    Stress stress = new Stress();
                    Placement stressPlacement = PlacementFactory.newPlacement(articulationsSubelement);
                    stress.setPlacement(stressPlacement);
                    notationList.add(stress);
                    break;
                case "unstress":
                    Unstress unstress = new Unstress();
                    Placement unstressPlacement = PlacementFactory.newPlacement(articulationsSubelement);
                    unstress.setPlacement(unstressPlacement);
                    notationList.add(unstress);
                    break;
                case "other-articulation":
                    OtherArticulation otherArticulation = new OtherArticulation();
                    otherArticulation.setValue(PlacementFactory.newPlacementText(articulationsSubelement));
                    notationList.add(otherArticulation);
                    break;
            }
        }

    }
}