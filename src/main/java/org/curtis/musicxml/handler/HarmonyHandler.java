package org.curtis.musicxml.handler;

import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.direction.harmony.Barre;
import org.curtis.musicxml.direction.harmony.Bass;
import org.curtis.musicxml.direction.harmony.BassAlter;
import org.curtis.musicxml.direction.harmony.BassStep;
import org.curtis.musicxml.direction.harmony.Degree;
import org.curtis.musicxml.direction.harmony.DegreeAlter;
import org.curtis.musicxml.direction.harmony.DegreeSymbol;
import org.curtis.musicxml.direction.harmony.DegreeType;
import org.curtis.musicxml.direction.harmony.DegreeTypeValue;
import org.curtis.musicxml.direction.harmony.DegreeValue;
import org.curtis.musicxml.direction.harmony.FirstFret;
import org.curtis.musicxml.direction.harmony.Frame;
import org.curtis.musicxml.direction.harmony.FrameNote;
import org.curtis.musicxml.direction.harmony.Function;
import org.curtis.musicxml.direction.harmony.Harmony;
import org.curtis.musicxml.direction.harmony.HarmonyChord;
import org.curtis.musicxml.direction.harmony.HarmonyType;
import org.curtis.musicxml.direction.harmony.Inversion;
import org.curtis.musicxml.direction.harmony.Kind;
import org.curtis.musicxml.direction.harmony.KindValue;
import org.curtis.musicxml.direction.harmony.Root;
import org.curtis.musicxml.direction.harmony.RootAlter;
import org.curtis.musicxml.direction.harmony.RootStep;
import org.curtis.musicxml.display.Halign;
import org.curtis.musicxml.factory.DirectionFactory;
import org.curtis.musicxml.factory.DisplayFactory;
import org.curtis.musicxml.factory.FactoryUtil;
import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.factory.NoteFactory;
import org.curtis.musicxml.factory.TechnicalFactory;
import org.curtis.musicxml.util.TypeUtil;
import org.curtis.musicxml.score.MusicData;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class HarmonyHandler extends MusicDataHandler {
    public HarmonyHandler() {

    }

    public MusicData handle(Element element) {
        Harmony harmony = new Harmony();

        harmony.setElementId(element.getAttribute("id"));
        harmony.setEditorial(FormattingFactory.newEditorial(element));

        List<Element> harmonySubelements = XmlUtil.getChildElements(element);
        HarmonyChord harmonyChord = null;
        for(Element harmonySubelement: harmonySubelements) {
            String harmonySubelementName = harmonySubelement.getTagName();
            switch (harmonySubelementName) {
                case "root":
                    if(harmonyChord != null) harmony.getHarmonyChords().add(harmonyChord);
                    Root root = new Root();
                    Element rootStepElement = XmlUtil.getChildElement(harmonySubelement, "root-step");
                    RootStep rootStep = new RootStep();
                    rootStep.setStep(NoteFactory.newStep(rootStepElement));
                    rootStep.setText(harmonySubelement.getAttribute("text"));
                    rootStep.setDisplay(DisplayFactory.newDisplay(harmonySubelement));
                    root.setRootStep(rootStep);
                    Element rootAlterElement = XmlUtil.getChildElement(harmonySubelement, "root-alter");
                    if(rootAlterElement != null) {
                        RootAlter rootAlter = new RootAlter();
                        rootAlter.setSemitones(MathUtil.newBigDecimal(XmlUtil.getElementText(rootAlterElement)));
                        rootAlter.setPrintObject(FormattingFactory.getPrintObject(rootAlterElement));
                        rootAlter.setDisplay(DisplayFactory.newDisplay(rootAlterElement));
                        rootAlter.setLocation(FactoryUtil.enumValue(Halign.class, rootAlterElement.getAttribute("location")));
                        root.setRootAlter(rootAlter);
                    }
                    harmonyChord = root;
                    break;
                case "function":
                    if(harmonyChord != null) harmony.getHarmonyChords().add(harmonyChord);
                    Function function = new Function();
                    function.setFunction(FormattingFactory.newStyleText(harmonySubelement));
                    harmonyChord = function;
                    break;
                case "kind":
                    Kind kind = new Kind();
                    kind.setKindValue(FactoryUtil.enumValue(KindValue.class, XmlUtil.getElementText(harmonySubelement)));
                    kind.setUseSymbols(TypeUtil.getYesNo(harmonySubelement.getAttribute("use-symbols")));
                    kind.setText(harmonySubelement.getAttribute("text"));
                    kind.setStackDegrees(TypeUtil.getYesNo(harmonySubelement.getAttribute("stack-degrees")));
                    kind.setParenthesesDegrees(TypeUtil.getYesNo(harmonySubelement.getAttribute("parentheses-degrees")));
                    kind.setBracketDegrees(TypeUtil.getYesNo(harmonySubelement.getAttribute("bracket-degrees")));
                    kind.setDisplay(DisplayFactory.newDisplay(harmonySubelement));
                    if (harmonyChord != null) harmonyChord.setKind(kind);
                    break;
                case "inversion":
                    Inversion inversion = new Inversion();
                    inversion.setValue(StringUtil.getInteger(XmlUtil.getElementText(harmonySubelement)));
                    inversion.setDisplay(DisplayFactory.newDisplay(harmonySubelement));
                    if(harmonyChord != null) harmonyChord.setInversion(inversion);
                    break;
                case "bass":
                    Bass bass = new Bass();
                    Element bassStepElement = XmlUtil.getChildElement(harmonySubelement, "bass-step");
                    BassStep bassStep = new BassStep();
                    bassStep.setText(XmlUtil.getElementText(bassStepElement));
                    bassStep.setText(bassStepElement.getAttribute("text"));
                    bassStep.setDisplay(DisplayFactory.newDisplay(bassStepElement));
                    bass.setBassStep(bassStep);
                    Element bassAlterElement = XmlUtil.getChildElement(harmonySubelement, "bass-alter");
                    if (bassAlterElement != null) {
                        BassAlter bassAlter = new BassAlter();
                        bassAlter.setSemitones(MathUtil.newBigDecimal(XmlUtil.getElementText(bassAlterElement)));
                        bassAlter.setPrintObject(FormattingFactory.getPrintObject(bassAlterElement));
                        bassAlter.setDisplay(DisplayFactory.newDisplay(bassAlterElement));
                        bassAlter.setLocation(FactoryUtil.enumValue(Halign.class, bassAlterElement.getAttribute("location")));
                        bass.setBassAlter(bassAlter);
                    }
                    if (harmonyChord != null) harmonyChord.setBass(bass);
                    break;
                case "degree":
                    if (harmonyChord != null) {
                        List<Degree> degrees = harmonyChord.getDegrees();
                        Degree degree = new Degree();
                        Element degreeValueElement = XmlUtil.getChildElement(harmonySubelement, "degree-value");
                        DegreeValue degreeValue = new DegreeValue();
                        degreeValue.setValue(StringUtil.getInteger(XmlUtil.getElementText(degreeValueElement)));
                        degreeValue.setSymbol(FactoryUtil.enumValue(DegreeSymbol.class, degreeValueElement.getAttribute("symbol")));
                        degreeValue.setText(degreeValueElement.getAttribute("text"));
                        degreeValue.setDisplay(DisplayFactory.newDisplay(degreeValueElement));
                        degree.setDegreeValue(degreeValue);
                        Element degreeAlterElement = XmlUtil.getChildElement(harmonySubelement, "degree-alter");
                        DegreeAlter degreeAlter = new DegreeAlter();
                        degreeAlter.setSemitones(MathUtil.newBigDecimal(XmlUtil.getElementText(degreeAlterElement)));
                        degreeAlter.setDisplay(DisplayFactory.newDisplay(degreeAlterElement));
                        degreeAlter.setPlusMinus(TypeUtil.getYesNo(degreeValueElement.getAttribute("plus-minus")));
                        degree.setDegreeAlter(degreeAlter);
                        Element degreeTypeElement = XmlUtil.getChildElement(harmonySubelement, "degree-type");
                        DegreeType degreeType = new DegreeType();
                        degreeType.setValue(FactoryUtil.enumValue(DegreeTypeValue.class, XmlUtil.getElementText(degreeTypeElement)));
                        degreeType.setText(degreeTypeElement.getAttribute("text"));
                        degreeType.setDisplay(DisplayFactory.newDisplay(degreeTypeElement));
                        degree.setDegreeType(degreeType);
                        degree.setPrintObject(FormattingFactory.getPrintObject(degreeAlterElement));
                        degrees.add(degree);
                    }
                    break;
                case "frame":
                    Frame frame = new Frame();
                    frame.setElementId(harmonySubelement.getAttribute("id"));
                    frame.setFrameStrings(StringUtil.getInteger(XmlUtil.getChildElementText(harmonySubelement, "frame-strings")));
                    frame.setFrameFrets(StringUtil.getInteger(XmlUtil.getChildElementText(harmonySubelement, "frame-frets")));
                    frame.setDisplay(DisplayFactory.newDisplay(harmonySubelement));
                    frame.setHeight(MathUtil.newBigDecimal(harmonySubelement.getAttribute("height")));
                    frame.setWidth(MathUtil.newBigDecimal(harmonySubelement.getAttribute("width")));
                    frame.setUnplayed(harmonySubelement.getAttribute("unplayed"));
                    Element firstFretElement = XmlUtil.getChildElement(harmonySubelement, "first-fret");
                    if (firstFretElement != null) {
                        FirstFret firstFret = new FirstFret();
                        firstFret.setValue(StringUtil.getInteger(XmlUtil.getElementText(firstFretElement)));
                        firstFret.setText(firstFretElement.getAttribute("text"));
                        firstFret.setLocation(FactoryUtil.enumValue(Halign.class, firstFretElement.getAttribute("location")));
                        frame.setFirstFret(firstFret);
                    }
                    List<Element> frameNoteElements = XmlUtil.getChildElements(harmonySubelement, "frame-note");
                    for(Element frameNoteElement : frameNoteElements) {
                        List<FrameNote> frameNotes = frame.getFrameNotes();
                        FrameNote frameNote = new FrameNote();
                        frameNote.setString(TechnicalFactory.newStringNumber(XmlUtil.getChildElement(frameNoteElement, "string")));
                        frameNote.setFret(TechnicalFactory.newFret(XmlUtil.getChildElement(frameNoteElement, "fret")));
                        frameNote.setFingering(TechnicalFactory.newFingering(XmlUtil.getChildElement(frameNoteElement, "fingering")));
                        Element barreElement = XmlUtil.getChildElement(frameNoteElement, "barre");
                        if (barreElement != null) {
                            Barre barre = new Barre();
                            barre.setType(FactoryUtil.enumValue(Connection.class, barreElement.getAttribute("type")));
                            barre.setDisplay(DisplayFactory.newDisplay(barreElement));
                            frameNote.setBarre(barre);
                        }
                        frameNotes.add(frameNote);
                    }
                    harmony.setFrame(frame);
                    break;
                case "offset":
                    harmony.setOffset(DirectionFactory.newOffset(harmonySubelement));
                    break;
                case "staff":
                    harmony.setStaff(StringUtil.getInteger(XmlUtil.getChildElementText(harmonySubelement, "staff")));
                    break;
            }
            harmony.setType(FactoryUtil.enumValue(HarmonyType.class, element.getAttribute("type")));
            harmony.setPrintObject(FormattingFactory.getPrintObject(element));
            harmony.setPrintFrame(TypeUtil.getYesNo(element.getAttribute("print-frame")));
            harmony.setDisplay(DisplayFactory.newDisplay(element));
        }

        if(harmonyChord != null) harmony.getHarmonyChords().add(harmonyChord);

        return harmony;
    }
}
