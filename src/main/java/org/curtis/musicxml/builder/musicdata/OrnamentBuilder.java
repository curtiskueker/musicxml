package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.builder.util.BuilderUtil;
import org.curtis.musicxml.note.notation.ornament.AbstractMordent;
import org.curtis.musicxml.note.notation.ornament.DelayedInvertedTurn;
import org.curtis.musicxml.note.notation.ornament.DelayedTurn;
import org.curtis.musicxml.note.notation.ornament.HorizontalTurn;
import org.curtis.musicxml.note.notation.ornament.InvertedMordent;
import org.curtis.musicxml.note.notation.ornament.InvertedTurn;
import org.curtis.musicxml.note.notation.ornament.Mordent;
import org.curtis.musicxml.note.notation.ornament.Ornament;
import org.curtis.musicxml.note.notation.ornament.OtherOrnament;
import org.curtis.musicxml.note.notation.ornament.Schleifer;
import org.curtis.musicxml.note.notation.ornament.Shake;
import org.curtis.musicxml.note.notation.ornament.Tremolo;
import org.curtis.musicxml.note.notation.ornament.TrillMark;
import org.curtis.musicxml.note.notation.ornament.Turn;
import org.curtis.musicxml.note.notation.ornament.VerticalTurn;

import java.util.HashMap;
import java.util.Map;

public class OrnamentBuilder extends BaseBuilder {
    private Ornament ornament;

    public OrnamentBuilder(Ornament ornament) {
        this.ornament = ornament;
    }

    public StringBuilder build() {
        if (ornament == null) return stringBuilder;

        if (ornament instanceof TrillMark) buildTrillMark((TrillMark)ornament);
        else if (ornament instanceof HorizontalTurn) buildHorizontalTurn((HorizontalTurn)ornament);
        else if (ornament instanceof VerticalTurn) buildVerticalTurn((VerticalTurn)ornament);
        else if (ornament instanceof Shake) buildShake((Shake)ornament);
        else if (ornament instanceof AbstractMordent) buildAbstractMordent((AbstractMordent)ornament);
        else if (ornament instanceof Schleifer) buildSchleifer((Schleifer)ornament);
        else if (ornament instanceof Tremolo) buildTremolo((Tremolo)ornament);
        else if (ornament instanceof OtherOrnament) buildOtherOrnament((OtherOrnament)ornament);

        return stringBuilder;
    }

    private void buildTrillMark(TrillMark trillMark) {
        buildElement("trill-mark");
    }

    private void buildHorizontalTurn(HorizontalTurn horizontalTurn) {
        String elementName;
        if (horizontalTurn instanceof Turn) elementName = "turn";
        else if (horizontalTurn instanceof DelayedTurn) elementName = "delayed-turn";
        else if (horizontalTurn instanceof InvertedTurn) elementName = "inverted-turn";
        else if (horizontalTurn instanceof DelayedInvertedTurn) elementName = "delayed-inverted-turn";
        else return;

        buildElementWithAttribute(elementName, "slash", BuilderUtil.yesOrNo(horizontalTurn.getSlash()));
    }

    private void buildVerticalTurn(VerticalTurn verticalTurn) {
        buildElement("vertical-turn");
    }

    private void buildShake(Shake shake) {
        buildElement("shake");
    }

    private void buildAbstractMordent(AbstractMordent abstractMordent) {
        String elementName;
        if (abstractMordent instanceof Mordent) elementName = "mordent";
        else if (abstractMordent instanceof InvertedMordent) elementName = "inverted-mordent";
        else return;

        Map<String, String> attributes = new HashMap<>();
        attributes.put("long", BuilderUtil.yesOrNo(abstractMordent.getLongMordent()));
        attributes.put("approach", BuilderUtil.enumValue(abstractMordent.getApproach()));
        attributes.put("departure", BuilderUtil.enumValue(abstractMordent.getDeparture()));
        buildElementWithAttributes(elementName, attributes);
    }

    private void buildSchleifer(Schleifer schleifer) {
        buildPlacement("schleifer", schleifer.getPlacement());
    }

    private void buildTremolo(Tremolo tremolo) {
        buildElementWithValueAndAttribute("tremolo", tremolo.getTremoloMarks(), "type", BuilderUtil.enumValue(tremolo.getType()));
    }

    private void buildOtherOrnament(OtherOrnament otherOrnament) {
        buildPlacementText("other-ornament", otherOrnament.getPlacementText());
    }
}
