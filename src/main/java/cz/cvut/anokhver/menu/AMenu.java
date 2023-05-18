package cz.cvut.anokhver.menu;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;

/**
 * Abstarct class for implementing view representention for contollers
 *
 * @author Veronika
 */
public abstract class AMenu extends VBox {
    protected Scene scene;

    /**
     * Updates the view of menu if the menu is animated
     */
    public abstract void update_menu();
}
