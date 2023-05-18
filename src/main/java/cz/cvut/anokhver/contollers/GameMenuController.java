package cz.cvut.anokhver.contollers;

import cz.cvut.anokhver.GameLogic;
import cz.cvut.anokhver.menu.InGameMenu;
import javafx.scene.input.KeyCode;

/**
 * Controller for the game menu
 *
 * @author Veronika
 */
public class GameMenuController extends AContoller {

    /**
     * Setting all button pushes for view etc...
     */
    public GameMenuController() {
        InGameMenu temp_view = new InGameMenu();

        temp_view.getExitButton().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                System.exit(0);
            }
        });


        temp_view.getContinueButton().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                GameLogic.renewGame();
            }
        });

        temp_view.getSaveButton().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                GameLogic.saveGame();
            }
        });
        temp_view.getExitMainMenu().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                GameLogic.setMainMenu();
            }
        });


        this.setView(temp_view);
    }


    @Override
    public void update(double delta) {

    }

}
