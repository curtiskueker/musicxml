package org.curtis.musicxml.handler;

import org.curtis.musicxml.common.Text;
import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.factory.NoteFactory;
import org.curtis.musicxml.factory.PlacementFactory;
import org.curtis.musicxml.factory.ScorePartFactory;
import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.musicxml.handler.util.TypeUtil;
import org.curtis.musicxml.note.Accidental;
import org.curtis.musicxml.note.Beam;
import org.curtis.musicxml.note.BeamFan;
import org.curtis.musicxml.note.Dot;
import org.curtis.musicxml.note.FullNote;
import org.curtis.musicxml.note.Grace;
import org.curtis.musicxml.note.Notations;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.note.NoteType;
import org.curtis.musicxml.note.Notehead;
import org.curtis.musicxml.note.NoteheadText;
import org.curtis.musicxml.note.NoteheadType;
import org.curtis.musicxml.note.Pitch;
import org.curtis.musicxml.note.Rest;
import org.curtis.musicxml.note.Stem;
import org.curtis.musicxml.note.StemType;
import org.curtis.musicxml.note.Tie;
import org.curtis.musicxml.note.Unpitched;
import org.curtis.musicxml.note.lyric.Lyric;
import org.curtis.musicxml.score.MusicData;
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

        note.setPosition(PlacementFactory.newPosition(element));
        note.setEditorialVoice(FormattingFactory.newEditorialVoice(element));
        note.setFont(FormattingFactory.newFont(element));
        note.setColor(element.getAttribute("color"));
        note.setPrintout(FormattingFactory.newPrintout(element));
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
                    break;
                case "rest":
                    Rest rest = new Rest();
                    rest.setDisplayStep(NoteFactory.newStep(XmlUtil.getChildElement(noteSubelement, "display-step")));
                    rest.setDisplayOctave(StringUtil.getInteger(XmlUtil.getChildElementText(noteSubelement, "display-octave")));
                    fullNote.setFullNoteType(rest);
                    rest.setMeasure(TypeUtil.getYesNo(noteSubelement.getAttribute("measure")));
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
                    noteType.setSize(FormattingFactory.newSymbolSize(noteSubelement));
                    break;
                case "dot":
                    Dot dot = new Dot();
                    dot.setPlacement(PlacementFactory.newPlacement(noteSubelement));
                    note.getDots().add(dot);
                    break;
                case "accidental":
                    Accidental accidental = new Accidental();
                    accidental.setAccidentalType(NoteFactory.newAccidentalType(noteSubelement));
                    accidental.setCautionary(TypeUtil.getYesNo(noteSubelement.getAttribute("cautionary")));
                    accidental.setEditorial(TypeUtil.getYesNo(noteSubelement.getAttribute("editorial")));
                    accidental.setLevelDisplay(FormattingFactory.newLevelDisplay(noteSubelement));
                    accidental.setPrintStyle(FormattingFactory.newPrintStyle(noteSubelement));
                    note.setAccidental(accidental);
                    break;
                case "time-modification":
                    note.setTimeModification(NoteFactory.newTimeModification(noteSubelement));
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
                    stem.setPosition(PlacementFactory.newPosition(noteSubelement));
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
                    notehead.setFilled(TypeUtil.getYesNo(filledNotehead));
                    notehead.setParentheses(TypeUtil.getYesNo(noteSubelement.getAttribute("parentheses")));
                    notehead.setFont(FormattingFactory.newFont(noteSubelement));
                    notehead.setColor(noteSubelement.getAttribute("color"));
                    note.setNotehead(notehead);
                    break;
                case "notehead-text":
                    List<Element> noteheadTextSubelements = XmlUtil.getChildElements(noteSubelement);
                    NoteheadText noteheadText = new NoteheadText();
                    for(Element noteheadTextSubelement : noteheadTextSubelements) {
                        Text text = FormattingFactory.newText(noteheadTextSubelement);
                        if (text != null) {
                            noteheadText.getTextList().add(text);
                            text.setNoteheadText(noteheadText);
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
                    beam.setType(NoteFactory.newBeamType(noteSubelement));
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
                    NotationHandler notationHandler = new NotationHandler(notationsList);
                    notationHandler.handle(noteSubelement);
                    break;
                case "lyric":
                    List<Lyric> lyrics = note.getLyrics();
                    LyricHandler lyricHandler = new LyricHandler(lyrics);
                    lyricHandler.handle(noteSubelement);
                    break;
                case "play":
                    note.setPlay(ScorePartFactory.newPlay(noteSubelement));
                    break;
            }
        }

        note.setFullNote(fullNote);
        return note;
    }
}
