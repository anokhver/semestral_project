package cz.cvut.anokhver.menu;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public abstract class AMenu extends VBox {
    protected Scene scene;
    public abstract void init();
    public abstract void update_menu();
}
