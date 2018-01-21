package org.curtis.lilypond.util;

import org.curtis.lilypond.part.PartBuilder;
import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.attributes.Clef;
import org.curtis.musicxml.attributes.Directive;
import org.curtis.musicxml.attributes.StaffDetails;
import org.curtis.musicxml.attributes.Transpose;
import org.curtis.musicxml.attributes.key.Key;
import org.curtis.musicxml.attributes.measure.MeasureStyle;
import org.curtis.musicxml.attributes.time.Time;
import org.curtis.musicxml.score.PartSymbol;

import java.math.BigDecimal;
import java.util.List;

public class AttributesUtil {
    private AttributesUtil() {

    }

    public static void setCurrentAttributes(Attributes attributes) {
        if(attributes == null) return;

        // first time setting
        if(PartBuilder.CURRENT_ATTRIBUTES == null) {
            PartBuilder.CURRENT_ATTRIBUTES = attributes;
            return;
        }

        // reset only those items found
        BigDecimal divisions = attributes.getDivisions();
        if (divisions != null) {
            PartBuilder.CURRENT_ATTRIBUTES.setDivisions(divisions);
        }

        List<Key> keys = attributes.getKeys();
        if(!keys.isEmpty()) {
            PartBuilder.CURRENT_ATTRIBUTES.setKeys(keys);
        }

        List<Time> timeList = attributes.getTimeList();
        if(!timeList.isEmpty()) {
            PartBuilder.CURRENT_ATTRIBUTES.setTimeList(timeList);
        }

        Integer staves = attributes.getStaves();
        if (staves != null) {
            PartBuilder.CURRENT_ATTRIBUTES.setStaves(staves);
        }

        PartSymbol partSymbol = attributes.getPartSymbol();
        if (partSymbol != null) {
            PartBuilder.CURRENT_ATTRIBUTES.setPartSymbol(partSymbol);
        }

        Integer instruments = attributes.getInstruments();
        if (instruments != null) {
            PartBuilder.CURRENT_ATTRIBUTES.setInstruments(instruments);
        }

        Clef clef = attributes.getClef();
        if(clef != null) {
            PartBuilder.CURRENT_ATTRIBUTES.setClef(clef);
        }

        List<StaffDetails> staffDetailsList = attributes.getStaffDetailsList();
        if(!staffDetailsList.isEmpty()) {
            PartBuilder.CURRENT_ATTRIBUTES.setStaffDetailsList(staffDetailsList);
        }

        List<Transpose> transpositions = attributes.getTranspositions();
        if(!transpositions.isEmpty()) {
            PartBuilder.CURRENT_ATTRIBUTES.setTranspositions(transpositions);
        }

        List<Directive> directives = attributes.getDirectives();
        if(!directives.isEmpty()) {
            PartBuilder.CURRENT_ATTRIBUTES.setDirectives(directives);
        }

        List<MeasureStyle> measureStyles = attributes.getMeasureStyles();
        if(!measureStyles.isEmpty()) {
            PartBuilder.CURRENT_ATTRIBUTES.setMeasureStyles(measureStyles);
        }

    }
}
