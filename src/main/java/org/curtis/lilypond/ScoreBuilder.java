package org.curtis.lilypond;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.lilypond.part.HarmonyPartBuilder;
import org.curtis.lilypond.part.PartBuilder;
import org.curtis.lilypond.util.TypeUtil;
import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.attributes.Clef;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.direction.harmony.Harmony;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.note.Backup;
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

import java.util.List;

public class ScoreBuilder extends AbstractBuilder {
    private Score score;

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
        appendLine("\\score {");
        appendLine("<<");

        List<PartItem> partItems = score.getScoreHeader().getPartList().getPartItems();
        if(partItems.isEmpty()) {
            throw new BuildException("Score header part list not found");
        }

        boolean scorePartFirst = partItems.get(0) instanceof ScorePart;

        if(scorePartFirst) {
            appendLine("<<");
        }

        for (PartItem partItem : partItems) {
            if(partItem instanceof PartGroup) {
                PartGroup partGroup = (PartGroup)partItem;
                Connection partGroupType = partGroup.getType();
                if (partGroupType == Connection.START) {
                    appendLine("\\new StaffGroup <<");
                } else if (partGroupType == Connection.STOP) {
                    appendLine(">>");
                }
            } else if(partItem instanceof ScorePart) {
                ScorePart scorePart = (ScorePart)partItem;
                appendLine("<<");
                buildPart(scorePart);
                appendLine(">>");
            }
        }

        if(scorePartFirst) {
            appendLine(">>");
        }

        // end score
        appendLine(">>");
        appendLine("}");

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
        // test for existence of harmony data: construct harmony part
        Integer staves = 1;
        boolean hasHarmony = false;
        for (Measure measure : measures) {
            for(MeasureItem measureItem : measure.getMeasureItems()) {
                MusicData musicData;
                try {
                    musicData = MusicXmlUtil.getMusicDataForMeasureItem(measureItem);
                } catch (MusicXmlException e) {
                    throw new BuildException(e);
                }
                if(musicData instanceof Attributes) {
                    Attributes attributes = (Attributes)musicData;
                    Integer attributesStaves = attributes.getStaves();
                    if (attributesStaves != null && attributesStaves > 1) {
                        staves = attributes.getStaves();
                    }
                } else if (musicData instanceof Harmony) {
                    hasHarmony = true;
                }
                measure.getMusicDataList().add(musicData);
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

    private void buildSingleStaffPart(ScorePart scorePart, Part part) throws BuildException {
        appendLine("\\new Staff");

        // staff identifiers
        appendLine("\\with {");

        PartName partName = scorePart.getPartName();
        if (TypeUtil.getBooleanDefaultYes(partName.getPartNamePrintObject())) {
            append("instrumentName = #\"");
            append(partName.getPartName());
            appendLine("\"");
        }

        PartName partAbbreviation = scorePart.getPartAbbreviation();
        if (partAbbreviation != null && TypeUtil.getBooleanDefaultYes(partAbbreviation.getPartNamePrintObject())) {
            append("shortInstrumentName = #\"");
            append(partAbbreviation.getPartName());
            appendLine("\"");
        }

        appendLine("}");

        PartBuilder partBuilder = new PartBuilder(part);
        append(partBuilder.build().toString());
    }

    private void buildGrandStaffPart(ScorePart scorePart, Part part, Integer staves) throws BuildException {
        appendLine("\\new GrandStaff <<");

        PartName partName = scorePart.getPartName();
        if (TypeUtil.getBooleanDefaultYes(partName.getPartNamePrintObject())) {
            append("\\set GrandStaff.instrumentName = #\"");
            append(partName.getPartName());
            appendLine("\"");
        }

        PartName partAbbreviation = scorePart.getPartAbbreviation();
        if (partAbbreviation != null && TypeUtil.getBooleanDefaultYes(partAbbreviation.getPartNamePrintObject())) {
            append("\\set GrandStaff.shortInstrumentName = #\"");
            append(partAbbreviation.getPartName());
            appendLine("\"");
        }

        // separate the parts by staves
        Part[] staffParts = new Part[staves];
        for(int index = 0; index < staves; index++) {
            staffParts[index] = new Part();
            staffParts[index].setPartId(scorePart.getScorePartId() + ", staff " + String.valueOf(index + 1));
        }

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
                    Integer staff = note.getStaff();
                    if(staff == null || staff < 1 || staff > staves) {
                        throw new BuildException("Invalid staff number in note");
                    }
                    if(staff.equals(currentStaff) && currentBackup != null) {
                        staffMeasures[staff - 1].getMusicDataList().add(currentBackup);
                    }
                    staffMeasures[staff - 1].getMusicDataList().add(musicData);
                    currentBackup = null;
                    currentStaff = staff;
                } else if(musicData instanceof Backup) {
                    currentBackup = musicData;
                } else {
                    if (musicData instanceof Attributes) {
                        Attributes attributes = (Attributes)musicData;
                        List<Clef> clefs = attributes.getClefs();
                        for (Integer index = 0; index < staves; index++) {
                            Integer staffNumber = index + 1;
                            Measure staffMeasure = staffMeasures[index];
                            Attributes staffMeasureAttributes = new Attributes();
                            for (Clef clef : clefs) {
                                Integer clefNumber = clef.getNumber();
                                if (clefNumber == null || staffNumber.equals(clefNumber)) staffMeasureAttributes.getClefs().add(clef);
                            }
                            staffMeasure.getMusicDataList().add(staffMeasureAttributes);
                        }
                        clefs.clear();
                    }
                    for(Measure staffMeasure : staffMeasures) {
                        staffMeasure.getMusicDataList().add(musicData);
                    }
                }
            }

            for(Measure staffMeasure : staffMeasures) {
                staffMeasure.setNumber(measure.getNumber());
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

            append("\\new Staff ");
            PartBuilder partBuilder = new PartBuilder(staffPart);
            append(partBuilder.build().toString());
        }


        appendLine(">>");
    }

    private void buildHarmonyPart(Part part) throws BuildException {
        HarmonyPartBuilder harmonyPartBuilder = new HarmonyPartBuilder(part);
        append(harmonyPartBuilder.build().toString());
    }
}
