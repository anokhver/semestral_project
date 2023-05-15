package cz.cvut.anokhver.contollers;

import cz.cvut.anokhver.GameLogic;
import cz.cvut.anokhver.menu.InGameMenu;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

public class GameMenuController extends AContoller {

    private final InGameMenu view;

    public GameMenuController() {
        this.view = new InGameMenu();

        view.getExitButton().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                System.exit(0);
            }
        });


        view.getContinueButton().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                GameLogic.renewGame();
            }
        });


    }

    public VBox getView() {
        return view;
    }

    public void update(double delta) {

    }

}
