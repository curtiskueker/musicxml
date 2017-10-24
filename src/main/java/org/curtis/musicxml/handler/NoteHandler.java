package org.curtis.musicxml.handler;

import org.curtis.musicxml.note.FullNote;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.note.Pitch;
import org.curtis.musicxml.note.Step;
import org.curtis.musicxml.score.MusicData;
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
                    fullNote.setPitch(pitch);
                    note.setFullNote(fullNote);
                    break;
                case "duration":
                    break;
                case "tie":
                    break;
                case "voice":
                    break;
                case "type":
                    break;
                case "stem":
                    break;
                case "notations":
                    break;
            }
        }

        musicDataList.add(note);
    }
}
