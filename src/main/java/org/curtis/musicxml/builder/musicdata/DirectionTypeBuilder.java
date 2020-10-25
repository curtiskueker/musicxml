package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.attributes.Image;
import org.curtis.musicxml.builder.DisplayBuilder;
import org.curtis.musicxml.builder.FormattingBuilder;
import org.curtis.musicxml.builder.BuilderUtil;
import org.curtis.musicxml.direction.directiontype.Accord;
import org.curtis.musicxml.direction.directiontype.AccordionRegistration;
import org.curtis.musicxml.direction.directiontype.Bracket;
import org.curtis.musicxml.direction.directiontype.Coda;
import org.curtis.musicxml.direction.directiontype.Damp;
import org.curtis.musicxml.direction.directiontype.DampAll;
import org.curtis.musicxml.direction.directiontype.Dashes;
import org.curtis.musicxml.direction.directiontype.DirectionType;
import org.curtis.musicxml.direction.directiontype.Dynamics;
import org.curtis.musicxml.direction.directiontype.Eyeglasses;
import org.curtis.musicxml.direction.directiontype.HarpPedals;
import org.curtis.musicxml.direction.directiontype.OctaveShift;
import org.curtis.musicxml.direction.directiontype.OtherDirection;
import org.curtis.musicxml.direction.directiontype.Pedal;
import org.curtis.musicxml.direction.directiontype.PedalTuning;
import org.curtis.musicxml.direction.directiontype.PrincipalVoice;
import org.curtis.musicxml.direction.directiontype.Rehearsal;
import org.curtis.musicxml.direction.directiontype.Scordatura;
import org.curtis.musicxml.direction.directiontype.Segno;
import org.curtis.musicxml.direction.directiontype.StaffDivide;
import org.curtis.musicxml.direction.directiontype.StringMute;
import org.curtis.musicxml.direction.directiontype.Symbol;
import org.curtis.musicxml.direction.directiontype.Wedge;
import org.curtis.musicxml.direction.directiontype.Words;
import org.curtis.musicxml.direction.directiontype.metronome.BeatMetronome;
import org.curtis.musicxml.direction.directiontype.metronome.BeatUnit;
import org.curtis.musicxml.direction.directiontype.metronome.Metronome;
import org.curtis.musicxml.direction.directiontype.metronome.MetronomeBeam;
import org.curtis.musicxml.direction.directiontype.metronome.MetronomeMark;
import org.curtis.musicxml.direction.directiontype.metronome.MetronomeNote;
import org.curtis.musicxml.direction.directiontype.metronome.MetronomeTuplet;
import org.curtis.musicxml.direction.directiontype.metronome.NoteMetronome;
import org.curtis.musicxml.direction.directiontype.metronome.PerMinute;
import org.curtis.musicxml.direction.directiontype.percussion.Beater;
import org.curtis.musicxml.direction.directiontype.percussion.Effect;
import org.curtis.musicxml.direction.directiontype.percussion.Glass;
import org.curtis.musicxml.direction.directiontype.percussion.Membrane;
import org.curtis.musicxml.direction.directiontype.percussion.Metal;
import org.curtis.musicxml.direction.directiontype.percussion.OtherPercussion;
import org.curtis.musicxml.direction.directiontype.percussion.Percussion;
import org.curtis.musicxml.direction.directiontype.percussion.Pitched;
import org.curtis.musicxml.direction.directiontype.percussion.Stick;
import org.curtis.musicxml.direction.directiontype.percussion.StickLocation;
import org.curtis.musicxml.direction.directiontype.percussion.Timpani;
import org.curtis.musicxml.direction.directiontype.percussion.Wood;
import org.curtis.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectionTypeBuilder extends MusicDataBuilder {
    private DirectionType directionType;

    public DirectionTypeBuilder(DirectionType directionType) {
        this.directionType = directionType;
    }

    public StringBuilder build() {
        if (directionType == null) return stringBuilder;

        if (directionType instanceof Rehearsal) buildRehearsal((Rehearsal)directionType);
        else if (directionType instanceof Segno) buildSegno((Segno)directionType);
        else if (directionType instanceof Words) buildWords((Words)directionType);
        else if (directionType instanceof Symbol) buildSymbol((Symbol)directionType);
        else if (directionType instanceof Coda) buildCoda((Coda) directionType);
        else if (directionType instanceof Wedge) buildWedge((Wedge) directionType);
        else if (directionType instanceof Dynamics) buildDynamics((Dynamics) directionType);
        else if (directionType instanceof Dashes) buildDashes((Dashes) directionType);
        else if (directionType instanceof Bracket) buildBracket((Bracket) directionType);
        else if (directionType instanceof Pedal) buildPedal((Pedal) directionType);
        else if (directionType instanceof Metronome) buildMetronome((Metronome) directionType);
        else if (directionType instanceof OctaveShift) buildOctaveShift((OctaveShift) directionType);
        else if (directionType instanceof HarpPedals) buildHarpPedals((HarpPedals) directionType);
        else if (directionType instanceof Damp) buildDamp((Damp) directionType);
        else if (directionType instanceof DampAll) buildDampAll((DampAll) directionType);
        else if (directionType instanceof Eyeglasses) buildEyeglasses((Eyeglasses) directionType);
        else if (directionType instanceof StringMute) buildStringMute((StringMute) directionType);
        else if (directionType instanceof Scordatura) buildScordatura((Scordatura) directionType);
        else if (directionType instanceof Image) buildImage("image", (Image) directionType);
        else if (directionType instanceof PrincipalVoice) buildPrincipalVoice((PrincipalVoice) directionType);
        else if (directionType instanceof AccordionRegistration) buildAccordionRegistration((AccordionRegistration) directionType);
        else if (directionType instanceof Percussion) buildPercussion((Percussion) directionType);
        else if (directionType instanceof StaffDivide) buildStaffDivide((StaffDivide) directionType);
        else if (directionType instanceof OtherDirection) buildOtherDirection((OtherDirection) directionType);

        return stringBuilder;
    }

    private void buildRehearsal(Rehearsal rehearsal) {
        buildFormattedDisplayElement("rehearsal", rehearsal.getElementId(), rehearsal.getDisplay(), rehearsal.getTextFormat());
    }

    private void buildWords(Words words) {
        buildFormattedDisplayElement("words", words.getElementId(), words.getDisplay(), words.getTextFormat());
    }

    private void buildSymbol(Symbol symbol) {
        buildFormattedDisplayElement("symbol", symbol.getElementId(), symbol.getDisplay(), symbol.getTextFormat());
    }

    private void buildWedge(Wedge wedge) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("id", wedge.getElementId());
        attributes.put("type", BuilderUtil.enumValue(wedge.getType()));
        attributes.put("number", BuilderUtil.stringValue(wedge.getNumber()));
        attributes.put("spread", BuilderUtil.stringValue(wedge.getSpread()));
        attributes.put("niente", BuilderUtil.yesOrNo(wedge.getNiente()));
        attributes.put("line-type", BuilderUtil.enumValue(wedge.getLineType()));
        attributes.putAll(FormattingBuilder.buildDashedFormatting(wedge.getDashedFormatting()));
        attributes.putAll(DisplayBuilder.buildDisplay(wedge.getDisplay()));
        buildElementWithAttributes("wedge", attributes);
    }

    private void buildDashes(Dashes dashes) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("id", dashes.getElementId());
        attributes.put("type", BuilderUtil.enumValue(dashes.getType()));
        attributes.put("number", BuilderUtil.stringValue(dashes.getNumber()));
        attributes.putAll(FormattingBuilder.buildDashedFormatting(dashes.getDashedFormatting()));
        attributes.putAll(DisplayBuilder.buildDisplay(dashes.getDisplay()));
        buildElementWithAttributes("dashes", attributes);
    }

    private void buildBracket(Bracket bracket) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("id", bracket.getElementId());
        attributes.put("type", BuilderUtil.enumValue(bracket.getType()));
        attributes.put("number", BuilderUtil.stringValue(bracket.getNumber()));
        attributes.put("line-end", BuilderUtil.enumValue(bracket.getLineEnd()));
        attributes.put("end-length", BuilderUtil.stringValue(bracket.getEndLength()));
        attributes.put("line-type", BuilderUtil.enumValue(bracket.getLineType()));
        attributes.putAll(FormattingBuilder.buildDashedFormatting(bracket.getDashedFormatting()));
        attributes.putAll(DisplayBuilder.buildDisplay(bracket.getDisplay()));
        buildElementWithAttributes("bracket", attributes);
    }

    private void buildPedal(Pedal pedal) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("id", pedal.getElementId());
        attributes.put("type", BuilderUtil.enumValue(pedal.getType()));
        attributes.put("number", BuilderUtil.stringValue(pedal.getNumber()));
        attributes.put("line", BuilderUtil.yesOrNo(pedal.getLine()));
        attributes.put("sign", BuilderUtil.yesOrNo(pedal.getSign()));
        attributes.putAll(DisplayBuilder.buildDisplay(pedal.getDisplay()));
        attributes.put("abbreviated", BuilderUtil.yesOrNo(pedal.getAbbreviated()));
        buildElementWithAttributes("pedal", attributes);
    }

    private void buildMetronome(Metronome metronome) {
        buildOpenElement("metronome");
        buildAttribute("id", metronome.getElementId());
        buildAttributes(DisplayBuilder.buildDisplay(metronome.getDisplay()));
        buildAttribute("justify", metronome.getJustify());
        buildAttribute("parentheses", metronome.getParentheses());
        buildCloseElement();
        if (metronome instanceof BeatMetronome) buildBeatMetronome((BeatMetronome)metronome);
        else if (metronome instanceof NoteMetronome) buildNoteMetronome((NoteMetronome)metronome);
        buildEndElement("metronome");
    }

    private void buildBeatMetronome(BeatMetronome beatMetronome) {
        List<MetronomeMark> metronomeMarks = new ArrayList<>();
        metronomeMarks.add(beatMetronome.getMetronomeMark1());
        metronomeMarks.add(beatMetronome.getMetronomeMark2());
        for (MetronomeMark metronomeMark : metronomeMarks) {
            if (metronomeMark instanceof BeatUnit) {
                BeatUnit beatUnit = (BeatUnit)metronomeMark;
                buildBeatUnit(beatUnit);
            } else if (metronomeMark instanceof PerMinute) {
                PerMinute perMinute = (PerMinute)metronomeMark;
                buildElementWithValueAndAttributes("per-minute", perMinute.getValue(), DisplayBuilder.buildDisplay(perMinute.getDisplay()));
            }
        }
    }

    private void buildBeatUnit(BeatUnit beatUnit) {
        buildElementWithValue("beat-unit", BuilderUtil.noteTypeValue(beatUnit.getValue()));
        for (int beatUnitDots = 1; beatUnitDots <= beatUnit.getBeatUnitDots(); beatUnitDots++) buildElement("beat-unit-dot");
        for (BeatUnit beatUnitTied : beatUnit.getBeatUnitTieds()) {
            buildStartElement("beat-unit-tied");
            buildBeatUnit(beatUnitTied);
            buildEndElement("beat-unit-tied");
        }
    }

    private void buildNoteMetronome(NoteMetronome noteMetronome) {
        if (noteMetronome.getMetronomeArrows()) buildElement("metronome-arrows");
        noteMetronome.getMetronomeNotes1().forEach(this::buildMetronomeNote);
        buildElementWithValue("metronome-relation", noteMetronome.getMetronomeRelation());
        noteMetronome.getMetronomeNotes2().forEach(this::buildMetronomeNote);
    }

    private void buildMetronomeNote(MetronomeNote metronomeNote) {
        buildElementWithValue("metronome-type", BuilderUtil.noteTypeValue(metronomeNote.getMetronomeType()));
        for (int metronomeDots = 1; metronomeDots <= metronomeNote.getMetronomeDots(); metronomeDots++) buildElement("metronome-dot");
        for (MetronomeBeam metronomeBeam : metronomeNote.getMetronomeBeams()) {
            buildElementWithValueAndAttribute("metronome-beam", NoteBuilder.buildBeamType(metronomeBeam.getValue()), "number", metronomeBeam.getNumber());
        }
        buildElementWithAttribute("metronome-tied", "type", BuilderUtil.enumValue(metronomeNote.getMetronomeTied()));
        MetronomeTuplet metronomeTuplet = metronomeNote.getMetronomeTuplet();
        if (metronomeTuplet != null) {
            buildOpenElement("metronome-tuplet");
            buildAttribute("type", BuilderUtil.enumValue(metronomeTuplet.getType()));
            buildAttribute("bracket", metronomeTuplet.getBracket());
            buildAttribute("show-number", metronomeTuplet.getShowNumber());
            buildCloseElement();
            buildTimeModification(metronomeTuplet.getTimeModification());
            buildStartElement("metronome-tuplet");
        }
    }

    private void buildOctaveShift(OctaveShift octaveShift) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("id", octaveShift.getElementId());
        attributes.put("type", BuilderUtil.enumValue(octaveShift.getType()));
        attributes.put("number", BuilderUtil.stringValue(octaveShift.getNumber()));
        attributes.put("size", BuilderUtil.stringValue(octaveShift.getSize()));
        attributes.putAll(FormattingBuilder.buildDashedFormatting(octaveShift.getDashedFormatting()));
        attributes.putAll(DisplayBuilder.buildDisplay(octaveShift.getDisplay()));
        buildElementWithAttributes("octave-shift", attributes);
    }

    private void buildHarpPedals(HarpPedals harpPedals) {
        buildOpenElement("harp-pedals");
        buildAttribute("id", harpPedals.getElementId());
        buildAttributes(DisplayBuilder.buildDisplay(harpPedals.getDisplay()));
        buildCloseElement();
        for (PedalTuning pedalTuning : harpPedals.getPedalTunings()) {
            buildStartElement("pedal-tuning");
            buildElementWithValue("pedal-step", BuilderUtil.enumValue(pedalTuning.getPedalStep()).toUpperCase());
            buildElementWithValue("pedal-alter", pedalTuning.getPedalAlter());
            buildEndElement("pedal-tuning");
        }
        buildEndElement("harp-pedals");
    }

    private void buildDamp(Damp damp) {
        Map<String, String> attributes = new HashMap<>(DisplayBuilder.buildDisplay(damp.getDisplay()));
        attributes.put("id", damp.getElementId());
        buildElementWithOptionalAttributes("damp", attributes);
    }

    private void buildDampAll(DampAll dampAll) {
        Map<String, String> attributes = new HashMap<>(DisplayBuilder.buildDisplay(dampAll.getDisplay()));
        attributes.put("id", dampAll.getElementId());
        buildElementWithOptionalAttributes("damp-all", attributes);
    }

    private void buildEyeglasses(Eyeglasses eyeglasses) {
        Map<String, String> attributes = new HashMap<>(DisplayBuilder.buildDisplay(eyeglasses.getDisplay()));
        attributes.put("id", eyeglasses.getElementId());
        buildElementWithOptionalAttributes("eyeglasses", attributes);
    }

    private void buildStringMute(StringMute stringMute) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("id", stringMute.getElementId());
        attributes.put("type", BuilderUtil.enumValue(stringMute.getType()));
        attributes.putAll(DisplayBuilder.buildDisplay(stringMute.getDisplay()));
        buildElementWithAttributes("string-mute", attributes);
    }

    private void buildScordatura(Scordatura scordatura) {
        buildOpenElement("scordatura");
        buildAttribute("id", scordatura.getElementId());
        buildCloseElement();
        for (Accord accord : scordatura.getAccords()) {
            buildOpenElement("accord");
            buildAttribute("string", accord.getString());
            buildCloseElement();
            buildTuning(accord.getTuning());
            buildEndElement("accord");
        }
        buildEndElement("scordatura");
    }

    private void buildPrincipalVoice(PrincipalVoice principalVoice) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("id", principalVoice.getElementId());
        attributes.put("type", BuilderUtil.enumValue(principalVoice.getType()));
        String symbol = BuilderUtil.enumValue(principalVoice.getSymbol());
        symbol = symbol.replace("hauptstimme", "Hauptstimme");
        symbol = symbol.replace("nebenstimme", "Nebenstimme");
        attributes.put("symbol", symbol);
        attributes.putAll(DisplayBuilder.buildDisplay(principalVoice.getDisplay()));
        buildElementWithValueAndAttributes("principal-voice", principalVoice.getValue(), attributes);
    }

    private void buildAccordionRegistration(AccordionRegistration accordionRegistration) {
        buildOpenElement("accordion-registration");
        buildAttribute("id", accordionRegistration.getElementId());
        buildAttributes(DisplayBuilder.buildDisplay(accordionRegistration.getDisplay()));
        buildCloseElement();
        if (accordionRegistration.getAccordionHigh()) buildElement("accordion-high");
        buildElementWithValue("accordion-middle", accordionRegistration.getAccordionMiddle());
        if (accordionRegistration.getAccordionLow()) buildElement("accordion-low");
        buildEndElement("accordion-registration");
    }

    private void buildPercussion(Percussion percussion) {
        buildOpenElement("percussion");
        buildAttribute("id", percussion.getElementId());
        buildAttributes(DisplayBuilder.buildDisplay(percussion.getDisplay()));
        buildAttribute("enclosure", percussion.getEnclosure());
        buildCloseElement();
        if (percussion instanceof Glass) buildGlass((Glass)percussion);
        else if (percussion instanceof Metal) buildMetal((Metal)percussion);
        else if (percussion instanceof Wood) buildWood((Wood)percussion);
        else if (percussion instanceof Pitched) buildPitched((Pitched)percussion);
        else if (percussion instanceof Membrane) buildMembrane((Membrane)percussion);
        else if (percussion instanceof Effect) buildEffect((Effect)percussion);
        else if (percussion instanceof Timpani) buildTimpani();
        else if (percussion instanceof Beater) buildBeater((Beater)percussion);
        else if (percussion instanceof Stick) buildStick((Stick)percussion);
        else if (percussion instanceof StickLocation) buildStickLocation((StickLocation)percussion);
        else if (percussion instanceof OtherPercussion) buildOtherPercussion((OtherPercussion)percussion);
        buildEndElement("percussion");
    }

    private void buildGlass(Glass glass) {
        buildElementWithValueAndAttribute("glass", BuilderUtil.enumValueWithSpaces(glass.getValue()), "smufl", glass.getSmufl());
    }

    private void buildMetal(Metal metal) {
        String type = BuilderUtil.enumValueWithSpaces(metal.getValue());
        type = type.replace("chinese cymbal", "Chinese cymbal");
        type = type.replace("high hat", "high-hat");
        type = type.replace("high hat cymbals", "high-hat cymbals");
        type = type.replace("vietnamese hat", "Vietnamese hat");
        buildElementWithValue("metal", type);
    }

    private void buildWood(Wood wood) {
        String woodType = BuilderUtil.enumValueWithSpaces(wood.getValue());
        woodType = woodType.replace("reco reco", "reco-reco");
        buildElementWithValue("wood", woodType);
    }

    private void buildPitched(Pitched pitched) {
        buildElementWithValueAndAttribute("pitched", BuilderUtil.enumValueWithSpaces(pitched.getValue()), "smufl", pitched.getSmufl());
    }

    private void buildMembrane(Membrane membrane) {
        String attributeValue = BuilderUtil.enumValueWithSpaces(membrane.getValue());
        if (StringUtil.isNotEmpty(attributeValue)) {
            attributeValue = attributeValue.replace("chinese tomtom", "Chinese tomtom");
            attributeValue = attributeValue.replace("indo american tomtom", "Indo-American tomtom");
            attributeValue = attributeValue.replace("japanese tomtom", "Japanese tomtom");
        }

        buildElementWithValue("membrane", attributeValue);
    }

    private void buildEffect(Effect effect) {
        buildElementWithValue("effect", BuilderUtil.enumValueWithSpaces(effect.getValue()));
    }

    private void buildTimpani() {
        buildElement("timpani");
    }

    private void buildBeater(Beater beater) {
        buildElementWithValueAndAttribute("beater", beater.getValue(), "tip", beater.getTip());
    }

    private void buildStick(Stick stick) {
        buildOpenElement("stick");
        buildAttribute("tip", stick.getTip());
        buildCloseElement();
        buildElementWithValue("stick-type", BuilderUtil.enumValueWithSpaces(stick.getStickType()));
        buildElementWithValue("stick-material", stick.getStickMaterial());
        buildEndElement("stick");
    }

    private void buildStickLocation(StickLocation stickLocation) {
        buildElementWithValue("stick-location", BuilderUtil.enumValueWithSpaces(stickLocation.getValue()));
    }

    private void buildOtherPercussion(OtherPercussion otherPercussion) {
        buildElementWithValueAndAttribute("other-percussion", otherPercussion.getValue(), "smufl", otherPercussion.getSmufl());
    }

    private void buildStaffDivide(StaffDivide staffDivide) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("id", staffDivide.getElementId());
        attributes.put("type", BuilderUtil.enumValue(staffDivide.getType()));
        attributes.putAll(DisplayBuilder.buildDisplay(staffDivide.getDisplay()));
        buildElementWithAttributes("staff-divide", attributes);
    }

    private void buildOtherDirection(OtherDirection otherDirection) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("id", otherDirection.getElementId());
        attributes.put("print-object", BuilderUtil.yesOrNo(otherDirection.getPrintObject()));
        attributes.putAll(DisplayBuilder.buildDisplay(otherDirection.getDisplay()));
        attributes.put("smufl", otherDirection.getSmufl());
        buildElementWithValueAndAttributes("other-direction", otherDirection.getValue(), attributes);
    }
}
