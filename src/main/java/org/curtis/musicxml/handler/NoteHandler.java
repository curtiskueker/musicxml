package org.curtis.musicxml.handler;

import org.curtis.musicxml.common.EditorialVoice;
import org.curtis.musicxml.note.Accidental;
import org.curtis.musicxml.note.AccidentalValue;
import org.curtis.musicxml.note.Beam;
import org.curtis.musicxml.note.BeamType;
import org.curtis.musicxml.note.FullNote;
import org.curtis.musicxml.note.Grace;
import org.curtis.musicxml.note.Notations;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.note.NoteType;
import org.curtis.musicxml.note.NoteTypeValue;
import org.curtis.musicxml.note.Pitch;
import org.curtis.musicxml.note.Placement;
import org.curtis.musicxml.note.Rest;
import org.curtis.musicxml.note.Stem;
import org.curtis.musicxml.note.StemType;
import org.curtis.musicxml.note.Step;
import org.curtis.musicxml.note.Tie;
import org.curtis.musicxml.note.notation.Fermata;
import org.curtis.musicxml.note.notation.FermataType;
import org.curtis.musicxml.note.notation.Notation;
import org.curtis.musicxml.note.notation.Slur;
import org.curtis.musicxml.note.notation.Tied;
import org.curtis.musicxml.note.notation.articulation.Staccato;
import org.curtis.musicxml.note.notation.articulation.Tenuto;
import org.curtis.musicxml.note.notation.ornament.TrillMark;
import org.curtis.musicxml.score.MusicData;
import org.curtis.musicxml.util.EnumUtil;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class NoteHandler extends AbstractHandler {
    private List<MusicData> musicDataList;

    public NoteHandler(Element element, List<MusicData> musicDataList) {
        super(element);
        this.musicDataList = musicDataList;
    }

    public void handle() {
        Note note = new Note();

        List<Element> noteSubelements = XmlUtil.getChildElements(getElement());
        for(Element noteSubelement : noteSubelements) {
            switch (noteSubelement.getTagName()) {
                case "grace":
                    Grace grace = new Grace();
                    note.setGrace(grace);
                    break;
                case "pitch":
                    FullNote fullNote = new FullNote();
                    Pitch pitch = new Pitch();
                    Step step = null;
                    String stepValue = XmlUtil.getChildElementText(noteSubelement, "step");
                    switch (stepValue) {
                        case "A":
                            step = Step.A;
                            break;
                        case "B":
                            step = Step.B;
                            break;
                        case "C":
                            step = Step.C;
                            break;
                        case "D":
                            step = Step.D;
                            break;
                        case "E":
                            step = Step.E;
                            break;
                        case "F":
                            step = Step.F;
                            break;
                        case "G":
                            step = Step.G;
                            break;
                    }
                    pitch.setStep(step);
                    pitch.setOctave(Integer.parseInt(XmlUtil.getChildElementText(noteSubelement, "octave")));

                    Element alterElement = XmlUtil.getChildElement(noteSubelement, "alter");
                    if(alterElement != null) {
                        pitch.setAlter(MathUtil.newBigDecimal(XmlUtil.getElementText(alterElement)));
                    }

                    fullNote.setPitch(pitch);
                    note.setFullNote(fullNote);
                    break;
                case "rest":
                    FullNote fullNote1 = new FullNote();
                    Rest rest = new Rest();
                    fullNote1.setRest(rest);
                    note.setFullNote(fullNote1);
                    break;
                case "duration":
                    note.setDuration(MathUtil.newBigDecimal(XmlUtil.getElementText(noteSubelement)));
                    break;
                case "tie":
                    Tie tie = new Tie();
                    tie.setType(EnumUtil.getConnection(noteSubelement.getAttribute("type")));
                    List<Tie> ties = note.getTies();
                    ties.add(tie);
                    break;
                case "voice":
                    EditorialVoice editorialVoice = note.getEditorialVoice();
                    editorialVoice.setVoice(XmlUtil.getElementText(noteSubelement));
                    break;
                case "type":
                    NoteType noteType = new NoteType();
                    switch (XmlUtil.getElementText(noteSubelement)) {
                        case "1024th":
                            noteType.setValue(NoteTypeValue._1024TH);
                            break;
                        case "512th":
                            noteType.setValue(NoteTypeValue._512TH);
                            break;
                        case "256th":
                            noteType.setValue(NoteTypeValue._256TH);
                            break;
                        case "128th":
                            noteType.setValue(NoteTypeValue._128TH);
                            break;
                        case "64th":
                            noteType.setValue(NoteTypeValue._64TH);
                            break;
                        case "32nd":
                            noteType.setValue(NoteTypeValue._32ND);
                            break;
                        case "16th":
                            noteType.setValue(NoteTypeValue._16TH);
                            break;
                        case "eighth":
                            noteType.setValue(NoteTypeValue.EIGHTH);
                            break;
                        case "quarter":
                            noteType.setValue(NoteTypeValue.QUARTER);
                            break;
                        case "half":
                            noteType.setValue(NoteTypeValue.HALF);
                            break;
                        case "whole":
                            noteType.setValue(NoteTypeValue.WHOLE);
                            break;
                        case "breve":
                            noteType.setValue(NoteTypeValue.BREVE);
                            break;
                        case "long":
                            noteType.setValue(NoteTypeValue.LONG);
                            break;
                        case "maxima":
                            noteType.setValue(NoteTypeValue.MAXIMA);
                            break;
                    }
                    note.setType(noteType);
                    break;
                case "dot":
                    List<Placement> dots = note.getDots();
                    Placement dotPlacement = new Placement();
                    dots.add(dotPlacement);
                    break;
                case "accidental":
                    Accidental accidental = new Accidental();
                    switch (XmlUtil.getElementText(noteSubelement)) {
                        case "sharp":
                            accidental.setAccidentalValue(AccidentalValue.SHARP);
                            break;
                        case "natural":
                            accidental.setAccidentalValue(AccidentalValue.NATURAL);
                            break;
                        case "flat":
                            accidental.setAccidentalValue(AccidentalValue.FLAT);
                            break;
                    }
                    note.setAccidental(accidental);
                    break;
                case "stem":
                    Stem stem = new Stem();
                    switch (XmlUtil.getElementText(noteSubelement)) {
                        case "down":
                            stem.setValue(StemType.DOWN);
                            break;
                        case "up":
                            stem.setValue(StemType.UP);
                            break;
                        case "double":
                            stem.setValue(StemType.DOUBLE);
                            break;
                        case "none":
                            stem.setValue(StemType.NONE);
                            break;
                    }
                    note.setStem(stem);
                    break;
                case "beam":
                    List<Beam> beams = note.getBeams();
                    Beam beam = new Beam();
                    beam.setNumber(Integer.parseInt(noteSubelement.getAttribute("number")));
                    switch (XmlUtil.getElementText(noteSubelement)) {
                        case "begin":
                            beam.setValue(BeamType.BEGIN);
                            break;
                        case "continue":
                            beam.setValue(BeamType.CONTINUE);
                            break;
                        case "end":
                            beam.setValue(BeamType.END);
                            break;
                        case "forward hook":
                            beam.setValue(BeamType.FORWARD_HOOK);
                            break;
                        case "backward hook":
                            beam.setValue(BeamType.BACKWARD_HOOK);
                            break;
                    }
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
                                tied.setType(EnumUtil.getConnection(notationsSubelement.getAttribute("type")));
                                notationList.add(tied);
                                break;
                            case "slur":
                                Slur slur = new Slur();
                                slur.setType(EnumUtil.getConnection(notationsSubelement.getAttribute("type")));
                                String number = notationsSubelement.getAttribute("number");
                                if (StringUtil.isNotEmpty(number)) {
                                    slur.setNumber(Integer.parseInt(number));
                                }
                                notationList.add(slur);
                                break;
                            case "ornaments":
                                List<Element> ornamentsSubelements = XmlUtil.getChildElements(notationsSubelement);
                                for(Element ornamentsSubelement : ornamentsSubelements) {
                                    switch (ornamentsSubelement.getTagName()) {
                                        case "trill-mark":
                                            TrillMark trillMark = new TrillMark();
                                            trillMark.setPlacement(EnumUtil.getLocation(ornamentsSubelement.getAttribute("placement")));
                                            notationList.add(trillMark);
                                            break;
                                    }
                            }
                                break;
                            case "articulations":
                                List<Element> articulationsSubelements = XmlUtil.getChildElements(notationsSubelement);
                                for(Element articulationsSubelement : articulationsSubelements) {
                                    switch (articulationsSubelement.getTagName()) {
                                        case "staccato":
                                            Staccato staccato = new Staccato();
                                            Placement placement = new Placement();
                                            placement.setPlacement(EnumUtil.getLocation(articulationsSubelement.getAttribute("placement")));
                                            staccato.setValue(placement);
                                            notationList.add(staccato);
                                            break;
                                        case "tenuto":
                                            Tenuto tenuto = new Tenuto();
                                            Placement placement1 = new Placement();
                                            placement1.setPlacement(EnumUtil.getLocation(articulationsSubelement.getAttribute("placement")));
                                            tenuto.setValue(placement1);
                                            notationList.add(tenuto);
                                            break;
                                    }
                                }
                                break;
                            case "fermata":
                                Fermata fermata = new Fermata();
                                switch (notationsSubelement.getAttribute("type")) {
                                    case "upright":
                                        fermata.setType(FermataType.UPRIGHT);
                                        break;
                                    case "inverted":
                                        fermata.setType(FermataType.INVERTED);
                                        break;
                                }
                                notationList.add(fermata);
                                break;
                        }
                    }
                    notations.setNotations(notationList);
                    notationsList.add(notations);
                    break;
            }
        }

        musicDataList.add(note);
    }
}