package org.curtis.musicxml.handler;

import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.common.TextDisplay;
import org.curtis.musicxml.factory.DisplayFactory;
import org.curtis.musicxml.factory.FactoryUtil;
import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.factory.NoteFactory;
import org.curtis.musicxml.factory.ScorePartFactory;
import org.curtis.musicxml.util.TypeUtil;
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

        note.setElementId(element.getAttribute("id"));
        note.setDisplay(DisplayFactory.newDisplay(element));
        note.setEditorial(FormattingFactory.newEditorial(element));
        note.setPrintout(FormattingFactory.newPrintout(element));
        note.setPrintLeger(TypeUtil.getYesNo(element.getAttribute("print-leger")));
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
                    tie.setType(FactoryUtil.enumValue(Connection.class, noteSubelement.getAttribute("type")));
                    tie.setTimeOnly(noteSubelement.getAttribute("time-only"));
                    List<Tie> ties = note.getTies();
                    ties.add(tie);
                    break;
                case "cue":
                    note.setCue(true);
                    break;
                case "instrument":
                    note.setInstrument(noteSubelement.getAttribute("id"));
                    break;
                case "type":
                    NoteType noteType = new NoteType();
                    noteType.setValue(NoteFactory.newNoteTypeValue(noteSubelement));
                    note.setType(noteType);
                    noteType.setSize(FormattingFactory.newSymbolSize(noteSubelement));
                    break;
                case "dot":
                    Dot dot = new Dot();
                    dot.setDisplay(DisplayFactory.newDisplay(noteSubelement));
                    note.getDots().add(dot);
                    break;
                case "accidental":
                    Accidental accidental = new Accidental();
                    accidental.setAccidentalType(NoteFactory.newAccidentalType(noteSubelement));
                    accidental.setCautionary(TypeUtil.getYesNo(noteSubelement.getAttribute("cautionary")));
                    accidental.setEditorial(TypeUtil.getYesNo(noteSubelement.getAttribute("editorial")));
                    accidental.setLevelDisplay(FormattingFactory.newLevelDisplay(noteSubelement));
                    accidental.setDisplay(DisplayFactory.newDisplay(noteSubelement));
                    accidental.setSmufl(noteSubelement.getAttribute("smufl"));
                    note.setAccidental(accidental);
                    break;
                case "time-modification":
                    note.setTimeModification(NoteFactory.newTimeModification(noteSubelement));
                    break;
                case "stem":
                    Stem stem = new Stem();
                    stem.setType(FactoryUtil.enumValue(StemType.class, XmlUtil.getElementText(noteSubelement)));
                    stem.setDisplay(DisplayFactory.newDisplay(noteSubelement));
                    note.setStem(stem);
                    break;
                case "notehead":
                    Notehead notehead = new Notehead();
                    String noteheadType = XmlUtil.getElementText(noteSubelement);
                    notehead.setType(FactoryUtil.enumValue(NoteheadType.class, noteheadType));
                    String filledNotehead = noteSubelement.getAttribute("filled");
                    notehead.setFilled(TypeUtil.getYesNo(filledNotehead));
                    notehead.setParentheses(TypeUtil.getYesNo(noteSubelement.getAttribute("parentheses")));
                    notehead.setDisplay(DisplayFactory.newDisplay(noteSubelement));
                    notehead.setSmufl(noteSubelement.getAttribute("smufl"));
                    note.setNotehead(notehead);
                    break;
                case "notehead-text":
                    List<Element> noteheadTextSubelements = XmlUtil.getChildElements(noteSubelement);
                    NoteheadText noteheadText = new NoteheadText();
                    for(Element noteheadTextSubelement : noteheadTextSubelements) {
                        TextDisplay text = FormattingFactory.newText(noteheadTextSubelement);
                        if (text != null) noteheadText.getTextList().add(text);
                    }
                    note.setNoteheadText(noteheadText);
                    break;
                case "staff":
                    note.setStaff(StringUtil.getInteger(XmlUtil.getElementText(noteSubelement)));
                    break;
                case "beam":
                    List<Beam> beams = note.getBeams();
                    Beam beam = new Beam();
                    beam.setElementId(noteSubelement.getAttribute("id"));
                    beam.setType(NoteFactory.newBeamType(noteSubelement));
                    beam.setNumber(StringUtil.getInteger(noteSubelement.getAttribute("number")));
                    beam.setRepeater(TypeUtil.getYesNo(noteSubelement.getAttribute("repeater")));
                    beam.setFan(FactoryUtil.enumValue(BeamFan.class, noteSubelement.getAttribute("fan")));
                    beam.setDisplay(DisplayFactory.newDisplay(noteSubelement));
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
