package cz.cvut.anokhver.contollers;

import javafx.scene.layout.VBox;

public abstract class AContoller {
    public void init() {
    }

    public abstract VBox getView();

    public abstract void update(double delta);

}
