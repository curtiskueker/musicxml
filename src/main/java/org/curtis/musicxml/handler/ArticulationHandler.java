package org.curtis.musicxml.handler;

import org.curtis.musicxml.display.SymbolDirection;
import org.curtis.musicxml.factory.DisplayFactory;
import org.curtis.musicxml.factory.FactoryUtil;
import org.curtis.musicxml.factory.NotationFactory;
import org.curtis.musicxml.note.notation.Articulations;
import org.curtis.musicxml.note.notation.Notation;
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

public class ArticulationHandler implements NotationHandler {
    public Notation handle(Element element) {
        Articulations articulations = new Articulations();
        List<Element> articulationsSubelements = XmlUtil.getChildElements(element);
        for(Element articulationsSubelement : articulationsSubelements) {
            Articulation articulation = null;
            switch (articulationsSubelement.getTagName()) {
                case "accent":
                    articulation = new Accent();
                    break;
                case "strong-accent":
                    StrongAccent strongAccent = new StrongAccent();
                    strongAccent.setType(FactoryUtil.enumValue(SymbolDirection.class, articulationsSubelement.getAttribute("type")));
                    articulation = strongAccent;
                    break;
                case "staccato":
                    articulation = new Staccato();
                    break;
                case "tenuto":
                    articulation = new Tenuto();
                    break;
                case "detached-legato":
                    articulation = new DetachedLegato();
                    break;
                case "staccatissimo":
                    articulation = new Staccatissimo();
                    break;
                case "spiccato":
                    articulation = new Spiccato();
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
                    breathMark.setValue(FactoryUtil.enumValue(BreathMarkType.class, breathMarkValue));
                    articulation = breathMark;
                    break;
                case "caesura":
                    Caesura caesura = new Caesura();
                    caesura.setValue(FactoryUtil.enumValue(CaesuraValue.class, XmlUtil.getElementText(articulationsSubelement)));
                    articulation = caesura;
                    break;
                case "stress":
                    articulation = new Stress();
                    break;
                case "unstress":
                    articulation = new Unstress();
                    break;
                case "soft-accent":
                    articulation = new SoftAccent();
                    break;
                case "other-articulation":
                    OtherArticulation otherArticulation = new OtherArticulation();
                    otherArticulation.setValue(XmlUtil.getElementText(articulationsSubelement));
                    otherArticulation.setSmufl(articulationsSubelement.getAttribute("smufl"));
                    articulation = otherArticulation;
                    break;
            }
            if (articulation != null) {
                articulation.setDisplay(DisplayFactory.newDisplay(articulationsSubelement));
                articulations.getArticulationList().add(articulation);
            }
        }

        return articulations;
    }
}
