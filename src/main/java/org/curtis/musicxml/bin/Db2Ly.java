package org.curtis.musicxml.bin;

import org.curtis.musicxml.exception.MusicXmlException;

public class Db2Ly extends MusicXmlScript {
    private void execute() throws MusicXmlException {
        OUTPUT_FILE += ".ly";

        outputScore(getScoreFromDb());
    }

    public static void main(String[] args) {
        try {
            setArgs(args);

            Db2Ly db2Ly = new Db2Ly();
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
