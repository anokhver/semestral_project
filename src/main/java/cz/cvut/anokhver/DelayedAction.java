package cz.cvut.anokhver;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class DelayedAction {
    private boolean actionExecuted = false;

    public DelayedAction(Duration delay, Runnable action) {
        PauseTransition pause = new PauseTransition(delay);
        pause.setOnFinished(event -> {
            if (!actionExecuted) {
                action.run();
                actionExecuted = true;
            }
        });
        pause.play();
    }
}
