package org.curtis.musicxml.factory;

import org.curtis.musicxml.direction.directiontype.Bracket;
import org.curtis.musicxml.direction.directiontype.Dashes;
import org.curtis.musicxml.direction.directiontype.DirectionType;
import org.curtis.musicxml.direction.directiontype.LineEnd;
import org.curtis.musicxml.direction.directiontype.Metronome;
import org.curtis.musicxml.direction.directiontype.Pedal;
import org.curtis.musicxml.direction.directiontype.Rehearsal;
import org.curtis.musicxml.direction.directiontype.Segno;
import org.curtis.musicxml.direction.directiontype.Wedge;
import org.curtis.musicxml.direction.directiontype.WedgeType;
import org.curtis.musicxml.direction.directiontype.Words;
import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.musicxml.handler.util.TypeUtil;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.w3c.dom.Element;

import java.util.List;

public class DirectionTypeFactory {
    private DirectionTypeFactory() {

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
                // TODO: the rest of the direction types
                Metronome metronome = new Metronome();
                return metronome;
            default:
                return null;
        }
    }
}
