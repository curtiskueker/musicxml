package org.curtis.ui.input;

import org.curtis.musicxml.bin.DeleteScore;
import org.curtis.musicxml.bin.MusicXmlScript;
import org.curtis.ui.task.TaskInitializer;

public class DeleteScoreHandler extends InputHandler {
    @Override
    public MusicXmlScript transferInputToTask(TaskInitializer taskInitializer) {
        DeleteScore deleteScore = new DeleteScore();
        deleteScore.setScoreName(taskInitializer.getSelection("scoreName"));

        return deleteScore;
    }
}
