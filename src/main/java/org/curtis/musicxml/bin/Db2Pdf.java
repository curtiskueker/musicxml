package org.curtis.musicxml.bin;

import org.curtis.lilypond.ScoreBuilder;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.score.Score;
import org.curtis.musicxml.util.MusicXmlUtil;
import org.curtis.properties.AppProperties;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Db2Pdf extends MusicXmlScript {
    public Db2Pdf() {

    }

    public void execute() throws MusicXmlException {
        try {
            MusicXmlUtil.SKIP_COMMENTS = true;
            Score score = getScoreFromDb();
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
