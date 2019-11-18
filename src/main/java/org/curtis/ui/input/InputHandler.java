package org.curtis.ui.input;

import org.curtis.musicxml.bin.MusicXmlScript;

public abstract class InputHandler {
    protected MusicXmlScript musicXmlScript;

    protected InputHandler(MusicXmlScript musicXmlScript) {
        this.musicXmlScript = musicXmlScript;
    }

    public abstract void transferInputToTask();
}
