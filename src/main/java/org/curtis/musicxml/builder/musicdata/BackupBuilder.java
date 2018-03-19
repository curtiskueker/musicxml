package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.note.Backup;

public class BackupBuilder extends BaseBuilder {
    private Backup backup;

    public BackupBuilder(Backup backup) {
        this.backup = backup;
    }

    public StringBuilder build() {
        if (backup == null) return stringBuilder;

        // TODO: backup duration
        appendLine("<backup>");
        buildElementWithValue("duration", 1);
        appendLine("</backup>");

        return stringBuilder;
    }
}
