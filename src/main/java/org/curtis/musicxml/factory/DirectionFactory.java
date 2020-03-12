package org.curtis.musicxml.factory;

import org.curtis.musicxml.attributes.Image;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.direction.DirectionOffset;
import org.curtis.musicxml.direction.Sound;
import org.curtis.musicxml.direction.SoundMidi;
import org.curtis.musicxml.direction.directiontype.Accord;
import org.curtis.musicxml.direction.directiontype.AccordionRegistration;
import org.curtis.musicxml.direction.directiontype.Bracket;
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
import org.curtis.musicxml.direction.directiontype.Pedal;
import org.curtis.musicxml.direction.directiontype.PedalTuning;
import org.curtis.musicxml.direction.directiontype.PedalType;
import org.curtis.musicxml.direction.directiontype.PrincipalVoice;
import org.curtis.musicxml.direction.directiontype.PrincipalVoiceSymbol;
import org.curtis.musicxml.direction.directiontype.Rehearsal;
import org.curtis.musicxml.direction.directiontype.Scordatura;
import org.curtis.musicxml.direction.directiontype.StaffDivide;
import org.curtis.musicxml.direction.directiontype.StaffDivideSymbol;
import org.curtis.musicxml.direction.directiontype.StringMute;
import org.curtis.musicxml.direction.directiontype.StringMuteDirection;
import org.curtis.musicxml.direction.directiontype.Symbol;
import org.curtis.musicxml.direction.directiontype.Wedge;
import org.curtis.musicxml.direction.directiontype.WedgeType;
import org.curtis.musicxml.direction.directiontype.Words;
import org.curtis.musicxml.direction.directiontype.metronome.BeatMetronome;
import org.curtis.musicxml.direction.directiontype.metronome.BeatUnit;
import org.curtis.musicxml.direction.directiontype.metronome.BeatUnitTied;
import org.curtis.musicxml.direction.directiontype.metronome.Metronome;
import org.curtis.musicxml.direction.directiontype.metronome.MetronomeBeam;
import org.curtis.musicxml.direction.directiontype.metronome.MetronomeMark;
import org.curtis.musicxml.direction.directiontype.metronome.MetronomeNote;
import org.curtis.musicxml.direction.directiontype.metronome.MetronomeTuplet;
import org.curtis.musicxml.direction.directiontype.metronome.NoteMetronome;
import org.curtis.musicxml.direction.directiontype.metronome.PerMinute;
import org.curtis.musicxml.direction.directiontype.percussion.Beater;
import org.curtis.musicxml.direction.directiontype.percussion.BeaterValue;
import org.curtis.musicxml.direction.directiontype.percussion.Effect;
import org.curtis.musicxml.direction.directiontype.percussion.EffectType;
import org.curtis.musicxml.direction.directiontype.percussion.Glass;
import org.curtis.musicxml.direction.directiontype.percussion.GlassType;
import org.curtis.musicxml.direction.directiontype.percussion.Membrane;
import org.curtis.musicxml.direction.directiontype.percussion.MembraneType;
import org.curtis.musicxml.direction.directiontype.percussion.Metal;
import org.curtis.musicxml.direction.directiontype.percussion.MetalType;
import org.curtis.musicxml.direction.directiontype.percussion.OtherPercussion;
import org.curtis.musicxml.direction.directiontype.percussion.Percussion;
import org.curtis.musicxml.direction.directiontype.percussion.Pitched;
import org.curtis.musicxml.direction.directiontype.percussion.PitchedType;
import org.curtis.musicxml.direction.directiontype.percussion.Stick;
import org.curtis.musicxml.direction.directiontype.percussion.StickLocation;
import org.curtis.musicxml.direction.directiontype.percussion.StickLocationType;
import org.curtis.musicxml.direction.directiontype.percussion.StickMaterial;
import org.curtis.musicxml.direction.directiontype.percussion.StickType;
import org.curtis.musicxml.direction.directiontype.percussion.Timpani;
import org.curtis.musicxml.direction.directiontype.percussion.TipDirection;
import org.curtis.musicxml.direction.directiontype.percussion.Wood;
import org.curtis.musicxml.direction.directiontype.percussion.WoodType;
import org.curtis.musicxml.display.Halign;
import org.curtis.musicxml.util.TypeUtil;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

public class DirectionFactory {
    private DirectionFactory() {

    }

    public static DirectionType newDirectionType(Element element) {
        if(element == null) return null;

        DirectionType directionType = null;
        switch (element.getTagName()) {
            case "rehearsal":
                Rehearsal rehearsal = new Rehearsal();
                rehearsal.setTextFormat(TextFormatFactory.newTextFormat(element));
                directionType = rehearsal;
                break;
            case "segno":
                directionType = NotationFactory.newSegno(element);
                break;
            case "words":
                Words words = new Words();
                words.setTextFormat(TextFormatFactory.newTextFormat(element));
                directionType = words;
                break;
            case "symbol":
                Symbol symbol = new Symbol();
                symbol.setDisplay(DisplayFactory.newDisplay(element));
                symbol.setTextFormat(TextFormatFactory.newTextFormat(element));
                directionType = symbol;
                break;
            case "coda":
                directionType = NotationFactory.newCoda(element);
                break;
            case "wedge":
                Wedge wedge = new Wedge();
                wedge.setType(FactoryUtil.enumValue(WedgeType.class, element.getAttribute("type")));
                wedge.setNumber(StringUtil.getInteger(element.getAttribute("number")));
                wedge.setSpread(MathUtil.newBigDecimal(element.getAttribute("spread")));
                wedge.setNiente(TypeUtil.getYesNo(element.getAttribute("niente")));
                wedge.setLineType(NotationFactory.newLineType(element));
                wedge.setDashedFormatting(FormattingFactory.newDashedFormatting(element));
                directionType = wedge;
                break;
            case "dynamics":
                directionType = NotationFactory.newDynamics(element);
                break;
            case "dashes":
                Dashes dashes = new Dashes();
                dashes.setType(FactoryUtil.enumValue(Connection.class, element.getAttribute("type")));
                dashes.setNumber(StringUtil.getInteger(element.getAttribute("number")));
                dashes.setDashedFormatting(FormattingFactory.newDashedFormatting(element));
                directionType = dashes;
                break;
            case "bracket":
                Bracket bracket = new Bracket();
                bracket.setType(FactoryUtil.enumValue(Connection.class, element.getAttribute("type")));
                bracket.setNumber(StringUtil.getInteger(element.getAttribute("number")));
                bracket.setLineEnd(FactoryUtil.enumValue(LineEnd.class, element.getAttribute("line-end")));
                bracket.setEndLength(MathUtil.newBigDecimal(element.getAttribute("end-length")));
                bracket.setLineType(NotationFactory.newLineType(element));
                bracket.setDashedFormatting(FormattingFactory.newDashedFormatting(element));
                directionType = bracket;
                break;
            case "pedal":
                Pedal pedal = new Pedal();
                pedal.setPedalType(FactoryUtil.enumValue(PedalType.class, element.getAttribute("type")));
                pedal.setNumber(StringUtil.getInteger(element.getAttribute("number")));
                pedal.setLine(TypeUtil.getYesNo(element.getAttribute("line")));
                pedal.setSign(TypeUtil.getYesNo(element.getAttribute("sign")));
                pedal.setAbbreviated(TypeUtil.getYesNo(element.getAttribute("abbreviated")));
                directionType = pedal;
                break;
            case "metronome":
                Element beatUnitElement = XmlUtil.getChildElement(element, "beat-unit");
                Element metronomeNoteElement = XmlUtil.getChildElement(element, "metronome-note");
                Metronome metronome;
                if(beatUnitElement != null) metronome = newBeatMetronome(element);
                else if(metronomeNoteElement != null) metronome = newNoteMetronome(element);
                else return null;
                metronome.setJustify(FactoryUtil.enumValue(Halign.class, element.getAttribute("justify")));
                metronome.setParentheses(TypeUtil.getYesNo(element.getAttribute("parentheses")));
                directionType = metronome;
                break;
            case "octave-shift":
                OctaveShift octaveShift = new OctaveShift();
                octaveShift.setType(FactoryUtil.enumValue(OctaveShiftType.class, element.getAttribute("type")));
                octaveShift.setNumber(StringUtil.getInteger(element.getAttribute("number")));
                Integer octaveShiftSize = StringUtil.getInteger(element.getAttribute("size"));
                if (octaveShiftSize != null) {
                    octaveShift.setSize(octaveShiftSize);
                }
                octaveShift.setDashedFormatting(FormattingFactory.newDashedFormatting(element));
                directionType = octaveShift;
                break;
            case "harp-pedals":
                HarpPedals harpPedals = new HarpPedals();
                List<PedalTuning> pedalTunings = harpPedals.getPedalTunings();
                for(Element pedalTuningElement : XmlUtil.getChildElements(element, "pedal-tuning")) {
                    PedalTuning pedalTuning = new PedalTuning();
                    pedalTuning.setPedalStep(NoteFactory.newStep(XmlUtil.getChildElement(pedalTuningElement, "pedal-step")));
                    pedalTuning.setPedalAlter(MathUtil.newBigDecimal(XmlUtil.getChildElementText(pedalTuningElement, "pedal-alter")));
                    pedalTunings.add(pedalTuning);
                }
                directionType = harpPedals;
                break;
            case "damp":
                directionType = new Damp();
                break;
            case "damp-all":
                directionType = new DampAll();
                break;
            case "eyeglasses":
                directionType = new Eyeglasses();
                break;
            case "string-mute":
                StringMute stringMute = new StringMute();
                stringMute.setType(FactoryUtil.enumValue(StringMuteDirection.class, element.getAttribute("type")));
                directionType = stringMute;
                break;
            case "scordatura":
                Scordatura scordatura = new Scordatura();
                List<Accord> accords = scordatura.getAccords();
                for(Element accordElement : XmlUtil.getChildElements(element, "accord")) {
                    Accord accord = new Accord();
                    accord.setTuning(AttributesFactory.newTuning(accordElement));
                    accord.setString(StringUtil.getInteger(accordElement.getAttribute("string")));
                    accords.add(accord);
                }
                directionType = scordatura;
                break;
            case "image":
                directionType = newImage(element);
                break;
            case "principal-voice":
                PrincipalVoice principalVoice = new PrincipalVoice();
                principalVoice.setPrincipalVoice(XmlUtil.getElementText(element));
                principalVoice.setType(FactoryUtil.enumValue(Connection.class, element.getAttribute("type")));
                principalVoice.setSymbol(FactoryUtil.enumValue(PrincipalVoiceSymbol.class, element.getAttribute("symbol")));
                directionType = principalVoice;
                break;
            case "percussion":
                List<Element> percussionElements = XmlUtil.getChildElements(element);
                if (percussionElements.isEmpty()) return null;
                directionType = newPercussion(percussionElements.get(0));
                break;
            case "accordion-registration":
                AccordionRegistration accordionRegistration = new AccordionRegistration();
                accordionRegistration.setAccordionHigh(XmlUtil.hasChildElement(element, "accordion-high"));
                accordionRegistration.setAccordionMiddle(StringUtil.getInteger(XmlUtil.getChildElementText(element, "accordion-middle")));
                accordionRegistration.setAccordionLow(XmlUtil.hasChildElement(element, "accordion-low"));
                directionType = accordionRegistration;
                break;
            case "staff-divide":
                StaffDivide staffDivide = new StaffDivide();
                staffDivide.setStaffDivideSymbol(FactoryUtil.enumValue(StaffDivideSymbol.class, element.getAttribute("type")));
                directionType = staffDivide;
                break;
            case "other-direction":
                OtherDirection otherDirection = new OtherDirection();
                otherDirection.setValue(XmlUtil.getElementText(element));
                otherDirection.setPrintObject(FormattingFactory.getPrintObject(element));
                otherDirection.setSmufl(element.getAttribute("smufl"));
                directionType = otherDirection;
                break;
        }

        if (directionType != null) {
            directionType.setElementId(element.getAttribute("id"));
            directionType.setDisplay(DisplayFactory.newDisplay(element));
        }

        return directionType;
    }

    private static BeatMetronome newBeatMetronome(Element element) {
        BeatMetronome beatMetronome = new BeatMetronome();

        List<Element> metronomeMark1Elements = new ArrayList<>();
        List<Element> metronomeMark2Elements = new ArrayList<>();

        boolean list1Complete = false;
        for(Element beatElement : XmlUtil.getChildElements(element)) {
            if (list1Complete) {
                metronomeMark2Elements.add(beatElement);
                continue;
            }

            switch (beatElement.getTagName()) {
                case "beat-unit":
                    if (metronomeMark1Elements.isEmpty()) metronomeMark1Elements.add(beatElement);
                    else {
                        metronomeMark2Elements.add(beatElement);
                        list1Complete = true;
                    }
                    break;
                case "per-minute":
                    metronomeMark2Elements.add(beatElement);
                    list1Complete = true;
                    break;
                default:
                    metronomeMark1Elements.add(beatElement);
                    break;
            }
        }

        beatMetronome.setMetronomeMark1(newMetronomeMark(metronomeMark1Elements));
        beatMetronome.setMetronomeMark2(newMetronomeMark(metronomeMark2Elements));

        return beatMetronome;
    }

    private static MetronomeMark newMetronomeMark(List<Element> elements) {
        if (elements.isEmpty()) return null;

        String metronomeMarkElementName = elements.get(0).getTagName();
        if (metronomeMarkElementName.equals("beat-unit")) return newBeatUnit(elements);
        else if (metronomeMarkElementName.equals("per-minute")) return newPerMinute(elements.get(0));
        else return null;
    }

    private static BeatUnit newBeatUnit(List<Element> elements) {
        BeatUnit beatUnit = new BeatUnit();
        for(Element element : elements) {
            String beatElementName = element.getTagName();
            switch (beatElementName) {
                case "beat-unit":
                    beatUnit.setBeatUnit(NoteFactory.newNoteTypeValue(element));
                    break;
                case "beat-unit-dot":
                    Integer beatUnitDots = beatUnit.getBeatUnitDots();
                    beatUnitDots++;
                    beatUnit.setBeatUnitDots(beatUnitDots);
                    break;
                case "beat-unit-tied":
                    BeatUnitTied beatUnitTied = new BeatUnitTied();
                    beatUnitTied.setBeatUnit(newBeatUnit(XmlUtil.getChildElements(element)));
                    beatUnit.getBeatUnitTieds().add(beatUnitTied);
                    break;
            }
        }

        return beatUnit;
    }

    private static PerMinute newPerMinute(Element element) {
        PerMinute perMinute = new PerMinute();
        perMinute.setPerMinute(XmlUtil.getElementText(element));
        perMinute.setDisplay(DisplayFactory.newDisplay(element));

        return perMinute;
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
                                metronomeBeam.setBeamType(NoteFactory.newBeamType(metronomeNoteElement));
                                Integer beamNumber = StringUtil.getInteger(metronomeNoteElement.getAttribute("number"));
                                if (beamNumber != null) {
                                    metronomeBeam.setNumber(beamNumber);
                                }
                                metronomeBeams.add(metronomeBeam);
                                break;
                            case "metronome-tied":
                                metronomeNote.setMetronomeTied(FactoryUtil.enumValue(Connection.class, metronomeNoteElement.getAttribute("type")));
                                break;
                            case "metronome-tuplet":
                                MetronomeTuplet metronomeTuplet = new MetronomeTuplet();
                                metronomeTuplet.setTimeModification(NoteFactory.newTimeModification(metronomeNoteElement));
                                metronomeTuplet.setType(FactoryUtil.enumValue(Connection.class, metronomeNoteElement.getAttribute("type")));
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
                case "metronome-arrows":
                    noteMetronome.setMetronomeArrows(true);
                    break;
            }
        }

        return noteMetronome;
    }

    public static DirectionOffset newOffset(Element element) {
        if (element == null) return null;

        DirectionOffset offset = new DirectionOffset();
        offset.setDivisions(MathUtil.newBigDecimal(XmlUtil.getElementText(element)));
        offset.setSound(TypeUtil.getYesNo(element.getAttribute("sound")));

        return offset;
    }

    private static Percussion newPercussion(Element element) {
        if (element == null) return null;

        String elementName = element.getTagName();
        String elementValue = XmlUtil.getElementText(element);
        Percussion percussion = null;
        switch (elementName) {
            case "glass":
                Glass glass = new Glass();
                glass.setType(FactoryUtil.enumValue(GlassType.class, elementValue));
                glass.setSmufl(element.getAttribute("smufl"));
                percussion = glass;
                break;
            case "metal":
                Metal metal = new Metal();
                metal.setType(FactoryUtil.enumValue(MetalType.class, elementValue));
                percussion = metal;
                break;
            case "wood":
                Wood wood = new Wood();
                wood.setType(FactoryUtil.enumValue(WoodType.class, elementValue));
                percussion = wood;
                break;
            case "pitched":
                Pitched pitched = new Pitched();
                pitched.setType(FactoryUtil.enumValue(PitchedType.class, elementValue));
                pitched.setSmufl(element.getAttribute("smufl"));
                percussion = pitched;
                break;
            case "membrane":
                Membrane membrane = new Membrane();
                membrane.setType(FactoryUtil.enumValue(MembraneType.class, elementValue));
                percussion = membrane;
                break;
            case "effect":
                Effect effect = new Effect();
                effect.setType(FactoryUtil.enumValue(EffectType.class, elementValue));
                percussion = effect;
                break;
            case "timpani":
                percussion = new Timpani();
                break;
            case "beater":
                Beater beater = new Beater();
                beater.setBeaterValue(FactoryUtil.enumValue(BeaterValue.class, elementValue));
                beater.setTip(newTipDirection(element));
                percussion = beater;
                break;
            case "stick":
                Stick stick = new Stick();
                String stickType = XmlUtil.getChildElementText(element, "stick-type");
                stick.setStickType(FactoryUtil.enumValue(StickType.class, stickType));
                stick.setStickMaterial(FactoryUtil.enumValue(StickMaterial.class, XmlUtil.getChildElementText(element, "stick-material")));
                stick.setTip(newTipDirection(element));
                percussion = stick;
                break;
            case "stick-location":
                StickLocation stickLocation = new StickLocation();
                stickLocation.setType(FactoryUtil.enumValue(StickLocationType.class, elementValue));
                percussion = stickLocation;
                break;
            case "other-percussion":
                OtherPercussion otherPercussion = new OtherPercussion();
                otherPercussion.setValue(XmlUtil.getElementText(element));
                otherPercussion.setSmufl(element.getAttribute("smufl"));
                percussion = otherPercussion;
                break;
        }

        if (percussion == null) return null;

        percussion.setEnclosure(FormattingFactory.newEnclosureShape(element));

        return percussion;
    }

    private static TipDirection newTipDirection(Element element) {
        if (element == null) return null;

        return FactoryUtil.enumValue(TipDirection.class, element.getAttribute("tip"));
    }

    public static Sound newSound(Element element) {
        if (element == null) return null;

        Sound sound = new Sound();
        sound.setElementId(element.getAttribute("id"));
        sound.setOffset(newOffset(XmlUtil.getChildElement(element, "offset")));
        sound.setTempo(MathUtil.newBigDecimal(element.getAttribute("tempo")));
        sound.setDynamics(MathUtil.newBigDecimal(element.getAttribute("dynamics")));
        sound.setDacapo(TypeUtil.getYesNo(element.getAttribute("dacapo")));
        sound.setSegno(element.getAttribute("segno"));
        sound.setDalsegno(element.getAttribute("dalsegno"));
        sound.setCoda(element.getAttribute("coda"));
        sound.setTocoda(element.getAttribute("tocoda"));
        sound.setDivisions(MathUtil.newBigDecimal(element.getAttribute("divisions")));
        sound.setForwardRepeat(TypeUtil.getYesNo(element.getAttribute("forward-repeat")));
        sound.setFine(element.getAttribute("fine"));
        sound.setTimeOnly(element.getAttribute("time-only"));
        sound.setPizzicato(TypeUtil.getYesNo(element.getAttribute("pizzicato")));
        sound.setPan(MathUtil.newBigDecimal(element.getAttribute("pan")));
        sound.setElevation(MathUtil.newBigDecimal(element.getAttribute("elevation")));
        sound.setDamperPedal(element.getAttribute("damper-pedal"));
        sound.setSoftPedal(element.getAttribute("soft-pedal"));
        sound.setSostenutoPedal(element.getAttribute("sostenuto-pedal"));
        List<Element> midiDeviceElements = XmlUtil.getChildElements(element, "midi-device");
        for (Element midiDeviceElement : midiDeviceElements) {
            handleSoundMidi(sound, midiDeviceElement.getAttribute("id"), midiDeviceElement, null, null);
        }
        List<Element> midiInstrumentElements = XmlUtil.getChildElements(element, "midi-instrument");
        for(Element midiInstrumentElement : midiInstrumentElements) {
            handleSoundMidi(sound, midiInstrumentElement.getAttribute("id"), null, midiInstrumentElement, null);
        }
        List<Element> playElements = XmlUtil.getChildElements(element, "play");
        for(Element playElement : playElements) {
            handleSoundMidi(sound, playElement.getAttribute("id"), null, null, playElement);
        }

        return sound;
    }

    private static void handleSoundMidi(Sound sound, String id, Element midiDeviceElement, Element midiInstrumentElement, Element playElement) {
        SoundMidi soundMidi;
        if (StringUtil.isEmpty(id)) {
            soundMidi = new SoundMidi();
            sound.getSoundMidis().add(soundMidi);
        } else {
            soundMidi = sound.getSoundMidis().stream().filter(spm -> spm.getSoundMidiId().equals(id)).findAny().orElse(null);
            if (soundMidi == null) {
                soundMidi = new SoundMidi();
                soundMidi.setSoundMidiId(id);
                sound.getSoundMidis().add(soundMidi);
            }
        }

        if (midiDeviceElement != null) soundMidi.setMidiDevice(ScorePartFactory.newMidiDevice(midiDeviceElement));
        else if (midiInstrumentElement != null) soundMidi.setMidiInstrument(ScorePartFactory.newMidiInstrument(midiInstrumentElement));
        else if (playElement != null) soundMidi.setPlay(ScorePartFactory.newPlay(playElement));
    }

    public static Image newImage(Element element) {
        if (element == null) return null;

        Image image = new Image();
        image.setSource(element.getAttribute("source"));
        image.setType(element.getAttribute("type"));
        image.setHeight(MathUtil.newBigDecimal(element.getAttribute("height")));
        image.setWidth(MathUtil.newBigDecimal(element.getAttribute("width")));
        image.setDisplay(DisplayFactory.newDisplay(element));

        return image;
    }
}
