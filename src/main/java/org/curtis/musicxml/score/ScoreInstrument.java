package org.curtis.musicxml.score;

public class ScoreInstrument {
    private String instrumentName;
    private String instrumentAbbreviation;
    private String instrumentSound;
    // TODO: solo
    // TODO: ensemble
    private String virtualLibrary;
    private String virtualName;

    public ScoreInstrument() {

    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    public String getInstrumentAbbreviation() {
        return instrumentAbbreviation;
    }

    public void setInstrumentAbbreviation(String instrumentAbbreviation) {
        this.instrumentAbbreviation = instrumentAbbreviation;
    }

    public String getInstrumentSound() {
        return instrumentSound;
    }

    public void setInstrumentSound(String instrumentSound) {
        this.instrumentSound = instrumentSound;
    }

    public String getVirtualLibrary() {
        return virtualLibrary;
    }

    public void setVirtualLibrary(String virtualLibrary) {
        this.virtualLibrary = virtualLibrary;
    }

    public String getVirtualName() {
        return virtualName;
    }

    public void setVirtualName(String virtualName) {
        this.virtualName = virtualName;
    }
}
