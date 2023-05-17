package cz.cvut.anokhver.contollers;

import cz.cvut.anokhver.GameLogic;
import cz.cvut.anokhver.menu.InGameMenu;
import javafx.scene.input.KeyCode;

public class GameMenuController extends AContoller {


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

        this.setView(temp_view);
    }



    public void update(double delta) {

    }

}
