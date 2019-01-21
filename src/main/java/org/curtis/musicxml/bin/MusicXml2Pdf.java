package org.curtis.musicxml.bin;

import org.curtis.lilypond.ScoreBuilder;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.handler.ScoreHandler;
import org.curtis.musicxml.score.Score;
import org.curtis.properties.AppProperties;

import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class MusicXml2Pdf extends MusicXmlScript {
    public MusicXml2Pdf() {

    }

    public void execute() throws MusicXmlException {
        try {
            File xmlFile = new File(INPUT_FILE);
            SKIP_COMMENTS = true;
            ScoreHandler scoreHandler = handleXmlScoreFile(xmlFile);
            Score score = scoreHandler.getScore();
            ScoreBuilder scoreBuilder = new ScoreBuilder(score);

            List<String> commands = new ArrayList<>();
            commands.add(AppProperties.getString("location.lilypond"));
            commands.add("-fpdf");
            commands.add("-o" + OUTPUT_FILE);
            commands.add("-");
            Process process = new ProcessBuilder(commands).start();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            writer.write(scoreBuilder.build().toString());
            writer.flush();
            writer.close();
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
