package org.curtis.musicxml.direction.type;

import org.curtis.musicxml.common.PrintStyleAlign;

import java.util.List;

public class HarpPedals extends DirectionType {
    private List<PedalTuning> pedalTunings;
    private PrintStyleAlign printStyleAlign;

    public HarpPedals() {

    }

    public List<PedalTuning> getPedalTunings() {
        return pedalTunings;
    }

    public void setPedalTunings(List<PedalTuning> pedalTunings) {
        this.pedalTunings = pedalTunings;
    }

    public PrintStyleAlign getPrintStyleAlign() {
        return printStyleAlign;
    }

    public void setPrintStyleAlign(PrintStyleAlign printStyleAlign) {
        this.printStyleAlign = printStyleAlign;
    }
}
