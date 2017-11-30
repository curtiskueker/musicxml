package org.curtis.musicxml.handler;

import org.curtis.musicxml.common.FormattedText;
import org.curtis.musicxml.common.LevelDisplay;
import org.curtis.musicxml.common.Printout;
import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.factory.NotationFactory;
import org.curtis.musicxml.factory.NoteFactory;
import org.curtis.musicxml.factory.OrnamentFactory;
import org.curtis.musicxml.factory.PlacementFactory;
import org.curtis.musicxml.handler.util.TypeUtil;
import org.curtis.musicxml.note.Accidental;
import org.curtis.musicxml.note.AccidentalText;
import org.curtis.musicxml.note.Beam;
import org.curtis.musicxml.note.BeamFan;
import org.curtis.musicxml.note.BeamType;
import org.curtis.musicxml.note.FullNote;
import org.curtis.musicxml.note.Grace;
import org.curtis.musicxml.note.Notations;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.note.NoteType;
import org.curtis.musicxml.note.Notehead;
import org.curtis.musicxml.note.NoteheadText;
import org.curtis.musicxml.note.NoteheadType;
import org.curtis.musicxml.note.Pitch;
import org.curtis.musicxml.note.Placement;
import org.curtis.musicxml.note.Rest;
import org.curtis.musicxml.note.Stem;
import org.curtis.musicxml.note.StemType;
import org.curtis.musicxml.note.Tie;
import org.curtis.musicxml.note.TimeModification;
import org.curtis.musicxml.note.Unpitched;
import org.curtis.musicxml.note.XPosition;
import org.curtis.musicxml.note.YPosition;
import org.curtis.musicxml.note.notation.Fermata;
import org.curtis.musicxml.note.notation.Notation;
import org.curtis.musicxml.note.notation.Slur;
import org.curtis.musicxml.note.notation.Tied;
import org.curtis.musicxml.note.notation.Tuplet;
import org.curtis.musicxml.note.notation.articulation.Staccato;
import org.curtis.musicxml.note.notation.articulation.Tenuto;
import org.curtis.musicxml.note.notation.ornament.TrillMark;
import org.curtis.musicxml.note.notation.ornament.WavyLine;
import org.curtis.musicxml.score.MusicData;
import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class NoteHandler extends MusicDataHandler {
    public NoteHandler() {

    }

    public MusicData handle(Element element) {
        Note note = new Note();
        FullNote fullNote = new FullNote();

        XPosition xPosition = new XPosition();
        xPosition.setDefaultX(MathUtil.newBigDecimal(element.getAttribute("default-x")));
        xPosition.setDefaultY(MathUtil.newBigDecimal(element.getAttribute("default-y")));
        xPosition.setRelativeX(MathUtil.newBigDecimal(element.getAttribute("relative-x")));
        xPosition.setRelativeY(MathUtil.newBigDecimal(element.getAttribute("relative-y")));
        note.setxPosition(xPosition);

        note.setFont(FormattingFactory.newFont(element));
        note.setColor(element.getAttribute("color"));

        Printout printout = new Printout();
        printout.setPrintObject(TypeUtil.getYesNo(element.getAttribute("print-object")));
        printout.setPrintDot(TypeUtil.getYesNo(element.getAttribute("print-dot")));
        printout.setPrintSpacing(TypeUtil.getYesNo(element.getAttribute("print-spacing")));
        printout.setPrintLyric(TypeUtil.getYesNo(element.getAttribute("print-lyric")));
        note.setPrintout(printout);

        note.setDynamics(MathUtil.newBigDecimal(element.getAttribute("dynamics")));
        note.setEndDynamics(MathUtil.newBigDecimal(element.getAttribute("end-dynamics")));
        note.setAttack(MathUtil.newBigDecimal(element.getAttribute("attack")));
        note.setRelease(MathUtil.newBigDecimal(element.getAttribute("release")));
        note.setTimeOnly(element.getAttribute("time-only"));
        note.setPizzicato(TypeUtil.getYesNo(element.getAttribute("pizzicato")));

        List<Element> noteSubelements = XmlUtil.getChildElements(element);
        for(Element noteSubelement : noteSubelements) {
            switch (noteSubelement.getTagName()) {
                case "grace":
                    Grace grace = new Grace();
                    grace.setStealTimePrevious(MathUtil.newBigDecimal(noteSubelement.getAttribute("steal-time-previous")));
                    grace.setStealTimeFollowing(MathUtil.newBigDecimal(noteSubelement.getAttribute("steal-time-following")));
                    grace.setMakeTime(MathUtil.newBigDecimal(noteSubelement.getAttribute("make-time")));
                    grace.setSlash(TypeUtil.getYesNo(noteSubelement.getAttribute("slash")));
                    note.setGrace(grace);
                    break;
                case "chord":
                    fullNote.setChord(true);
                    break;
                case "pitch":
                    Pitch pitch = new Pitch();
                    pitch.setStep(NoteFactory.newStep(XmlUtil.getChildElement(noteSubelement, "step")));
                    pitch.setAlter(MathUtil.newBigDecimal(XmlUtil.getChildElementText(noteSubelement, "alter")));
                    pitch.setOctave(StringUtil.getInteger(XmlUtil.getChildElementText(noteSubelement, "octave")));
                    fullNote.setFullNoteType(pitch);
                    break;
                case "unpitched":
                    Unpitched unpitched = new Unpitched();
                    unpitched.setDisplayStep(NoteFactory.newStep(XmlUtil.getChildElement(noteSubelement, "display-step")));
                    unpitched.setDisplayOctave(StringUtil.getInteger(XmlUtil.getChildElementText(noteSubelement, "display-octave")));
                    fullNote.setFullNoteType(unpitched);
                case "rest":
                    Rest rest = new Rest();
                    rest.setDisplayStep(NoteFactory.newStep(XmlUtil.getChildElement(noteSubelement, "display-step")));
                    rest.setDisplayOctave(StringUtil.getInteger(XmlUtil.getChildElementText(noteSubelement, "display-octave")));
                    fullNote.setFullNoteType(rest);
                    break;
                case "duration":
                    note.setDuration(MathUtil.newBigDecimal(XmlUtil.getElementText(noteSubelement)));
                    break;
                case "tie":
                    Tie tie = new Tie();
                    tie.setType(PlacementUtil.getConnection(noteSubelement.getAttribute("type")));
                    tie.setTimeOnly(noteSubelement.getAttribute("time-only"));
                    List<Tie> ties = note.getTies();
                    ties.add(tie);
                    break;
                case "cue":
                    note.setCue(true);
                    break;
                case "instrument":
                    note.setInstrument(noteSubelement.getAttribute("id"));
                case "type":
                    NoteType noteType = new NoteType();
                    noteType.setValue(NoteFactory.newNoteTypeValue(noteSubelement));
                    note.setType(noteType);
                    String noteTypeSize = noteSubelement.getAttribute("size");
                    noteType.setSize(FormattingFactory.newSymbolSize(noteSubelement));
                    break;
                case "dot":
                    List<Placement> dots = note.getDots();
                    Placement dotPlacement = PlacementFactory.newPlacement(noteSubelement);
                    dots.add(dotPlacement);
                    break;
                case "accidental":
                    Accidental accidental = new Accidental();
                    accidental.setAccidentalType(NoteFactory.newAccidentalType(noteSubelement));
                    accidental.setCautionary(TypeUtil.getYesNo(noteSubelement.getAttribute("cautionary")));
                    accidental.setEditorial(TypeUtil.getYesNo(noteSubelement.getAttribute("editorial")));
                    LevelDisplay levelDisplay = new LevelDisplay();
                    levelDisplay.setParentheses(TypeUtil.getYesNo(noteSubelement.getAttribute("parentheses")));
                    levelDisplay.setBracket(TypeUtil.getYesNo(noteSubelement.getAttribute("bracket")));
                    levelDisplay.setSize(FormattingFactory.newSymbolSize(noteSubelement));
                    accidental.setLevelDisplay(levelDisplay);
                    accidental.setPrintStyle(FormattingFactory.newPrintStyle(noteSubelement));
                    note.setAccidental(accidental);
                    break;
                case "time-modification":
                    TimeModification timeModification = new TimeModification();
                    timeModification.setActualNotes(StringUtil.getInteger(XmlUtil.getChildElementText(noteSubelement, "actual-notes")));
                    timeModification.setNormalNotes(StringUtil.getInteger(XmlUtil.getChildElementText(noteSubelement, "normal-notes")));
                    timeModification.setNormalType(NoteFactory.newNoteTypeValue(XmlUtil.getChildElement(noteSubelement, "normal-type")));
                    List<Element> dotElements = XmlUtil.getChildElements(noteSubelement, "normal-dot");
                    timeModification.setNormalDots(dotElements.size());
                    note.setTimeModification(timeModification);
                    break;
                case "stem":
                    Stem stem = new Stem();
                    switch (XmlUtil.getElementText(noteSubelement)) {
                        case "down":
                            stem.setType(StemType.DOWN);
                            break;
                        case "up":
                            stem.setType(StemType.UP);
                            break;
                        case "double":
                            stem.setType(StemType.DOUBLE);
                            break;
                        case "none":
                            stem.setType(StemType.NONE);
                            break;
                    }
                    YPosition yPosition = new YPosition();
                    yPosition.setDefaultX(MathUtil.newBigDecimal(noteSubelement.getAttribute("default-x")));
                    yPosition.setDefaultY(MathUtil.newBigDecimal(noteSubelement.getAttribute("default-y")));
                    yPosition.setRelativeX(MathUtil.newBigDecimal(noteSubelement.getAttribute("relative-x")));
                    yPosition.setRelativeX(MathUtil.newBigDecimal(noteSubelement.getAttribute("relative-x")));
                    stem.setyPosition(yPosition);
                    stem.setColor(noteSubelement.getAttribute("color"));
                    note.setStem(stem);
                    break;
                case "notehead":
                    Notehead notehead = new Notehead();
                    String noteheadType = XmlUtil.getElementText(noteSubelement);
                    if(StringUtil.isNotEmpty(noteheadType)) {
                        switch (noteheadType) {
                            case "slash":
                                notehead.setType(NoteheadType.SLASH);
                                break;
                            case "triangle":
                                notehead.setType(NoteheadType.TRIANGLE);
                                break;
                            case "diamond":
                                notehead.setType(NoteheadType.DIAMOND);
                                break;
                            case "square":
                                notehead.setType(NoteheadType.SQUARE);
                                break;
                            case "cross":
                                notehead.setType(NoteheadType.CROSS);
                                break;
                            case "x":
                                notehead.setType(NoteheadType.X);
                                break;
                            case "circle-x":
                                notehead.setType(NoteheadType.CIRCLE_X);
                                break;
                            case "inverted-triangle":
                                notehead.setType(NoteheadType.INVERTED_TRIANGLE);
                                break;
                            case "arrow-down":
                                notehead.setType(NoteheadType.ARROW_DOWN);
                                break;
                            case "arrow-up":
                                notehead.setType(NoteheadType.ARROW_UP);
                                break;
                            case "slashed":
                                notehead.setType(NoteheadType.SLASHED);
                                break;
                            case "back-slashed":
                                notehead.setType(NoteheadType.BACK_SLASHED);
                                break;
                            case "normal":
                                notehead.setType(NoteheadType.NORMAL);
                                break;
                            case "cluster":
                                notehead.setType(NoteheadType.CLUSTER);
                                break;
                            case "circle-dot":
                                notehead.setType(NoteheadType.CIRCLE_DOT);
                                break;
                            case "left-triangle":
                                notehead.setType(NoteheadType.LEFT_TRIANGLE);
                                break;
                            case "none":
                                notehead.setType(NoteheadType.NONE);
                                break;
                            case "do":
                                notehead.setType(NoteheadType.DO);
                                break;
                            case "re":
                                notehead.setType(NoteheadType.RE);
                                break;
                            case "mi":
                                notehead.setType(NoteheadType.MI);
                                break;
                            case "fa":
                                notehead.setType(NoteheadType.FA);
                                break;
                            case "fa-up":
                                notehead.setType(NoteheadType.FA_UP);
                                break;
                            case "so":
                                notehead.setType(NoteheadType.SO);
                                break;
                            case "la":
                                notehead.setType(NoteheadType.LA);
                                break;
                            case "ti":
                                notehead.setType(NoteheadType.TI);
                                break;
                        }
                    }

                    String filledNotehead = noteSubelement.getAttribute("filled");
                    if (StringUtil.isNotEmpty(filledNotehead)) {
                        notehead.setFilled(TypeUtil.getYesNo(filledNotehead));
                    }
                    notehead.setParentheses(TypeUtil.getYesNo(noteSubelement.getAttribute("parentheses")));
                    notehead.setFont(FormattingFactory.newFont(noteSubelement));
                    notehead.setColor(noteSubelement.getAttribute("color"));
                    note.setNotehead(notehead);
                    break;
                case "notehead-text":
                    NoteheadText noteheadText = new NoteheadText();
                    List<Element> noteheadTextSubelements = XmlUtil.getChildElements(noteSubelement);
                    for(Element noteheadTextSubelement : noteheadTextSubelements) {
                        String noteheadTextSubelementName = noteheadTextSubelement.getTagName();
                        switch (noteheadTextSubelementName) {
                            case "display-text":
                                List<FormattedText> displayTextList = noteheadText.getDisplayTextList();
                                displayTextList.add(FormattingFactory.newFormattedText(noteheadTextSubelement));
                                break;
                            case "accidental-text":
                                List<AccidentalText> accidentalTextList = noteheadText.getAccidentalTextList();
                                AccidentalText accidentalText = new AccidentalText();
                                accidentalText.setAccidentalType(NoteFactory.newAccidentalType(noteheadTextSubelement));
                                accidentalText.setTextFormatting(FormattingFactory.newTextFormatting(noteheadTextSubelement));
                                accidentalTextList.add(accidentalText);
                                break;
                        }
                    }
                    note.setNoteheadText(noteheadText);
                    break;
                case "staff":
                    note.setStaff(StringUtil.getInteger(XmlUtil.getElementText(noteSubelement)));
                    break;
                case "beam":
                    List<Beam> beams = note.getBeams();
                    Beam beam = new Beam();
                    switch (XmlUtil.getElementText(noteSubelement)) {
                        case "begin":
                            beam.setType(BeamType.BEGIN);
                            break;
                        case "continue":
                            beam.setType(BeamType.CONTINUE);
                            break;
                        case "end":
                            beam.setType(BeamType.END);
                            break;
                        case "forward hook":
                            beam.setType(BeamType.FORWARD_HOOK);
                            break;
                        case "backward hook":
                            beam.setType(BeamType.BACKWARD_HOOK);
                            break;
                    }
                    beam.setNumber(StringUtil.getInteger(noteSubelement.getAttribute("number")));
                    beam.setRepeater(TypeUtil.getYesNo(noteSubelement.getAttribute("repeater")));
                    String beamFan = noteSubelement.getAttribute("fan");
                    if(StringUtil.isNotEmpty(beamFan)) {
                        switch (beamFan) {
                            case "accel":
                                beam.setFan(BeamFan.ACCEL);
                                break;
                            case "rit":
                                beam.setFan(BeamFan.RIT);
                                break;
                            case "none":
                                beam.setFan(BeamFan.NONE);
                                break;
                        }
                    }
                    beam.setColor(noteSubelement.getAttribute("color"));
                    beams.add(beam);
                    break;
                case "notations":
                    List<Notations> notationsList = note.getNotationsList();
                    Notations notations = new Notations();
                    List<Notation> notationList = notations.getNotations();
                    List<Element> notationsSubelements = XmlUtil.getChildElements(noteSubelement);
                    for(Element notationsSubelement : notationsSubelements) {
                        switch (notationsSubelement.getTagName()) {
                            case "tied":
                                Tied tied = new Tied();
                                tied.setType(PlacementUtil.getConnection(notationsSubelement.getAttribute("type")));
                                tied.setPlacement(PlacementUtil.getLocation(notationsSubelement.getAttribute("placement")));
                                notationList.add(tied);
                                break;
                            case "slur":
                                Slur slur = new Slur();
                                slur.setType(PlacementUtil.getConnection(notationsSubelement.getAttribute("type")));
                                slur.setPlacement(PlacementUtil.getLocation(notationsSubelement.getAttribute("placement")));
                                slur.setNumber(StringUtil.getInteger(notationsSubelement.getAttribute("number")));
                                notationList.add(slur);
                                break;
                            case "tuplet":
                                Tuplet tuplet = NotationFactory.newTuplet(notationsSubelement);
                                notationList.add(tuplet);
                                break;
                            case "ornaments":
                                List<Element> ornamentsSubelements = XmlUtil.getChildElements(notationsSubelement);
                                for(Element ornamentsSubelement : ornamentsSubelements) {
                                    switch (ornamentsSubelement.getTagName()) {
                                        case "trill-mark":
                                            TrillMark trillMark = new TrillMark();
                                            trillMark.setPlacement(PlacementUtil.getLocation(ornamentsSubelement.getAttribute("placement")));
                                            notationList.add(trillMark);
                                            break;
                                        case "wavy-line":
                                            WavyLine wavyLine = OrnamentFactory.newWavyLine(ornamentsSubelement);
                                            notationList.add(wavyLine);
                                    }
                            }
                                break;
                            case "articulations":
                                List<Element> articulationsSubelements = XmlUtil.getChildElements(notationsSubelement);
                                for(Element articulationsSubelement : articulationsSubelements) {
                                    switch (articulationsSubelement.getTagName()) {
                                        case "staccato":
                                            Staccato staccato = new Staccato();
                                            Placement staccatoPlacement = PlacementFactory.newPlacement(noteSubelement);
                                            staccato.setPlacement(staccatoPlacement);
                                            notationList.add(staccato);
                                            break;
                                        case "tenuto":
                                            Tenuto tenuto = new Tenuto();
                                            Placement tenutoPlacement = PlacementFactory.newPlacement(noteSubelement);
                                            tenuto.setPlacement(tenutoPlacement);
                                            notationList.add(tenuto);
                                            break;
                                    }
                                }
                                break;
                            case "fermata":
                                Fermata fermata = NotationFactory.newFermata(notationsSubelement);
                                notationList.add(fermata);
                                break;
                        }
                    }
                    notations.setNotations(notationList);
                    notationsList.add(notations);
                    break;
            }
        }

        note.setFullNote(fullNote);
        return note;
    }
}
