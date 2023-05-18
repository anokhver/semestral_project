package cz.cvut.anokhver.contollers;

import cz.cvut.anokhver.GameLogic;
import cz.cvut.anokhver.menu.MainMenu;
import javafx.scene.input.KeyCode;

public class MainMenuController extends AContoller {

    public MainMenuController() {
        MainMenu temp_view = new MainMenu();

        temp_view.getExitButton().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                System.exit(0);
            }
        });

        temp_view.getStartButton().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                GameLogic.new_game(1);
            }
        });

        temp_view.getLoadButton().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                GameLogic.load_game();
            }
        });

        this.setView(temp_view);
    }

    @Override
    public void update(double delta) {

    }
}
