package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.attributes.Image;
import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.builder.util.BuilderUtil;
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
import org.curtis.musicxml.direction.directiontype.PrincipalVoice;
import org.curtis.musicxml.direction.directiontype.Rehearsal;
import org.curtis.musicxml.direction.directiontype.Scordatura;
import org.curtis.musicxml.direction.directiontype.Segno;
import org.curtis.musicxml.direction.directiontype.StringMute;
import org.curtis.musicxml.direction.directiontype.Wedge;
import org.curtis.musicxml.direction.directiontype.Words;
import org.curtis.musicxml.direction.directiontype.metronome.Metronome;
import org.curtis.musicxml.direction.directiontype.percussion.Percussion;

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
        buildElenent("rehearsal");
    }

    private void buildSegno(Segno segno) {
        buildElenent("segno");
    }

    private void buildWords(Words words) {
        buildElenent("words");
    }

    private void buildCoda(Coda coda) {
        buildElenent("coda");
    }

    private void buildWedge(Wedge wedge) {
        buildElementWithAttribute("wedge", "type", BuilderUtil.enumValue(wedge.getType()));
    }

    private void buildDynamics(Dynamics dynamics) {
        buildElenent("dynamics");
    }

    private void buildDashes(Dashes dashes) {
        buildElenent("dashes");
    }

    private void buildBracket(Bracket bracket) {
        buildElementWithAttribute("bracket", "line-end", BuilderUtil.enumValue(bracket.getLineEnd()));
    }

    private void buildPedal(Pedal pedal) {
        buildElementWithAttribute("pedal", "type", BuilderUtil.enumValue(pedal.getType()));
    }

    private void buildMetronome(Metronome metronome) {
        buildElenent("metronome");
    }

    private void buildOctaveShift(OctaveShift octaveShift) {
        buildElenent("octave-shift");
    }

    private void buildHarpPedals(HarpPedals harpPedals) {
        buildElenent("harp-pedals");
    }

    private void buildDamp(Damp damp) {
        buildElenent("damp");
    }

    private void buildDampAll(DampAll dampAll) {
        buildElenent("damp-all");
    }

    private void buildEyglasses(Eyeglasses eyeglasses) {
        buildElenent("eyeglasses");
    }

    private void buildStringMute(StringMute stringMute) {
        buildElenent("string-mute");
    }

    private void buildScordatura(Scordatura scordatura) {
        buildElenent("scordatura");
    }

    private void buildImage(Image image) {
        buildElenent("image");
    }

    private void buildPrincipalVoice(PrincipalVoice principalVoice) {
        buildElenent("principal-voice");
    }

    private void buildAccordionRegistration(AccordionRegistration accordionRegistration) {
        buildElenent("accordion-registration");
    }

    private void buildPercussion(Percussion percussion) {
        buildElenent("percussion");
    }

    private void buildOtherDirection(OtherDirection otherDirection) {
        buildElenent("other-direction");
    }
}
