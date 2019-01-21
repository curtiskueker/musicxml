package org.curtis.musicxml.bin;

import org.curtis.musicxml.exception.MusicXmlException;

public class Db2Ly extends MusicXmlScript {
    public void execute() throws MusicXmlException {
        if (!OUTPUT_FILE.endsWith(".ly")) OUTPUT_FILE += ".ly";

        SKIP_COMMENTS = true;
        outputScore(getScoreFromDb());
    }

    public static void main(String[] args) {
        try {
            Db2Ly db2Ly = new Db2Ly();
            db2Ly.setArgs(args);
            db2Ly.execute();
        } catch (MusicXmlException e) {
            System.err.println("Fatal exception: " + e.getMessage());
        } catch (Throwable e){
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
}
