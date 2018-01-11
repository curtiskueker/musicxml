package org.curtis.musicxml.factory;

import org.curtis.musicxml.direction.Offset;
import org.curtis.musicxml.direction.directiontype.Accord;
import org.curtis.musicxml.direction.directiontype.AccordionRegistration;
import org.curtis.musicxml.direction.directiontype.Bracket;
import org.curtis.musicxml.direction.directiontype.Coda;
import org.curtis.musicxml.direction.directiontype.Damp;
import org.curtis.musicxml.direction.directiontype.DampAll;
import org.curtis.musicxml.direction.directiontype.Dashes;
import org.curtis.musicxml.direction.directiontype.DirectionType;
import org.curtis.musicxml.direction.directiontype.Eyeglasses;
import org.curtis.musicxml.direction.directiontype.HarpPedals;
import org.curtis.musicxml.direction.directiontype.LineEnd;
import org.curtis.musicxml.direction.directiontype.OctaveShift;
import org.curtis.musicxml.direction.directiontype.OctaveShiftType;
import org.curtis.musicxml.direction.directiontype.OtherDirection;
import org.curtis.musicxml.direction.directiontype.PedalTuning;
import org.curtis.musicxml.direction.directiontype.PrincipalVoice;
import org.curtis.musicxml.direction.directiontype.PrincipalVoiceSymbol;
import org.curtis.musicxml.direction.directiontype.Scordatura;
import org.curtis.musicxml.direction.directiontype.StringMute;
import org.curtis.musicxml.direction.directiontype.StringMuteDirection;
import org.curtis.musicxml.direction.directiontype.metronome.BeatMetronome;
import org.curtis.musicxml.direction.directiontype.metronome.BeatUnit;
import org.curtis.musicxml.direction.directiontype.metronome.Metronome;
import org.curtis.musicxml.direction.directiontype.Pedal;
import org.curtis.musicxml.direction.directiontype.Rehearsal;
import org.curtis.musicxml.direction.directiontype.Segno;
import org.curtis.musicxml.direction.directiontype.Wedge;
import org.curtis.musicxml.direction.directiontype.WedgeType;
import org.curtis.musicxml.direction.directiontype.Words;
import org.curtis.musicxml.direction.directiontype.metronome.MetronomeBeam;
import org.curtis.musicxml.direction.directiontype.metronome.MetronomeNote;
import org.curtis.musicxml.direction.directiontype.metronome.MetronomeTuplet;
import org.curtis.musicxml.direction.directiontype.metronome.NoteMetronome;
import org.curtis.musicxml.direction.directiontype.metronome.PerMinute;
import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.musicxml.handler.util.TypeUtil;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class DirectionFactory {
    private DirectionFactory() {

    }

    public static DirectionType newDirectionType(Element element) {
        if(element == null) return null;

        String directionTypeElementName = element.getTagName();
        switch (directionTypeElementName) {
            case "rehearsal":
                Rehearsal rehearsal = new Rehearsal();
                rehearsal.setFormattedText(FormattingFactory.newFormattedText(element));
                return rehearsal;
            case "segno":
                Segno segno = new Segno();
                segno.setPrintStyleAlign(FormattingFactory.newPrintStyleAlign(element));
                return segno;
            case "words":
                Words words = new Words();
                words.setFormattedText(FormattingFactory.newFormattedText(element));
                return words;
            case "coda":
                Coda coda = new Coda();
                coda.setPrintStyleAlign(FormattingFactory.newPrintStyleAlign(element));
                return coda;
            case "wedge":
                Wedge wedge = new Wedge();
                String wedgeType = element.getAttribute("type");
                switch (wedgeType) {
                    case "crescendo":
                        wedge.setType(WedgeType.CRESCENDO);
                        break;
                    case "diminuendo":
                        wedge.setType(WedgeType.DIMINUENDO);
                        break;
                    case "stop":
                        wedge.setType(WedgeType.STOP);
                        break;
                    case "continue":
                        wedge.setType(WedgeType.CONTINUE);
                        break;
                }
                wedge.setNumber(StringUtil.getInteger(element.getAttribute("number")));
                wedge.setSpread(MathUtil.newBigDecimal(element.getAttribute("spread")));
                wedge.setNiente(TypeUtil.getYesNo(element.getAttribute("niente")));
                wedge.setLineType(NotationFactory.newLineType(element));
                wedge.setDashedFormatting(FormattingFactory.newDashedFormatting(element));
                wedge.setPosition(PlacementFactory.newPosition(element));
                wedge.setColor(element.getAttribute("color"));
                return wedge;
            case "dynamics":
                return NotationFactory.newDynamics(element);
            case "dashes":
                Dashes dashes = new Dashes();
                dashes.setType(PlacementUtil.getConnection(element.getAttribute("type")));
                dashes.setNumber(StringUtil.getInteger(element.getAttribute("number")));
                dashes.setDashedFormatting(FormattingFactory.newDashedFormatting(element));
                dashes.setPosition(PlacementFactory.newPosition(element));
                dashes.setColor(element.getAttribute("color"));
                return dashes;
            case "bracket":
                Bracket bracket = new Bracket();
                bracket.setType(PlacementUtil.getConnection(element.getAttribute("type")));
                bracket.setNumber(StringUtil.getInteger(element.getAttribute("number")));
                String lineEnd = element.getAttribute("line-end");
                if(StringUtil.isNotEmpty(lineEnd)) {
                    switch (lineEnd) {
                        case "up":
                            bracket.setLineEnd(LineEnd.UP);
                        case "down":
                            bracket.setLineEnd(LineEnd.DOWN);
                        case "both":
                            bracket.setLineEnd(LineEnd.BOTH);
                        case "arrow":
                            bracket.setLineEnd(LineEnd.ARROW);
                        case "none":
                            bracket.setLineEnd(LineEnd.NONE);
                    }
                }
                bracket.setEndLength(MathUtil.newBigDecimal(element.getAttribute("end-length")));
                bracket.setLineType(NotationFactory.newLineType(element));
                bracket.setDashedFormatting(FormattingFactory.newDashedFormatting(element));
                bracket.setPosition(PlacementFactory.newPosition(element));
                bracket.setColor(element.getAttribute("color"));
                return bracket;
            case "pedal":
                Pedal pedal = new Pedal();
                pedal.setType(PlacementUtil.getConnection(element.getAttribute("type")));
                pedal.setLine(TypeUtil.getYesNo(element.getAttribute("line")));
                pedal.setSign(TypeUtil.getYesNo(element.getAttribute("sign")));
                pedal.setPrintStyleAlign(FormattingFactory.newPrintStyleAlign(element));
                return pedal;
            case "metronome":
                Element beatUnitElement = XmlUtil.getChildElement(element, "beat-unit");
                Element metronomeNoteElement = XmlUtil.getChildElement(element, "metronome-note");
                Metronome metronome;
                if(beatUnitElement != null) metronome = newBeatMetronome(element);
                else if(metronomeNoteElement != null) metronome = newNoteMetronome(element);
                else return null;
                metronome.setPrintStyleAlign(FormattingFactory.newPrintStyleAlign(element));
                metronome.setJustify(PlacementUtil.getLocation(element.getAttribute("justify")));
                metronome.setParentheses(TypeUtil.getYesNo(element.getAttribute("parentheses")));
                return metronome;
            case "octave-shift":
                OctaveShift octaveShift = new OctaveShift();
                String octaveShiftType = element.getAttribute("type");
                switch (octaveShiftType) {
                    case "up":
                        octaveShift.setType(OctaveShiftType.UP);
                        break;
                    case "down":
                        octaveShift.setType(OctaveShiftType.DOWN);
                        break;
                    case "stop":
                        octaveShift.setType(OctaveShiftType.STOP);
                        break;
                    case "continue":
                        octaveShift.setType(OctaveShiftType.CONTINUE);
                        break;
                }
                octaveShift.setNumber(StringUtil.getInteger(element.getAttribute("number")));
                octaveShift.setSize(StringUtil.getInteger(element.getAttribute("size")));
                octaveShift.setDashedFormatting(FormattingFactory.newDashedFormatting(element));
                octaveShift.setPrintStyle(FormattingFactory.newPrintStyle(element));
                return octaveShift;
            case "harp-pedals":
                HarpPedals harpPedals = new HarpPedals();
                List<PedalTuning> pedalTunings = harpPedals.getPedalTunings();
                for(Element pedalTuningElement : XmlUtil.getChildElements(element, "pedal-tuning")) {
                    PedalTuning pedalTuning = new PedalTuning();
                    pedalTuning.setPedalStep(NoteFactory.newStep(XmlUtil.getChildElement(pedalTuningElement, "pedal-step")));
                    pedalTuning.setPedalAlter(MathUtil.newBigDecimal(XmlUtil.getChildElementText(pedalTuningElement, "pedal-alter")));
                    pedalTunings.add(pedalTuning);
                }
                harpPedals.setPrintStyleAlign(FormattingFactory.newPrintStyleAlign(element));
                return harpPedals;
            case "damp":
                Damp damp = new Damp();
                damp.setPrintStyleAlign(FormattingFactory.newPrintStyleAlign(element));
                return damp;
            case "damp-all":
                DampAll dampAll = new DampAll();
                dampAll.setPrintStyleAlign(FormattingFactory.newPrintStyleAlign(element));
                return dampAll;
            case "eyeglasses":
                Eyeglasses eyeglasses = new Eyeglasses();
                eyeglasses.setPrintStyleAlign(FormattingFactory.newPrintStyleAlign(element));
                return eyeglasses;
            case "string-mute":
                StringMute stringMute = new StringMute();
                String stringMuteType = element.getAttribute("type");
                switch (stringMuteType) {
                    case "on":
                        stringMute.setType(StringMuteDirection.ON);
                        break;
                    case "off":
                        stringMute.setType(StringMuteDirection.OFF);
                        break;
                }
                stringMute.setPrintStyleAlign(FormattingFactory.newPrintStyleAlign(element));
                return stringMute;
            case "scordatura":
                Scordatura scordatura = new Scordatura();
                List<Accord> accords = scordatura.getAccords();
                for(Element accordElement : XmlUtil.getChildElements(element, "accord")) {
                    Accord accord = new Accord();
                    accord.setTuning(AttributesFactory.newTuning(accordElement));
                    accord.setString(StringUtil.getInteger(accordElement.getAttribute("string")));
                    accords.add(accord);
                }
                return scordatura;
            case "principal-voice":
                PrincipalVoice principalVoice = new PrincipalVoice();
                principalVoice.setPrincipalVoice(XmlUtil.getElementText(element));
                principalVoice.setType(PlacementUtil.getConnection(element.getAttribute("type")));
                String symbol = element.getAttribute("symbol");
                switch (symbol) {
                    case "hauptstimme":
                        principalVoice.setSymbol(PrincipalVoiceSymbol.HAUPTSTIMME);
                        break;
                    case "nebenstimme":
                        principalVoice.setSymbol(PrincipalVoiceSymbol.NEBENSTIMME);
                        break;
                    case "plain":
                        principalVoice.setSymbol(PrincipalVoiceSymbol.PLAIN);
                        break;
                    case "none":
                        principalVoice.setSymbol(PrincipalVoiceSymbol.NONE);
                        break;
                }
                principalVoice.setPrintStyleAlign(FormattingFactory.newPrintStyleAlign(element));
                return principalVoice;
            case "accordion-registration":
                AccordionRegistration accordionRegistration = new AccordionRegistration();
                accordionRegistration.setAccordionHigh(XmlUtil.hasChildElement(element, "accordion-high"));
                accordionRegistration.setAccordionMiddle(StringUtil.getInteger(XmlUtil.getChildElementText(element, "accordion-middle")));
                accordionRegistration.setAccordionLow(XmlUtil.hasChildElement(element, "accordion-low"));
                accordionRegistration.setPrintStyleAlign(FormattingFactory.newPrintStyleAlign(element));
                return accordionRegistration;
            case "percussion":
                // TODO: percussion elements
                return null;
            case "other-direction":
                OtherDirection otherDirection = new OtherDirection();
                otherDirection.setValue(XmlUtil.getElementText(element));
                otherDirection.setPrintObject(FormattingFactory.getPrintObject(element));
                otherDirection.setPrintStyleAlign(FormattingFactory.newPrintStyleAlign(element));
                return otherDirection;
            default:
                return null;
        }
    }

    private static BeatMetronome newBeatMetronome(Element element) {
        BeatMetronome beatMetronome = new BeatMetronome();

        for(Element beatElement : XmlUtil.getChildElements(element)) {
            String beatElementName = beatElement.getTagName();
            switch (beatElementName) {
                case "beat-unit":
                    BeatUnit beatUnit = new BeatUnit();
                    beatUnit.setBeatUnit(NoteFactory.newNoteTypeValue(beatElement));
                    if(beatMetronome.getBeatUnit1() == null) beatMetronome.setBeatUnit1(beatUnit);
                    else beatMetronome.setBeatUnit2(beatUnit);
                    break;
                case "beat-unit-dot":
                    BeatUnit beatUnitDot = beatMetronome.getBeatUnit2();
                    if(beatUnitDot == null) beatUnitDot = beatMetronome.getBeatUnit1();
                    Integer beatUnitDots = beatUnitDot.getBeatUnitDots();
                    beatUnitDots++;
                    beatUnitDot.setBeatUnitDots(beatUnitDots);
                    break;
                case "per-minute":
                    PerMinute perMinute = new PerMinute();
                    perMinute.setPerMinute(XmlUtil.getElementText(beatElement));
                    perMinute.setFont(FormattingFactory.newFont(beatElement));
                    beatMetronome.setPerMinute(perMinute);
                    break;
            }
        }

        return beatMetronome;
    }

    private static NoteMetronome newNoteMetronome(Element element) {
        NoteMetronome noteMetronome = new NoteMetronome();

        for(Element noteElement : XmlUtil.getChildElements(element)) {
            String noteElementName = noteElement.getTagName();
            switch (noteElementName) {
                case "metronome-note":
                    MetronomeNote metronomeNote = new MetronomeNote();
                    List<MetronomeNote> metronomeNotes = StringUtil.isEmpty(noteMetronome.getMetronomeRelation()) ?
                            noteMetronome.getMetronomeNotes1() : noteMetronome.getMetronomeNotes2();
                    metronomeNotes.add(metronomeNote);
                    for(Element metronomeNoteElement : XmlUtil.getChildElements(noteElement)) {
                        String metronomeNoteElementName = metronomeNoteElement.getTagName();
                        switch (metronomeNoteElementName) {
                            case "metronome-type":
                                metronomeNote.setMetronomeType(NoteFactory.newNoteTypeValue(metronomeNoteElement));
                                break;
                            case "metronome-dot":
                                Integer metronomeDots = metronomeNote.getMetronomeDots();
                                metronomeDots++;
                                metronomeNote.setMetronomeDots(metronomeDots);
                                break;
                            case "metronome-beam":
                                List<MetronomeBeam> metronomeBeams = metronomeNote.getMetronomeBeams();
                                MetronomeBeam metronomeBeam = new MetronomeBeam();
                                metronomeBeam.setBeamValue(NoteFactory.newBeamType(metronomeNoteElement));
                                metronomeBeam.setNumber(StringUtil.getInteger(metronomeNoteElement.getAttribute("number")));
                                metronomeBeams.add(metronomeBeam);
                                break;
                            case "metronome-tuplet":
                                MetronomeTuplet metronomeTuplet = new MetronomeTuplet();
                                metronomeTuplet.setTimeModification(NoteFactory.newTimeModification(metronomeNoteElement));
                                metronomeTuplet.setType(PlacementUtil.getConnection(metronomeNoteElement.getAttribute("type")));
                                metronomeTuplet.setBracket(TypeUtil.getYesNo(metronomeNoteElement.getAttribute("bracket")));
                                metronomeTuplet.setShowNumber(NotationFactory.newShowTuplet(metronomeNoteElement.getAttribute("show-number")));
                                metronomeNote.setMetronomeTuplet(metronomeTuplet);
                                break;
                        }
                    }
                    break;
                case "metronome-relation":
                    noteMetronome.setMetronomeRelation(XmlUtil.getElementText(noteElement));
                    break;
            }
        }

        return noteMetronome;
    }

    public static Offset newOffset(Element element) {
        if (element == null) return null;

        Offset offset = new Offset();
        offset.setDivisions(MathUtil.newBigDecimal(XmlUtil.getElementText(element)));
        offset.setSound(TypeUtil.getYesNo(element.getAttribute("sound")));

        return offset;
    }
}
