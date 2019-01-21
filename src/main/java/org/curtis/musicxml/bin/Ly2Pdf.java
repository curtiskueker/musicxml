package org.curtis.musicxml.bin;

import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.properties.AppProperties;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Ly2Pdf extends MusicXmlScript {
    public Ly2Pdf() {

    }

    public void execute() throws MusicXmlException {
        try {
            List<String> commands = new ArrayList<>();
            commands.add(AppProperties.getString("location.lilypond"));
            commands.add("-fpdf");
            commands.add("-o" + OUTPUT_FILE);
            commands.add(INPUT_FILE);
            Process process = new ProcessBuilder(commands).start();
            InputStream errorStream = process.getErrorStream();
            int c;
            while ((c = errorStream.read()) != -1) {
                System.err.print((char)c);
            }
        } catch (Exception e) {
            throw new MusicXmlException(e);
        }
    }
}
