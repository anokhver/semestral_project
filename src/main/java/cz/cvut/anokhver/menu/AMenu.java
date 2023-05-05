package cz.cvut.anokhver.menu;

import cz.cvut.anokhver.contollers.AContoller;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public abstract class AMenu extends VBox {
    protected Scene scene;
    protected AContoller controller;
    public abstract void init();
}
