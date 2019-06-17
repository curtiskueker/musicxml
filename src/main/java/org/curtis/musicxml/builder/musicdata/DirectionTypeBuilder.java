package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.attributes.Image;
import org.curtis.musicxml.builder.FormattingBuilder;
import org.curtis.musicxml.builder.PlacementBuilder;
import org.curtis.musicxml.builder.util.BuilderUtil;
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
import org.curtis.musicxml.direction.directiontype.StringMute;
import org.curtis.musicxml.direction.directiontype.Wedge;
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

import java.util.HashMap;
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
        else if (directionType instanceof Eyeglasses) buildEyglasses((Eyeglasses) directionType);
        else if (directionType instanceof StringMute) buildStringMute((StringMute) directionType);
        else if (directionType instanceof Scordatura) buildScordatura((Scordatura) directionType);
        else if (directionType instanceof Image) buildImage((Image) directionType);
        else if (directionType instanceof PrincipalVoice) buildPrincipalVoice((PrincipalVoice) directionType);
        else if (directionType instanceof AccordionRegistration) buildAccordionRegistration((AccordionRegistration) directionType);
        else if (directionType instanceof Percussion) buildPercussion((Percussion) directionType);
        else if (directionType instanceof OtherDirection) buildOtherDirection((OtherDirection) directionType);

        return stringBuilder;
    }

    private void buildRehearsal(Rehearsal rehearsal) {
        if (rehearsal.getFormattedText() != null) buildFormattedText("rehearsal", rehearsal.getFormattedText());
    }

    private void buildSegno(Segno segno) {
        buildElementWithOptionalAttributes("segno", FormattingBuilder.buildPrintStyleAlign(segno.getPrintStyleAlign()));
    }

    private void buildWords(Words words) {
        if (words.getFormattedText() != null) buildFormattedText("words", words.getFormattedText());
    }

    private void buildCoda(Coda coda) {
        buildElementWithOptionalAttributes("coda", FormattingBuilder.buildPrintStyleAlign(coda.getPrintStyleAlign()));
    }

    private void buildWedge(Wedge wedge) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("type", BuilderUtil.enumValue(wedge.getType()));
        attributes.put("number", BuilderUtil.stringValue(wedge.getNumber()));
        attributes.put("spread", BuilderUtil.stringValue(wedge.getSpread()));
        attributes.put("niente", BuilderUtil.yesOrNo(wedge.getNiente()));
        attributes.put("line-type", BuilderUtil.enumValue(wedge.getLineType()));
        attributes.putAll(FormattingBuilder.buildDashedFormatting(wedge.getDashedFormatting()));
        attributes.putAll(PlacementBuilder.buildPosition(wedge.getPosition()));
        attributes.put("color", wedge.getColor());
        buildElementWithAttributes("wedge", attributes);
    }

    private void buildDashes(Dashes dashes) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("type", BuilderUtil.enumValue(dashes.getType()));
        attributes.put("number", BuilderUtil.stringValue(dashes.getNumber()));
        attributes.putAll(FormattingBuilder.buildDashedFormatting(dashes.getDashedFormatting()));
        attributes.putAll(PlacementBuilder.buildPosition(dashes.getPosition()));
        attributes.put("color", dashes.getColor());
        buildElementWithAttributes("dashes", attributes);
    }

    private void buildBracket(Bracket bracket) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("type", BuilderUtil.enumValue(bracket.getType()));
        attributes.put("number", BuilderUtil.stringValue(bracket.getNumber()));
        attributes.put("line-end", BuilderUtil.enumValue(bracket.getLineEnd()));
        attributes.put("end-length", BuilderUtil.stringValue(bracket.getEndLength()));
        attributes.put("line-type", BuilderUtil.enumValue(bracket.getLineType()));
        attributes.putAll(FormattingBuilder.buildDashedFormatting(bracket.getDashedFormatting()));
        attributes.putAll(PlacementBuilder.buildPosition(bracket.getPosition()));
        attributes.put("color", bracket.getColor());
        buildElementWithAttributes("bracket", attributes);
    }

    private void buildPedal(Pedal pedal) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("type", BuilderUtil.enumValue(pedal.getType()));
        attributes.put("line", BuilderUtil.yesOrNo(pedal.getLine()));
        attributes.put("sign", BuilderUtil.yesOrNo(pedal.getSign()));
        attributes.putAll(FormattingBuilder.buildPrintStyleAlign(pedal.getPrintStyleAlign()));
        buildElementWithAttributes("pedal", attributes);
    }

    private void buildMetronome(Metronome metronome) {
        buildOpenElement("metronome");
        buildAttributes(FormattingBuilder.buildPrintStyleAlign(metronome.getPrintStyleAlign()));
        buildAttribute("justify", metronome.getJustify());
        buildAttribute("parentheses", metronome.getParentheses());
        buildCloseElement();
        if (metronome instanceof BeatMetronome) buildBeatMetronome((BeatMetronome)metronome);
        else if (metronome instanceof NoteMetronome) buildNoteMetronome((NoteMetronome)metronome);
        buildEndElement("metronome");
    }

    private void buildBeatMetronome(BeatMetronome beatMetronome) {
        buildBeatUnitWithDots(beatMetronome.getBeatUnit1());
        PerMinute perMinute = beatMetronome.getPerMinute();
        if (perMinute != null) buildElementWithValueAndAttributes("per-minute", perMinute.getPerMinute(), FormattingBuilder.buildFont(perMinute.getFont()));
        BeatUnit beatUnit2 = beatMetronome.getBeatUnit2();
        if (beatUnit2 != null) buildBeatUnitWithDots(beatUnit2);
    }

    private void buildBeatUnitWithDots(BeatUnit beatUnit) {
        buildElementWithValue("beat-unit", BuilderUtil.noteTypeValue(beatUnit.getBeatUnit()));
        for (int beatUnitDots = 1; beatUnitDots <= beatUnit.getBeatUnitDots(); beatUnitDots++) buildElement("beat-unit-dot");
    }

    private void buildNoteMetronome(NoteMetronome noteMetronome) {
        noteMetronome.getMetronomeNotes1().forEach(metronomeNote -> buildMetronomeNote(metronomeNote));
        buildElementWithValue("metronome-relation", noteMetronome.getMetronomeRelation());
        noteMetronome.getMetronomeNotes2().forEach(metronomeNote -> buildMetronomeNote(metronomeNote));
    }

    private void buildMetronomeNote(MetronomeNote metronomeNote) {
        buildElementWithValue("metronome-type", BuilderUtil.noteTypeValue(metronomeNote.getMetronomeType()));
        for (int metronomeDots = 1; metronomeDots <= metronomeNote.getMetronomeDots(); metronomeDots++) buildElement("metronome-dot");
        for (MetronomeBeam metronomeBeam : metronomeNote.getMetronomeBeams()) {
            buildElementWithValueAndAttribute("metronome-beam", NoteBuilder.buildBeamType(metronomeBeam.getBeamType()), "number", metronomeBeam.getNumber());
        }
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
        attributes.put("type", BuilderUtil.enumValue(octaveShift.getType()));
        attributes.put("number", BuilderUtil.stringValue(octaveShift.getNumber()));
        attributes.put("size", BuilderUtil.stringValue(octaveShift.getSize()));
        attributes.putAll(FormattingBuilder.buildDashedFormatting(octaveShift.getDashedFormatting()));
        attributes.putAll(FormattingBuilder.buildPrintStyle(octaveShift.getPrintStyle()));
        buildElementWithAttributes("octave-shift", attributes);
    }

    private void buildHarpPedals(HarpPedals harpPedals) {
        buildOpenElement("harp-pedals");
        buildAttributes(FormattingBuilder.buildPrintStyleAlign(harpPedals.getPrintStyleAlign()));
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
        buildElementWithOptionalAttributes("damp", FormattingBuilder.buildPrintStyleAlign(damp.getPrintStyleAlign()));
    }

    private void buildDampAll(DampAll dampAll) {
        buildElementWithOptionalAttributes("damp-all", FormattingBuilder.buildPrintStyleAlign(dampAll.getPrintStyleAlign()));
    }

    private void buildEyglasses(Eyeglasses eyeglasses) {
        buildElementWithOptionalAttributes("eyeglasses", FormattingBuilder.buildPrintStyleAlign(eyeglasses.getPrintStyleAlign()));
    }

    private void buildStringMute(StringMute stringMute) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("type", BuilderUtil.enumValue(stringMute.getType()));
        attributes.putAll(FormattingBuilder.buildPrintStyleAlign(stringMute.getPrintStyleAlign()));
        buildElementWithAttributes("string-mute", attributes);
    }

    private void buildScordatura(Scordatura scordatura) {
        buildStartElement("scordatura");
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
        attributes.put("type", BuilderUtil.enumValue(principalVoice.getType()));
        String symbol = BuilderUtil.enumValue(principalVoice.getSymbol());
        symbol = symbol.replace("hauptstimme", "Hauptstimme");
        symbol = symbol.replace("nebenstimme", "Nebenstimme");
        attributes.put("symbol", symbol);
        attributes.putAll(FormattingBuilder.buildPrintStyleAlign(principalVoice.getPrintStyleAlign()));
        buildElementWithValueAndAttributes("principal-voice", principalVoice.getPrincipalVoice(), attributes);
    }

    private void buildAccordionRegistration(AccordionRegistration accordionRegistration) {
        buildOpenElement("accordion-registration");
        buildAttributes(FormattingBuilder.buildPrintStyleAlign(accordionRegistration.getPrintStyleAlign()));
        buildCloseElement();
        if (accordionRegistration.getAccordionHigh()) buildElement("accordion-high");
        buildElementWithValue("accordion-middle", accordionRegistration.getAccordionMiddle());
        if (accordionRegistration.getAccordionLow()) buildElement("accordion-low");
        buildEndElement("accordion-registration");
    }

    private void buildPercussion(Percussion percussion) {
        buildOpenElement("percussion");
        buildAttributes(FormattingBuilder.buildPrintStyleAlign(percussion.getPrintStyleAlign()));
        buildAttribute("enclosure", percussion.getEnclosure());
        buildCloseElement();
        if (percussion instanceof Glass) buildGlass((Glass)percussion);
        else if (percussion instanceof Metal) buildMetal((Metal)percussion);
        else if (percussion instanceof Wood) buildWood((Wood)percussion);
        else if (percussion instanceof Pitched) buildPitched((Pitched)percussion);
        else if (percussion instanceof Membrane) buildMembrane((Membrane)percussion);
        else if (percussion instanceof Effect) buildEffect((Effect)percussion);
        else if (percussion instanceof Timpani) buildTimpani((Timpani)percussion);
        else if (percussion instanceof Beater) buildBeater((Beater)percussion);
        else if (percussion instanceof Stick) buildStick((Stick)percussion);
        else if (percussion instanceof StickLocation) buildStickLocation((StickLocation)percussion);
        else if (percussion instanceof OtherPercussion) buildOtherPercussion((OtherPercussion)percussion);
        buildEndElement("percussion");
    }

    private void buildGlass(Glass glass) {
        buildElementWithValue("glass", BuilderUtil.enumValueWithSpaces(glass.getType()));
    }

    private void buildMetal(Metal metal) {
        String type = BuilderUtil.enumValue(metal.getType());
        type = type.replace("bell-plate", "bell plate");
        type = type.replace("brake-drum", "brake drum");
        type = type.replace("chinese-cymbal", "Chinese cymbal");
        type = type.replace("crash-cymbals", "crash cymbals");
        type = type.replace("cymbal-tongs", "cymbal tongs");
        type = type.replace("domed-gong", "domed gong");
        type = type.replace("finger-cymbals", "finger cymbals");
        type = type.replace("high-hat-cymbals", "high-hat cymbals");
        type = type.replace("sizzle-cymbal", "sizzle cymbal");
        type = type.replace("sleigh-bells", "sleigh bells");
        type = type.replace("suspended-cymbal", "suspended cymbal");
        type = type.replace("tam-tam", "tam tam");
        type = type.replace("vietnamese-hat", "Vietnamese hat");
        buildElementWithValue("metal", type);
    }

    private void buildWood(Wood wood) {
        buildElementWithValue("wood", BuilderUtil.enumValueWithSpaces(wood.getType()));
    }

    private void buildPitched(Pitched pitched) {
        buildElementWithValue("pitched", BuilderUtil.enumValueWithSpaces(pitched.getType()));
    }

    private void buildMembrane(Membrane membrane) {
        buildElementWithValue("membrane", BuilderUtil.enumValueWithSpaces(membrane.getType()));
    }

    private void buildEffect(Effect effect) {
        buildElementWithValue("effect", BuilderUtil.enumValueWithSpaces(effect.getType()));
    }

    private void buildTimpani(Timpani timpani) {
        buildElement("timpani");
    }

    private void buildBeater(Beater beater) {
        buildElementWithValueAndAttribute("beater", beater.getBeaterValue(), "tip", beater.getTip());
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
        buildElementWithValue("stick-location", BuilderUtil.enumValueWithSpaces(stickLocation.getType()));
    }

    private void buildOtherPercussion(OtherPercussion otherPercussion) {
        buildElementWithValue("other-percussion", otherPercussion.getValue());
    }

    private void buildOtherDirection(OtherDirection otherDirection) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("print-object", BuilderUtil.yesOrNo(otherDirection.getPrintObject()));
        attributes.putAll(FormattingBuilder.buildPrintStyleAlign(otherDirection.getPrintStyleAlign()));
        buildElementWithValueAndAttributes("other-direction", otherDirection.getValue(), attributes);
    }
}
