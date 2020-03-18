package org.curtis.lilypond;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.lilypond.part.HarmonyPartBuilder;
import org.curtis.lilypond.part.PartBuilder;
import org.curtis.lilypond.util.AttributesUtil;
import org.curtis.lilypond.util.TypeUtil;
import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.attributes.Clef;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.direction.harmony.Harmony;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.note.Backup;
import org.curtis.musicxml.note.Forward;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.MeasureItem;
import org.curtis.musicxml.score.MusicData;
import org.curtis.musicxml.score.Part;
import org.curtis.musicxml.score.PartGroup;
import org.curtis.musicxml.score.PartItem;
import org.curtis.musicxml.score.PartName;
import org.curtis.musicxml.score.Score;
import org.curtis.musicxml.score.ScorePart;
import org.curtis.musicxml.util.MusicXmlUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreBuilder extends LilypondBuilder {
    private Score score;
    private Map<String, Integer> primaryVoiceStaves = new HashMap<>();

    public ScoreBuilder(Score score) {
        this.score = score;
    }

    public Score getScore() {
        return score;
    }

    public StringBuilder build() throws BuildException {
        // score header
        ScoreHeaderBuilder scoreHeaderBuilder = new ScoreHeaderBuilder(score.getScoreHeader());
        append(scoreHeaderBuilder.build().toString());

        // begin score
        appendStartSection("\\score {");
        appendStartSection("<<");

        List<PartItem> partItems = score.getScoreHeader().getPartList().getPartItems();
        if(partItems.isEmpty()) {
            throw new BuildException("Score header part list not found");
        }

        boolean scorePartFirst = partItems.get(0) instanceof ScorePart;

        if(scorePartFirst) {
            appendStartSection("<<");
        }

        for (PartItem partItem : partItems) {
            if(partItem instanceof PartGroup) {
                PartGroup partGroup = (PartGroup)partItem;
                Connection partGroupType = partGroup.getType();
                if (partGroupType == Connection.START) {
                    appendStartSection("\\new StaffGroup <<");
                } else if (partGroupType == Connection.STOP) {
                    appendEndSection(">>");
                }
            } else if(partItem instanceof ScorePart) {
                ScorePart scorePart = (ScorePart)partItem;
                appendStartSection("<<");
                buildPart(scorePart);
                appendEndSection(">>");
            }
        }

        if(scorePartFirst) {
            appendEndSection(">>");
        }

        // end score
        appendEndSection(">>");
        appendEndSection("}");

        return stringBuilder;
    }

    private void buildPart(ScorePart scorePart) throws BuildException {
        String partId = scorePart.getScorePartId();

        // find the corresponding Part indicated in the score header
        Part partToProcess = score.getParts().stream().filter(part -> partId.equals(part.getPartId())).findAny().orElse(null);

        if(partToProcess == null) throw new BuildException("Part " + partId + " not found");

        List<Measure> measures = partToProcess.getMeasures();
        if(measures.isEmpty()) {
            throw new BuildException("Part " + partId + " has no measures");
        }

        // pre-processing loop
        //
        // test for multi-staff part: default to 1 staff
        // assumes that the number of staves in a part doesn't change
        // test for existence of harmony data: construct harmony part
        Integer staves = 1;
        boolean hasHarmony = false;
        for (Measure measure : measures) {
            for(MusicData musicData : getMusicDataList(measure)) {
                if(musicData instanceof Attributes && staves == 1) {
                    Attributes attributes = (Attributes)musicData;
                    Integer attributesStaves = attributes.getStaves();
                    if (attributesStaves != null && attributesStaves > 1) {
                        staves = attributes.getStaves();
                    }
                } else if (musicData instanceof Harmony) {
                    hasHarmony = true;
                } else if (musicData instanceof Note && staves > 1) {
                    Note note = (Note)musicData;
                    String partVoice = partId + "/" + note.getVoice();
                    primaryVoiceStaves.computeIfAbsent(partVoice, k -> note.getStaff());
                }
            }
        }

        if (hasHarmony) {
            buildHarmonyPart(partToProcess);
        }

        if(staves > 1) {
            buildGrandStaffPart(scorePart, partToProcess, staves);
        } else {
            buildSingleStaffPart(scorePart, partToProcess);
        }
    }

    private List<MusicData> getMusicDataList(Measure measure) {
        List<MeasureItem> measureItems = measure.getMeasureItems();
        if (measureItems.isEmpty()) return measure.getMusicDataList();

        measure.getMusicDataList().clear();
        for (MeasureItem measureItem : measureItems) {
            try {
                MusicData musicData = MusicXmlUtil.getMusicDataForMeasureItem(measureItem);
                measure.getMusicDataList().add(musicData);
            } catch (MusicXmlException e) {
                displayMeasureMessage(measure, "MusicData not found for " + measureItem.getMusicDataType());
            }
        }

        return measure.getMusicDataList();
    }

    private void buildSingleStaffPart(ScorePart scorePart, Part part) throws BuildException {
        appendStartSection("\\new Staff");

        // staff identifiers
        appendStartSection("\\with {");

        PartName partName = scorePart.getPartName();
        if (TypeUtil.getBooleanDefaultYes(partName.getPrintObject())) {
            append("instrumentName = #\"");
            append(partName.getPartName());
            appendLine("\"");
        }

        PartName partAbbreviation = scorePart.getPartAbbreviation();
        if (partAbbreviation != null && TypeUtil.getBooleanDefaultYes(partAbbreviation.getPrintObject())) {
            append("shortInstrumentName = #\"");
            append(partAbbreviation.getPartName());
            appendLine("\"");
        }

        appendEndSection("}");

        PartBuilder partBuilder = new PartBuilder(part);
        append(partBuilder.build().toString());

        appendEndSection();
    }

    private void buildGrandStaffPart(ScorePart scorePart, Part part, Integer staves) throws BuildException {
        appendStartSection("\\new GrandStaff <<");

        String partId = scorePart.getScorePartId();
        PartName partName = scorePart.getPartName();
        if (TypeUtil.getBooleanDefaultYes(partName.getPrintObject())) {
            append("\\set GrandStaff.instrumentName = #\"");
            append(partName.getPartName());
            appendLine("\"");
        }

        PartName partAbbreviation = scorePart.getPartAbbreviation();
        if (partAbbreviation != null && TypeUtil.getBooleanDefaultYes(partAbbreviation.getPrintObject())) {
            append("\\set GrandStaff.shortInstrumentName = #\"");
            append(partAbbreviation.getPartName());
            appendLine("\"");
        }

        // separate the parts by staves
        Part[] staffParts = new Part[staves];
        for(int index = 0; index < staves; index++) {
            Part staffPart = new Part();
            Integer staffNumber = index + 1;
            staffPart.setPartId(partId + ", staff " + staffNumber);
            staffPart.setStaffNumber(staffNumber);
            staffParts[index] = staffPart;
        }

        Map<String, Integer> currentNoteVoiceStaves = new HashMap<>();
        for(Measure measure : part.getMeasures()) {
            Measure[] staffMeasures = new Measure[staves];
            for(int index = 0; index < staves; index++) {
                staffMeasures[index] = new Measure();
            }
            Integer currentStaff = 1;
            MusicData currentBackup = null;
            for(MusicData musicData : measure.getMusicDataList()) {
                if(musicData instanceof Direction) {
                    Direction direction = (Direction)musicData;
                    Integer staff = direction.getStaff();
                    // default direction to staff 1
                    if(staff == null || staff < 1 || staff > staves) {
                        staff = 1;
                    }
                    if(staff.equals(currentStaff) && currentBackup != null) {
                        staffMeasures[staff - 1].getMusicDataList().add(currentBackup);
                    }
                    staffMeasures[staff - 1].getMusicDataList().add(musicData);
                    currentBackup = null;
                    currentStaff = staff;
                } else if(musicData instanceof Note) {
                    Note note = (Note)musicData;
                    String voice = note.getVoice();
                    Integer noteStaff = note.getStaff();
                    if(noteStaff == null || noteStaff < 1 || noteStaff > staves) {
                        throw new BuildException("Invalid staff number in note");
                    }
                    Integer voiceStaff = primaryVoiceStaves.get(partId + "/" + voice);
                    if(voiceStaff.equals(currentStaff) && currentBackup != null) {
                        staffMeasures[voiceStaff - 1].getMusicDataList().add(currentBackup);
                    }
                    Integer currentNoteStaff = currentNoteVoiceStaves.get(voice);
                    if (currentNoteStaff != null && !noteStaff.equals(currentNoteStaff)) note.setChangeStaff();
                    staffMeasures[voiceStaff - 1].getMusicDataList().add(musicData);
                    currentBackup = null;
                    currentStaff = voiceStaff;
                    currentNoteVoiceStaves.put(voice, noteStaff);
                } else if(musicData instanceof Backup) {
                    currentBackup = musicData;
                } else if(musicData instanceof Forward) {
                    staffMeasures[currentStaff - 1].getMusicDataList().add(musicData);
                } else {
                    if (musicData instanceof Attributes) {
                        Attributes attributes = (Attributes)musicData;
                        // adjust per-staff Attributes
                        List<Clef> clefs = attributes.getClefs();
                        for (Integer index = 0; index < staves; index++) {
                            Attributes attributesCopy = AttributesUtil.attributesCopy(attributes);
                            List<Clef> staffClefs = new ArrayList<>();
                            Integer staffNumber = index + 1;
                            for (Clef clef : clefs) {
                                Integer clefNumber = clef.getNumber();
                                if (clefNumber == null || staffNumber.equals(clefNumber)) {
                                    staffClefs.add(clef);
                                }
                            }
                            attributesCopy.setClefs(staffClefs);
                            Measure staffMeasure = staffMeasures[index];
                            staffMeasure.getMusicDataList().add(attributesCopy);
                        }
                    } else {
                        for (Measure staffMeasure : staffMeasures) {
                            staffMeasure.getMusicDataList().add(musicData);
                        }
                    }
                }
            }

            // copy measure data over to measure copy
            for(Measure staffMeasure : staffMeasures) {
                staffMeasure.setNumber(measure.getNumber());
                staffMeasure.setText(measure.getText());
                staffMeasure.setImplicit(measure.getImplicit());
                staffMeasure.setNonControlling(measure.getNonControlling());
                staffMeasure.setWidth(measure.getWidth());
            }

            for(int index = 0; index < staves; index++) {
                Part staffPart = staffParts[index];
                staffPart.getMeasures().add(staffMeasures[index]);
            }
        }

        for(Part staffPart : staffParts) {
            List<Measure> partMeasures = staffPart.getMeasures();
            if (partMeasures.isEmpty()) {
                throw new BuildException(staffPart.getPartId() + " has no measures");
            }

            append("\\new Staff = \"");
            append(staffPart.getStaffNumber());
            append("\" ");
            PartBuilder partBuilder = new PartBuilder(staffPart);
            append(partBuilder.build().toString());
        }


        appendEndSection(">>");
    }

    private void buildHarmonyPart(Part part) throws BuildException {
        HarmonyPartBuilder harmonyPartBuilder = new HarmonyPartBuilder(part);
        append(harmonyPartBuilder.build().toString());
    }
}
