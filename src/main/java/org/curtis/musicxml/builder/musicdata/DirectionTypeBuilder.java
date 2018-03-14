package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.attributes.Image;
import org.curtis.musicxml.builder.BaseBuilder;
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
import org.curtis.musicxml.direction.directiontype.metronome.Metronome;
import org.curtis.musicxml.direction.directiontype.metronome.MetronomeBeam;
import org.curtis.musicxml.direction.directiontype.metronome.MetronomeNote;
import org.curtis.musicxml.direction.directiontype.metronome.MetronomeTuplet;
import org.curtis.musicxml.direction.directiontype.metronome.NoteMetronome;
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

public class DirectionTypeBuilder extends BaseBuilder {
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
        buildElement("rehearsal");
    }

    private void buildSegno(Segno segno) {
        buildElement("segno");
    }

    private void buildWords(Words words) {
        buildElement("words");
    }

    private void buildCoda(Coda coda) {
        buildElement("coda");
    }

    private void buildWedge(Wedge wedge) {
        buildElementWithAttribute("wedge", "type", BuilderUtil.enumValue(wedge.getType()));
    }

    private void buildDynamics(Dynamics dynamics) {
        buildElement("dynamics");
    }

    private void buildDashes(Dashes dashes) {
        buildElement("dashes");
    }

    private void buildBracket(Bracket bracket) {
        buildElementWithAttribute("bracket", "line-end", BuilderUtil.enumValue(bracket.getLineEnd()));
    }

    private void buildPedal(Pedal pedal) {
        buildElementWithAttribute("pedal", "type", BuilderUtil.enumValue(pedal.getType()));
    }

    private void buildMetronome(Metronome metronome) {
        appendLine("<metronome>");
        if (metronome instanceof BeatMetronome) buildBeatMetronome((BeatMetronome)metronome);
        else if (metronome instanceof NoteMetronome) buildNoteMetronome((NoteMetronome)metronome);
        appendLine("</metronome>");
    }

    private void buildBeatMetronome(BeatMetronome beatMetronome) {
        buildElementWithValue("beat-unit", BuilderUtil.noteTypeValue(beatMetronome.getBeatUnit1().getBeatUnit()));
        buildElementWithValue("per-minute", beatMetronome.getPerMinute().getPerMinute());
        buildElementWithValue("beat-unit", BuilderUtil.noteTypeValue(beatMetronome.getBeatUnit2().getBeatUnit()));
    }

    private void buildNoteMetronome(NoteMetronome noteMetronome) {
        noteMetronome.getMetronomeNotes1().forEach(metronomeNote -> buildMetronomeNote(metronomeNote));
        buildElementWithValue("metronome-relation", noteMetronome.getMetronomeRelation());
        noteMetronome.getMetronomeNotes2().forEach(metronomeNote -> buildMetronomeNote(metronomeNote));
    }

    private void buildMetronomeNote(MetronomeNote metronomeNote) {
        buildElementWithValue("metronome-type", BuilderUtil.noteTypeValue(metronomeNote.getMetronomeType()));
        for (MetronomeBeam metronomeBeam : metronomeNote.getMetronomeBeams()) {
            buildElementWithValueAndAttribute("metronome-beam", NoteBuilder.buildBeamType(metronomeBeam.getBeamType()), "number", metronomeBeam.getNumber());
        }
        MetronomeTuplet metronomeTuplet = metronomeNote.getMetronomeTuplet();
        if (metronomeTuplet != null) {
            appendLine("<metronome-tuplet>");
            NoteBuilder noteBuilder = new NoteBuilder();
            append(noteBuilder.buildTimeModification(metronomeTuplet.getTimeModification()).toString());
            appendLine("<metronome-tuplet>");
        }
    }

    private void buildOctaveShift(OctaveShift octaveShift) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("type", BuilderUtil.enumValue(octaveShift.getType()));
        attributes.put("size", BuilderUtil.stringValue(octaveShift.getSize()));
        buildElementWithAttributes("octave-shift", attributes);
    }

    private void buildHarpPedals(HarpPedals harpPedals) {
        appendLine("<harp-pedals>");
        for (PedalTuning pedalTuning : harpPedals.getPedalTunings()) {
            buildElement("pedal-tuning");
        }
        appendLine("</harp-pedals>");
    }

    private void buildDamp(Damp damp) {
        buildElement("damp");
    }

    private void buildDampAll(DampAll dampAll) {
        buildElement("damp-all");
    }

    private void buildEyglasses(Eyeglasses eyeglasses) {
        buildElement("eyeglasses");
    }

    private void buildStringMute(StringMute stringMute) {
        buildElement("string-mute");
    }

    private void buildScordatura(Scordatura scordatura) {
        appendLine("<scordatura>");
        for (Accord accord : scordatura.getAccords()) {
            buildElement("accord");
        }
        appendLine("</scordatura>");
    }

    private void buildImage(Image image) {
        buildElement("image");
    }

    private void buildPrincipalVoice(PrincipalVoice principalVoice) {
        String symbol = BuilderUtil.enumValue(principalVoice.getSymbol());
        symbol = symbol.replace("hauptstimme", "Hauptstimme");
        symbol = symbol.replace("nebenstimme", "Nebenstimme");
        buildElementWithValueAndAttribute("principal-voice", principalVoice.getPrincipalVoice(), "symbol", symbol);
    }

    private void buildAccordionRegistration(AccordionRegistration accordionRegistration) {
        appendLine("<accordion-registration>");
        buildElementWithValue("accordion-middle", accordionRegistration.getAccordionMiddle());
        appendLine("</accordion-registration>");
    }

    private void buildPercussion(Percussion percussion) {
        appendLine("<percussion>");
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
        appendLine("</percussion>");
    }

    private void buildGlass(Glass glass) {
        String type = BuilderUtil.enumValue(glass.getType());
        type = type.replace("-", " ");
        buildElementWithValue("glass", type);
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
        String type = BuilderUtil.enumValue(wood.getType());
        type = type.replace("-", " ");
        buildElementWithValue("wood", type);
    }

    private void buildPitched(Pitched pitched) {
        String type = BuilderUtil.enumValue(pitched.getType());
        type = type.replace("-", " ");
        buildElementWithValue("pitched", type);
    }

    private void buildMembrane(Membrane membrane) {
        String type = BuilderUtil.enumValue(membrane.getType());
        type = type.replace("-", " ");
        buildElementWithValue("membrane", type);
    }

    private void buildEffect(Effect effect) {
        String type = BuilderUtil.enumValue(effect.getType());
        type = type.replace("-", " ");
        buildElementWithValue("effect", type);
    }

    private void buildTimpani(Timpani timpani) {
        buildElement("timpani");
    }

    private void buildBeater(Beater beater) {
        buildElementWithValueAndAttribute("beater", BuilderUtil.enumValue(beater.getBeaterValue()), "tip", BuilderUtil.enumValue(beater.getTip()));
    }

    private void buildStick(Stick stick) {
        append("<stick");
        buildAttribute("tip", BuilderUtil.enumValue(stick.getTip()));
        appendLine(">");
        String stickType = BuilderUtil.enumValue(stick.getStickType());
        stickType = stickType.replace("-", " ");
        buildElementWithValue("stick-type", stickType);
        buildElementWithValue("stick-material", BuilderUtil.enumValue(stick.getStickMaterial()));
        appendLine("</stick>");
    }

    private void buildStickLocation(StickLocation stickLocation) {
        String type = BuilderUtil.enumValue(stickLocation.getType());
        type = type.replace("-", " ");
        buildElementWithValue("stick-location", type);
    }

    private void buildOtherPercussion(OtherPercussion otherPercussion) {
        buildElementWithValue("other-percussion", otherPercussion.getValue());
    }

    private void buildOtherDirection(OtherDirection otherDirection) {
        buildElementWithValue("other-direction", otherDirection.getValue());
    }
}