package org.curtis.musicxml.handler;

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
import org.curtis.musicxml.factory.DirectionFactory;
import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.factory.NoteFactory;
import org.curtis.musicxml.factory.PlacementFactory;
import org.curtis.musicxml.factory.TechnicalFactory;
import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.musicxml.handler.util.TypeUtil;
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
                    rootStep.setPrintStyle(FormattingFactory.newPrintStyle(harmonySubelement));
                    root.setRootStep(rootStep);
                    Element rootAlterElement = XmlUtil.getChildElement(harmonySubelement, "root-alter");
                    if(rootAlterElement != null) {
                        RootAlter rootAlter = new RootAlter();
                        rootAlter.setSemitones(MathUtil.newBigDecimal(XmlUtil.getElementText(rootAlterElement)));
                        rootAlter.setPrintObject(FormattingFactory.getPrintObject(rootAlterElement));
                        rootAlter.setPrintStyle(FormattingFactory.newPrintStyle(rootAlterElement));
                        rootAlter.setLocation(PlacementUtil.getLocation(rootAlterElement.getAttribute("location")));
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
                    String kindValue = XmlUtil.getElementText(harmonySubelement);
                    switch (kindValue) {
                        case "major":
                            kind.setKindValue(KindValue.MAJOR);
                            break;
                        case "minor":
                            kind.setKindValue(KindValue.MINOR);
                            break;
                        case "augmented":
                            kind.setKindValue(KindValue.AUGMENTED);
                            break;
                        case "diminished":
                            kind.setKindValue(KindValue.DIMINISHED);
                            break;
                        case "dominant":
                            kind.setKindValue(KindValue.DOMINANT);
                            break;
                        case "major-seventh":
                            kind.setKindValue(KindValue.MAJOR_SEVENTH);
                            break;
                        case "minor-seventh":
                            kind.setKindValue(KindValue.MINOR_SEVENTH);
                            break;
                        case "diminished-seventh":
                            kind.setKindValue(KindValue.DIMINISHED_SEVENTH);
                            break;
                        case "augmented-seventh":
                            kind.setKindValue(KindValue.AUGUMENTED_SEVENTH);
                            break;
                        case "half-diminished":
                            kind.setKindValue(KindValue.HALF_DIMINISHED);
                            break;
                        case "major-minor":
                            kind.setKindValue(KindValue.MAJOR_MINOR);
                            break;
                        case "major-sixth":
                            kind.setKindValue(KindValue.MAJOR_SIXTH);
                            break;
                        case "minor-sixth":
                            kind.setKindValue(KindValue.MINOR_SIXTH);
                            break;
                        case "dominant-ninth":
                            kind.setKindValue(KindValue.DOMINANT_NINTH);
                            break;
                        case "major-ninth":
                            kind.setKindValue(KindValue.MAJOR_NINTH);
                            break;
                        case "minor-ninth":
                            kind.setKindValue(KindValue.MINOR_NINTH);
                            break;
                        case "dominant-11th":
                            kind.setKindValue(KindValue.DOMINANT_11TH);
                            break;
                        case "major-11th":
                            kind.setKindValue(KindValue.MAJOR_11TH);
                            break;
                        case "minor-11th":
                            kind.setKindValue(KindValue.MINOR_11TH);
                            break;
                        case "dominant-13th":
                            kind.setKindValue(KindValue.DOMINANT_13TH);
                            break;
                        case "major-13th":
                            kind.setKindValue(KindValue.MAJOR_13TH);
                            break;
                        case "minor-13th":
                            kind.setKindValue(KindValue.MINOR_13TH);
                            break;
                        case "suspended-second":
                            kind.setKindValue(KindValue.SUSPENDED_SECOND);
                            break;
                        case "suspended-fourth":
                            kind.setKindValue(KindValue.SUSPENDED_FOURTH);
                            break;
                        case "neopolitan":
                            kind.setKindValue(KindValue.NEAPOLITAN);
                            break;
                        case "italian":
                            kind.setKindValue(KindValue.ITALIAN);
                            break;
                        case "french":
                            kind.setKindValue(KindValue.FRENCH);
                            break;
                        case "german":
                            kind.setKindValue(KindValue.GERMAN);
                            break;
                        case "pedal":
                            kind.setKindValue(KindValue.PEDAL);
                            break;
                        case "power":
                            kind.setKindValue(KindValue.POWER);
                            break;
                        case "tristan":
                            kind.setKindValue(KindValue.TRISTAN);
                            break;
                        case "other":
                            kind.setKindValue(KindValue.OTHER);
                            break;
                        case "none":
                            kind.setKindValue(KindValue.NONE);
                            break;
                    }
                    kind.setUseSymbols(TypeUtil.getYesNo(harmonySubelement.getAttribute("use-symbols")));
                    kind.setText(harmonySubelement.getAttribute("text"));
                    kind.setStackDegrees(TypeUtil.getYesNo(harmonySubelement.getAttribute("stack-degrees")));
                    kind.setParenthesesDegrees(TypeUtil.getYesNo(harmonySubelement.getAttribute("parentheses-degrees")));
                    kind.setBracketDegrees(TypeUtil.getYesNo(harmonySubelement.getAttribute("bracket-degrees")));
                    kind.setPrintStyle(FormattingFactory.newPrintStyle(harmonySubelement));
                    kind.setHalign(PlacementUtil.getLocation(harmonySubelement.getAttribute("halign")));
                    kind.setValign(PlacementUtil.getLocation(harmonySubelement.getAttribute("valign")));
                    if (harmonyChord != null) harmonyChord.setKind(kind);
                    break;
                case "inversion":
                    Inversion inversion = new Inversion();
                    inversion.setValue(StringUtil.getInteger(XmlUtil.getElementText(harmonySubelement)));
                    inversion.setPrintStyle(FormattingFactory.newPrintStyle(harmonySubelement));
                    if(harmonyChord != null) harmonyChord.setInversion(inversion);
                    break;
                case "bass":
                    Bass bass = new Bass();
                    Element bassStepElement = XmlUtil.getChildElement(harmonySubelement, "bass-step");
                    BassStep bassStep = new BassStep();
                    bassStep.setText(XmlUtil.getElementText(bassStepElement));
                    bassStep.setText(bassStepElement.getAttribute("text"));
                    bassStep.setPrintStyle(FormattingFactory.newPrintStyle(bassStepElement));
                    bass.setBassStep(bassStep);
                    Element bassAlterElement = XmlUtil.getChildElement(harmonySubelement, "bass-alter");
                    if (bassAlterElement != null) {
                        BassAlter bassAlter = new BassAlter();
                        bassAlter.setSemitones(MathUtil.newBigDecimal(XmlUtil.getElementText(bassAlterElement)));
                        bassAlter.setPrintObject(FormattingFactory.getPrintObject(bassAlterElement));
                        bassAlter.setPrintStyle(FormattingFactory.newPrintStyle(bassAlterElement));
                        bassAlter.setLocation(PlacementUtil.getLocation(bassAlterElement.getAttribute("location")));
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
                        String degreeSymbolValue = degreeValueElement.getAttribute("symbol");
                        switch (degreeSymbolValue) {
                            case "major":
                                degreeValue.setSymbol(DegreeSymbol.MAJOR);
                                break;
                            case "minor":
                                degreeValue.setSymbol(DegreeSymbol.MINOR);
                                break;
                            case "augmented":
                                degreeValue.setSymbol(DegreeSymbol.AUGMENTED);
                                break;
                            case "dimished":
                                degreeValue.setSymbol(DegreeSymbol.DIMINISHED);
                                break;
                            case "half-diminished":
                                degreeValue.setSymbol(DegreeSymbol.HALF_DIMINISHED);
                                break;
                        }
                        degreeValue.setText(degreeValueElement.getAttribute("text"));
                        degreeValue.setPrintStyle(FormattingFactory.newPrintStyle(degreeValueElement));
                        degree.setDegreeValue(degreeValue);
                        Element degreeAlterElement = XmlUtil.getChildElement(harmonySubelement, "degree-alter");
                        DegreeAlter degreeAlter = new DegreeAlter();
                        degreeAlter.setSemitones(MathUtil.newBigDecimal(XmlUtil.getElementText(degreeAlterElement)));
                        degreeAlter.setPrintStyle(FormattingFactory.newPrintStyle(degreeAlterElement));
                        degreeAlter.setPlusMinus(TypeUtil.getYesNo(degreeValueElement.getAttribute("plus-minus")));
                        degree.setDegreeAlter(degreeAlter);
                        Element degreeTypeElement = XmlUtil.getChildElement(harmonySubelement, "degree-type");
                        DegreeType degreeType = new DegreeType();
                        String degreeTypeValue = XmlUtil.getElementText(degreeTypeElement);
                        switch (degreeTypeValue) {
                            case "add":
                                degreeType.setValue(DegreeTypeValue.ADD);
                                break;
                            case "alter":
                                degreeType.setValue(DegreeTypeValue.ALTER);
                                break;
                            case "subtract":
                                degreeType.setValue(DegreeTypeValue.SUBTRACT);
                                break;
                        }
                        degreeType.setText(degreeTypeElement.getAttribute("text"));
                        degreeType.setPrintStyle(FormattingFactory.newPrintStyle(degreeTypeElement));
                        degree.setDegreeType(degreeType);
                        degree.setPrintObject(FormattingFactory.getPrintObject(degreeAlterElement));
                        degrees.add(degree);
                    }
                    break;
                case "frame":
                    Frame frame = new Frame();
                    frame.setFrameStrings(StringUtil.getInteger(XmlUtil.getChildElementText(harmonySubelement, "frame-strings")));
                    frame.setFrameFrets(StringUtil.getInteger(XmlUtil.getChildElementText(harmonySubelement, "frame-frets")));
                    frame.setPosition(PlacementFactory.newPosition(harmonySubelement));
                    frame.setColor(harmonySubelement.getAttribute("color"));
                    frame.setHalign(PlacementUtil.getLocation(harmonySubelement.getAttribute("halign")));
                    frame.setHeight(MathUtil.newBigDecimal(harmonySubelement.getAttribute("height")));
                    frame.setWidth(MathUtil.newBigDecimal(harmonySubelement.getAttribute("width")));
                    frame.setUnplayed(harmonySubelement.getAttribute("unplayed"));
                    Element firstFretElement = XmlUtil.getChildElement(harmonySubelement, "first-fret");
                    if (firstFretElement != null) {
                        FirstFret firstFret = new FirstFret();
                        firstFret.setValue(StringUtil.getInteger(XmlUtil.getElementText(firstFretElement)));
                        firstFret.setText(firstFretElement.getAttribute("text"));
                        firstFret.setLocation(PlacementUtil.getLocation(firstFretElement.getAttribute("location")));
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
                            barre.setType(PlacementUtil.getConnection(barreElement.getAttribute("type")));
                            barre.setColor(barreElement.getAttribute("color"));
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
            String harmonyType = element.getAttribute("type");
            if(StringUtil.isNotEmpty(harmonyType)) {
                switch (harmonyType) {
                    case "explicit":
                        harmony.setType(HarmonyType.EXPLICIT);
                        break;
                    case "implied":
                        harmony.setType(HarmonyType.IMPLIED);
                        break;
                    case "alternate":
                        harmony.setType(HarmonyType.ALTERNATE);
                        break;
                }
            }
            harmony.setPrintObject(FormattingFactory.getPrintObject(element));
            harmony.setPrintFrame(TypeUtil.getYesNo(element.getAttribute("print-frame")));
            harmony.setPrintStyle(FormattingFactory.newPrintStyle(element));
            harmony.setPlacement(PlacementUtil.getLocation(element.getAttribute("placement")));
        }

        if(harmonyChord != null) harmony.getHarmonyChords().add(harmonyChord);

        return harmony;
    }
}
