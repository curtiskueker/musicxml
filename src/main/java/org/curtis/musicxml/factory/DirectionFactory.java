package org.curtis.musicxml.factory;

import org.curtis.musicxml.attributes.Image;
import org.curtis.musicxml.common.FormattedText;
import org.curtis.musicxml.direction.DirectionOffset;
import org.curtis.musicxml.direction.Sound;
import org.curtis.musicxml.direction.SoundMidi;
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
import org.curtis.musicxml.direction.directiontype.Pedal;
import org.curtis.musicxml.direction.directiontype.PedalTuning;
import org.curtis.musicxml.direction.directiontype.PrincipalVoice;
import org.curtis.musicxml.direction.directiontype.PrincipalVoiceSymbol;
import org.curtis.musicxml.direction.directiontype.Rehearsal;
import org.curtis.musicxml.direction.directiontype.Scordatura;
import org.curtis.musicxml.direction.directiontype.Segno;
import org.curtis.musicxml.direction.directiontype.StringMute;
import org.curtis.musicxml.direction.directiontype.StringMuteDirection;
import org.curtis.musicxml.direction.directiontype.Wedge;
import org.curtis.musicxml.direction.directiontype.WedgeType;
import org.curtis.musicxml.direction.directiontype.Words;
import org.curtis.musicxml.direction.directiontype.metronome.BeatMetronome;
import org.curtis.musicxml.direction.directiontype.metronome.BeatUnit;
import org.curtis.musicxml.direction.directiontype.metronome.Metronome;
import org.curtis.musicxml.direction.directiontype.metronome.MetronomeBeam;
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
                FormattedText rehearsalFormattedText = FormattingFactory.newFormattedText(element);
                if (rehearsalFormattedText == null) return null;
                rehearsal.setFormattedText(rehearsalFormattedText);
                return rehearsal;
            case "segno":
                Segno segno = new Segno();
                segno.setPrintStyleAlign(FormattingFactory.newPrintStyleAlign(element));
                return segno;
            case "words":
                Words words = new Words();
                FormattedText wordsFormattedText = FormattingFactory.newFormattedText(element);
                if (wordsFormattedText == null) return null;
                words.setFormattedText(wordsFormattedText);
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
                Integer octaveShiftSize = StringUtil.getInteger(element.getAttribute("size"));
                if (octaveShiftSize != null) {
                    octaveShift.setSize(octaveShiftSize);
                }
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
            case "image":
                return newImage(element);
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
                List<Element> percussionElements = XmlUtil.getChildElements(element);
                if (percussionElements.isEmpty()) return null;
                return newPercussion(percussionElements.get(0));
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
                                metronomeBeam.setBeamType(NoteFactory.newBeamType(metronomeNoteElement));
                                Integer beamNumber = StringUtil.getInteger(metronomeNoteElement.getAttribute("number"));
                                if (beamNumber != null) {
                                    metronomeBeam.setNumber(beamNumber);
                                }
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
                switch (elementValue) {
                    case "wind chimes":
                        glass.setType(GlassType.WIND_CHIMES);
                }
                percussion = glass;
                break;
            case "metal":
                Metal metal = new Metal();
                switch (elementValue) {
                    case "almglocken":
                        metal.setType(MetalType.ALMGLOCKEN);
                        break;
                    case "bell":
                        metal.setType(MetalType.BELL);
                        break;
                    case "bell plate":
                        metal.setType(MetalType.BELL_PLATE);
                        break;
                    case "brake drum":
                        metal.setType(MetalType.BRAKE_DRUM);
                        break;
                    case "Chinese cymbal":
                        metal.setType(MetalType.CHINESE_CYMBAL);
                        break;
                    case "cowbell":
                        metal.setType(MetalType.COWBELL);
                        break;
                    case "crash cymbals":
                        metal.setType(MetalType.CRASH_CYMBALS);
                        break;
                    case "crotale":
                        metal.setType(MetalType.CROTALE);
                        break;
                    case "cymbal tongs":
                        metal.setType(MetalType.CYMBAL_TONGS);
                        break;
                    case "domed gong":
                        metal.setType(MetalType.DOMED_GONG);
                        break;
                    case "finger cymbals":
                        metal.setType(MetalType.FINGER_CYMBALS);
                        break;
                    case "flexatone":
                        metal.setType(MetalType.FLEXATONE);
                        break;
                    case "gong":
                        metal.setType(MetalType.GONG);
                        break;
                    case "hi-hat":
                        metal.setType(MetalType.HI_HAT);
                        break;
                    case "high-hat cymbals":
                        metal.setType(MetalType.HI_HAT_CYMBALS);
                        break;
                    case "handbell":
                        metal.setType(MetalType.HANDBELL);
                        break;
                    case "sistrum":
                        metal.setType(MetalType.SISTRUM);
                        break;
                    case "sizzle cymbal":
                        metal.setType(MetalType.SIZZLE_CYMBAL);
                        break;
                    case "sleigh bells":
                        metal.setType(MetalType.SLEIGH_BELLS);
                        break;
                    case "suspended cymbal":
                        metal.setType(MetalType.SUSPENDED_CYMBAL);
                        break;
                    case "tam tam":
                        metal.setType(MetalType.TAM_TAM);
                        break;
                    case "triangle":
                        metal.setType(MetalType.TRIANGLE);
                        break;
                    case "Vietnamese hat":
                        metal.setType(MetalType.VIETNAMESE_HAT);
                        break;
                }
                percussion = metal;
                break;
            case "wood":
                Wood wood = new Wood();
                switch (elementValue) {
                    case "board clapper":
                        wood.setType(WoodType.BOARD_CLAPPER);
                        break;
                    case "cabasa":
                        wood.setType(WoodType.CABASA);
                        break;
                    case "castanets":
                        wood.setType(WoodType.CASTANETS);
                        break;
                    case "claves":
                        wood.setType(WoodType.CLAVES);
                        break;
                    case "guiro":
                        wood.setType(WoodType.GUIRO);
                        break;
                    case "log drum":
                        wood.setType(WoodType.LOG_DRUM);
                        break;
                    case "maraca":
                        wood.setType(WoodType.MARACA);
                        break;
                    case "maracas":
                        wood.setType(WoodType.MARACAS);
                        break;
                    case "ratchet":
                        wood.setType(WoodType.RATCHET);
                        break;
                    case "sandpaper blocks":
                        wood.setType(WoodType.SANDPAPER_BLOCKS);
                        break;
                    case "slit drum":
                        wood.setType(WoodType.SLIT_DRUM);
                        break;
                    case "temple block":
                        wood.setType(WoodType.TEMPLE_BLOCK);
                        break;
                    case "vibraslap":
                        wood.setType(WoodType.VIBRASLAP);
                        break;
                    case "wood block":
                        wood.setType(WoodType.WOOD_BLOCK);
                        break;
                }
                percussion = wood;
                break;
            case "pitched":
                Pitched pitched = new Pitched();
                switch (elementValue) {
                    case "chimes":
                        pitched.setType(PitchedType.CHIMES);
                        break;
                    case "glockenspiel":
                        pitched.setType(PitchedType.GLOCKENSPIEL);
                        break;
                    case "mallet":
                        pitched.setType(PitchedType.MALLET);
                        break;
                    case "marimba":
                        pitched.setType(PitchedType.MARIMBA);
                        break;
                    case "tubular chimes":
                        pitched.setType(PitchedType.TUBULAR_CHIMES);
                        break;
                    case "vibraphone":
                        pitched.setType(PitchedType.VIBRAPHONE);
                        break;
                    case "xylophone":
                        pitched.setType(PitchedType.XYLOPHONE);
                        break;
                }
                percussion = pitched;
                break;
            case "membrane":
                Membrane membrane = new Membrane();
                switch (elementValue) {
                    case "bass drum":
                        membrane.setType(MembraneType.BASS_DRUM);
                        break;
                    case "bass drum on side":
                        membrane.setType(MembraneType.BASS_DRUM_ON_SIDE);
                        break;
                    case "bongos":
                        membrane.setType(MembraneType.BONGOS);
                        break;
                    case "conga drum":
                        membrane.setType(MembraneType.CONGA_DRUM);
                        break;
                    case "goblet drum":
                        membrane.setType(MembraneType.GOBLET_DRUM);
                        break;
                    case "military drum":
                        membrane.setType(MembraneType.MILITARY_DRUM);
                        break;
                    case "snare drum":
                        membrane.setType(MembraneType.SNARE_DRUM);
                        break;
                    case "snare drum snares off":
                        membrane.setType(MembraneType.SHARE_DRUM_SNARES_OFF);
                        break;
                    case "tambourine":
                        membrane.setType(MembraneType.TAMBOURINE);
                        break;
                    case "tenor drum":
                        membrane.setType(MembraneType.TENOR_DRUM);
                        break;
                    case "timbales":
                        membrane.setType(MembraneType.TIMBALES);
                        break;
                    case "tomtom":
                        membrane.setType(MembraneType.TOMTOM);
                        break;
                }
                percussion = membrane;
                break;
            case "effect":
                Effect effect = new Effect();
                switch (elementValue) {
                    case "anvil":
                        effect.setType(EffectType.ANVIL);
                        break;
                    case "auto horn":
                        effect.setType(EffectType.AUTO_HORN);
                        break;
                    case "bird whistle":
                        effect.setType(EffectType.BIRD_WHISTLE);
                        break;
                    case "cannon":
                        effect.setType(EffectType.CANNON);
                        break;
                    case "duck call":
                        effect.setType(EffectType.DUCK_CALL);
                        break;
                    case "gun shot":
                        effect.setType(EffectType.GUN_SHOT);
                        break;
                    case "klaxon horn":
                        effect.setType(EffectType.KLAXON_HORN);
                        break;
                    case "lions roar":
                        effect.setType(EffectType.LIONS_ROAR);
                        break;
                    case "police whistle":
                        effect.setType(EffectType.POLICE_WHISTLE);
                        break;
                    case "siren":
                        effect.setType(EffectType.SIREN);
                        break;
                    case "slide whistle":
                        effect.setType(EffectType.SLIDE_WHISTLE);
                        break;
                    case "thunder sheet":
                        effect.setType(EffectType.THUNDER_SHEET);
                        break;
                    case "wind machine":
                        effect.setType(EffectType.WIND_MACHINE);
                        break;
                    case "wind whistle":
                        effect.setType(EffectType.WIND_WHISTLE);
                        break;
                }
                percussion = effect;
                break;
            case "timpani":
                percussion = new Timpani();
                break;
            case "beater":
                Beater beater = new Beater();
                switch (elementValue) {
                    case "bow":
                        beater.setBeaterValue(BeaterValue.BOW);
                        break;
                    case "chime hammer":
                        beater.setBeaterValue(BeaterValue.CHIME_HAMNER);
                        break;
                    case "coin":
                        beater.setBeaterValue(BeaterValue.COIN);
                        break;
                    case "finger":
                        beater.setBeaterValue(BeaterValue.FINGER);
                        break;
                    case "fingernail":
                        beater.setBeaterValue(BeaterValue.FINGERNAIL);
                        break;
                    case "fist":
                        beater.setBeaterValue(BeaterValue.FIST);
                        break;
                    case "guiro scraper":
                        beater.setBeaterValue(BeaterValue.GUIRO_SCRAPER);
                        break;
                    case "hammer":
                        beater.setBeaterValue(BeaterValue.HAMMER);
                        break;
                    case "hand":
                        beater.setBeaterValue(BeaterValue.HAND);
                        break;
                    case "jazz stick":
                        beater.setBeaterValue(BeaterValue.JAZZ_STICK);
                        break;
                    case "knitting needle":
                        beater.setBeaterValue(BeaterValue.KNITTING_NEEDLE);
                        break;
                    case "metal hammer":
                        beater.setBeaterValue(BeaterValue.METAL_HAMMER);
                        break;
                    case "snare stick":
                        beater.setBeaterValue(BeaterValue.SNARE_STICK);
                        break;
                    case "spoon mallet":
                        beater.setBeaterValue(BeaterValue.SPOON_MALLET);
                        break;
                    case "triangle beater":
                        beater.setBeaterValue(BeaterValue.TRIANGLE_BEATER);
                        break;
                    case "triangle beater plain":
                        beater.setBeaterValue(BeaterValue.TRIANGLE_BEATER_PLAIN);
                        break;
                    case "wire brush":
                        beater.setBeaterValue(BeaterValue.WIRE_BRUSH);
                        break;
                }
                beater.setTip(newTipDirection(element));
                percussion = beater;
                break;
            case "stick":
                Stick stick = new Stick();
                String stickType = XmlUtil.getChildElementText(element, "stick-type");
                switch (stickType) {
                    case "bass drum":
                        stick.setStickType(StickType.BASS_DRUM);
                        break;
                    case "double bass drum":
                        stick.setStickType(StickType.DOUBLE_BASS_DRUM);
                        break;
                    case "timpani":
                        stick.setStickType(StickType.TIMPANI);
                        break;
                    case "xylophone":
                        stick.setStickType(StickType.XYLOPHONE);
                        break;
                    case "yarn":
                        stick.setStickType(StickType.YARN);
                        break;
                }
                String stickMaterial = XmlUtil.getChildElementText(element, "stick-material");
                switch (stickMaterial) {
                    case "soft":
                        stick.setStickMaterial(StickMaterial.SOFT);
                        break;
                    case "medium":
                        stick.setStickMaterial(StickMaterial.MEDIUM);
                        break;
                    case "hard":
                        stick.setStickMaterial(StickMaterial.HARD);
                        break;
                    case "shaded":
                        stick.setStickMaterial(StickMaterial.SHADED);
                        break;
                    case "x":
                        stick.setStickMaterial(StickMaterial.X);
                        break;
                }
                stick.setTip(newTipDirection(element));
                percussion = stick;
                break;
            case "stick-location":
                StickLocation stickLocation = new StickLocation();
                switch (elementValue) {
                    case "center":
                        stickLocation.setType(StickLocationType.CENTER);
                        break;
                    case "rim":
                        stickLocation.setType(StickLocationType.RIM);
                        break;
                    case "cymbal bell":
                        stickLocation.setType(StickLocationType.CYMBAL_BELL);
                        break;
                    case "cymbal edge":
                        stickLocation.setType(StickLocationType.CYMBAL_EDGE);
                        break;
                }
                percussion = stickLocation;
                break;
            case "other-percussion":
                OtherPercussion otherPercussion = new OtherPercussion();
                otherPercussion.setValue(XmlUtil.getElementText(element));
                percussion = otherPercussion;
                break;
        }

        if (percussion == null) return null;

        percussion.setPrintStyleAlign(FormattingFactory.newPrintStyleAlign(element));
        percussion.setEnclosure(FormattingFactory.newEnclosureShape(element));

        return percussion;
    }

    private static TipDirection newTipDirection(Element element) {
        if (element == null) return null;

        String tip = element.getAttribute("tip");
        if (StringUtil.isEmpty(tip)) return null;
        switch (tip) {
            case "up":
                return TipDirection.UP;
            case "down":
                return TipDirection.DOWN;
            case "left":
                return TipDirection.LEFT;
            case "right":
                return TipDirection.RIGHT;
            case "northwest":
                return TipDirection.NORTHWEST;
            case "northeast":
                return TipDirection.NORTHEAST;
            case "southeast":
                return TipDirection.SOUTHEAST;
            case "southwest":
                return TipDirection.SOUTHWEST;
            default:
                return null;
        }
    }

    public static Sound newSound(Element element) {
        if (element == null) return null;

        Sound sound = new Sound();
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
        image.setPosition(PlacementFactory.newPosition(element));
        image.setHalign(PlacementUtil.getLocation(element.getAttribute("halign")));
        image.setValignImage(PlacementUtil.getLocation(element.getAttribute("valign")));

        return image;
    }
}
